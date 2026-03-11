from pathlib import Path
from typing import Dict, List
import joblib
import numpy as np
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field

BASE_DIR = Path(__file__).resolve().parent.parent
MODEL_PATH = BASE_DIR / "models" / "disease_model.joblib"
META_PATH = BASE_DIR / "models" / "model_meta.joblib"

app = FastAPI(title="AI Medical ML Service", version="1.0.0")


class PredictRequest(BaseModel):
    symptoms: List[str] = Field(min_length=1)


class PredictResponse(BaseModel):
    predicted_disease: str
    confidence_score: float
    probabilities: Dict[str, float]


def load_artifacts():
    if not MODEL_PATH.exists() or not META_PATH.exists():
        raise RuntimeError("Model artifacts not found. Run train_model.py first.")
    model = joblib.load(MODEL_PATH)
    meta = joblib.load(META_PATH)
    return model, meta


@app.get("/health")
def health():
    return {"status": "ok"}


@app.post("/predict", response_model=PredictResponse)
def predict(req: PredictRequest):
    try:
        model, meta = load_artifacts()
    except RuntimeError as ex:
        raise HTTPException(status_code=500, detail=str(ex)) from ex

    features = meta["features"]
    input_vector = np.zeros(len(features), dtype=int)

    symptom_set = set(s.strip().lower() for s in req.symptoms)
    for idx, feature in enumerate(features):
        if feature.lower() in symptom_set:
            input_vector[idx] = 1

    if input_vector.sum() == 0:
        raise HTTPException(status_code=400, detail="No recognized symptoms provided")

    probs = model.predict_proba([input_vector])[0]
    max_index = int(np.argmax(probs))
    disease = model.classes_[max_index]
    confidence = float(probs[max_index])

    all_probs = {label: float(prob) for label, prob in zip(model.classes_, probs)}

    return PredictResponse(
        predicted_disease=disease,
        confidence_score=round(confidence, 4),
        probabilities=all_probs,
    )

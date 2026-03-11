from pathlib import Path
import joblib
import pandas as pd
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score, classification_report
from sklearn.model_selection import train_test_split

BASE_DIR = Path(__file__).parent
DATASET_PATH = BASE_DIR / "data" / "dataset.csv"
MODEL_DIR = BASE_DIR / "models"
MODEL_PATH = MODEL_DIR / "disease_model.joblib"
META_PATH = MODEL_DIR / "model_meta.joblib"


def main() -> None:
    df = pd.read_csv(DATASET_PATH)
    feature_columns = [c for c in df.columns if c != "disease"]

    X = df[feature_columns]
    y = df["disease"]

    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.25, random_state=42, stratify=y
    )

    model = RandomForestClassifier(n_estimators=300, random_state=42, class_weight="balanced")
    model.fit(X_train, y_train)

    predictions = model.predict(X_test)
    accuracy = accuracy_score(y_test, predictions)

    print("Model Accuracy:", round(accuracy, 4))
    print(classification_report(y_test, predictions))

    MODEL_DIR.mkdir(parents=True, exist_ok=True)
    joblib.dump(model, MODEL_PATH)
    joblib.dump({"features": feature_columns, "classes": model.classes_.tolist()}, META_PATH)
    print(f"Saved model to: {MODEL_PATH}")


if __name__ == "__main__":
    main()

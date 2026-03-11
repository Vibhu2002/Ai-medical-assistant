# API Documentation

## Authentication
- `POST /api/auth/register`
- `POST /api/auth/login`

## Prediction
- `POST /api/predict`
  - Request: `{ "symptoms": ["fever", "cough"] }`
  - Response: `{ "predictedDisease": "Flu", "confidenceScore": 0.89, "recommendation": "..." }`

## Admin
- `POST /api/admin/diseases`
- `PUT /api/admin/diseases/{id}`
- `DELETE /api/admin/diseases/{id}`
- `POST /api/admin/symptoms`
- `GET /api/admin/predictions`

## ML Service
- `GET /health`
- `POST /predict`

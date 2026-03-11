# Deployment Guide

## Local
1. Initialize MySQL using `database/schema.sql`.
2. Train and start ML service on port 8001.
3. Start Spring Boot backend on port 8081.
4. Serve frontend on port 8080.

## Docker (optional outline)
- Build backend jar and ML image.
- Use docker-compose with services: mysql, ml-service, backend, frontend nginx.
- Inject secrets through environment variables.

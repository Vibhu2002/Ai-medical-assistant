# ER Diagram

```mermaid
erDiagram
    USERS ||--o{ PREDICTIONS : creates
    DISEASES ||--o{ DISEASE_SYMPTOMS : has
    SYMPTOMS ||--o{ DISEASE_SYMPTOMS : maps
```

# Shop Service

### Get All Shop

- `GET /shops`

### All need authentication

- `POST /shops`
- `GET /shops/me`
- `PATCH /shops/me`
- `DELETE /shops/me`

### Create shop

```json
{

  "shopname": "String",
  "description": "String",
  "tel": "String (10 Number)",
  "address": "String",
  "rating":"String (0-5)",
  "items": [
    {
      "name": "String",
      "price": "Number"
    }
  ]

}
```

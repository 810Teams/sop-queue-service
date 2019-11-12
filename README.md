# API Endpoints : Inventory Service

## Shop-service

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

## Product-service

- `GET /products/all`

### All need authentication
- `POST /products`
- `GET /products/me`
- `POST /products/me/items/`
- `GET /products/me/items/`
- `GET /products/me/items/:item_id`
- `DELETE /products/me/items/:item_id`

### Change items or Create items
```json
[
    {
      "name": "String",
      "price": "Number"
    }
]
```

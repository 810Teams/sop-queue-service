# API Endpoints : Inventory Service

## Shop-service

- `POST /shops`
- `GET /shops`
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

- `POST /products`
- `GET /products/all`
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

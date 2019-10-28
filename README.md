# API Endpoints : Inventory Service

## Shop-service

- `POST /shops`
- `GET /shops`
- `GET /shops/:shop_id`
- `PATCH /shops/:shop_id`
- `DELETE /shops/:shop_id`

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
- `GET /products`
- `GET /products/:shop_id`
- `DELETE /products/:shop_id`
- `POST /products/:shop_id/items/`
- `GET /products/:shop_id/items/`
- `GET /products/:shop_id/items/:item_id`
- `DELETE /products/:shop_id/items/:item_id`

### Change items or Create items
```json
[
    {
      "name": "String",
      "price": "Number"
    }
]
```

# Product Service

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

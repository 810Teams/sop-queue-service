## Product service
### Shop
```json
    GET /shops --> Get all shops
    GET /shops/{shop_id} --> Get shop
    POST /shops --> Create a shop
        Templates :
        {
            "shopname":String,
            "items":
            [
                {"name":String,"price":Number}
            ]
        }
    PATCH /shops/{shop_id} --> Update shop info
    DELETE /shops/{shop_id} --> Delete a shop
``` 
### Item
```json
    GET /shops/{shop_id}/items 
    --> Get all items in shops
    GET /shops/{shop_id}/items/{item_id} 
    --> Get all items in shops
    POST /shops/{shop_id}/items 
    --> Create item for shop
        Templates :
            [
                {"name":String,"price":Number},
                ..more
            ]
    PATCH /shops/{shop_id}/items/{item_id}
    --> Update item info
    DELETE /shops/{shop_id}/items/{item_id} --> Delete item in shop
``` 
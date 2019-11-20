# API Endpoints : Reservation Service

`POST /reservation/new` (Customer Authentication Required)

```json
{
    "items": [
        {
            "itemId": "String"
            "amount": "Integer"
        }
    ],
    "timePeriod": {
        "startDateTime": "yyyy-MM-dd hh:mm",
        "endDateTime": "yyyy-MM-dd hh:mm"
    },
    "message": "String"
}
```

`GET /reservation/all`

`POST /reservation/id/{id}` (Authentication Required)

`POST /reservation/user/{userId}` (Customer Authentication Required)

`POST /reservation/shop/{userId}` (Shop Authentication Required)

`POST /reservation/id/{id}/cancel` (Authentication Required)

`POST /reservation/id/{id}/check` (Shop Authentication Required)

`POST /reservation/id/{id}/missed` (Shop Authentication Required)

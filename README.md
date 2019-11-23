# API Endpoints : Reservation Service

### Create New Reservation

`POST /reservation/new`

Customer authentication required

```json
{
    "items": [
        {
            "itemId": "String",
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

### View All Reservation

`GET /reservation/all`

### View Single Reservation

`POST /reservation/id/{id}`

Authentication required, either customer or shop owner of a certain reservation.

### View User's Reservation

`POST /reservation/user/{userId}`

Authentication required, only customer.

### View Shop's Reservation

`POST /reservation/shop/{userId}`

Authentication Required, only shop owner.

### Cancel Reservation

`POST /reservation/id/{id}/cancel`

Authentication required, either customer or shop owner of a certain reservation.

### Check Reservation as Success

`POST /reservation/id/{id}/check`

Authentication Required, only shop owner of a certain reservation.

### Check Reservation as Missed

`POST /reservation/id/{id}/missed`

Authentication Required, only shop owner of a certain reservation.

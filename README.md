# API Endpoints : Reservation Service

### Create New Reservation

`POST /reservation/new`

- Authentication required
- Customer token only

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

- Only for testing and development

### View Single Reservation

`POST /reservation/id/{id}`

- Authentication required
- Customer or shop owner of a certain reservation

### View Own Reservation

`POST /reservation/me`

- Authentication required
- Customer token: Show all customer's reservations
- Shop owner token: Show all shop's received reservations

### Cancel Reservation

`POST /reservation/id/{id}/cancel`

- Authentication required
- Customer token: Can only cancel before reservation start date time
- Shop owner token: Cancel *before* reservation end date time will change reservation's status to CANCELLED_BY_SHOP
- Shop owner token: Cancel *after* reservation end date time will change reservation's status to MISSED

### Check Reservation as Success

`POST /reservation/id/{id}/check`

- Authentication required
- Only shop owner of a certain reservation

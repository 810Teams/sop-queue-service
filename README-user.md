# User Management Service

- `POST /users`

### Create User

```json
{
    "username": "String",
    "password": "String",
    "firstname": "String",
    "lastname": "String",
    "role": "shop|customer"
}
```

### All need authentication

- `GET /users/me`
- `PATCH /users/me`
- `DELETE /users/me`

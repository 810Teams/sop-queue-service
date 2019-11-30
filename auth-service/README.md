# Authentication Service

### Login

`POST /login`

User sends username and password to get token.

```json
{
  "username": "String",
  "password": "String"
}
```

### Verify User

`GET /verify`

1. Attach token with header `Bearer token`.
2. Send GET request.
3. Wait for a response from server.
4. If valud, server send user data back to client.

### Logout

`GET /logout`

### Logout All

`GET /logoutAll`

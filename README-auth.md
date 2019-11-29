# API Endpoints : Authentication service and User-management-service

## Auth-service

- `POST /login`
```text
User send username and password to get token
```
- `GET /verify`
```text
Use to verify token 
-------------------
*** How to use ***
1.Attach token with header `Bearer tokenxxx`
2.Send get request
3.Wait for responding from server
4.If pass server send user back to client 
```
- `GET /logout`
- `GET /logoutAll`

### Login

```json
{

  "username":"String",
  "password":"String"

}
```

## User-management-service

- `POST /users`

### Create user
```json
{
    "username":"String",
    "password":"String",
    "firstname":"String",
    "lastname":"String",
    "role":"shop|customer"
}
```

### All need authentication
- `GET /users/me`
- `PATCH /users/me`
- `DELETE /users/me`

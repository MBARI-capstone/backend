# MBARI Backend

## Authentication and Authorization

## How to use API

### Get JWT Token

POST `/api/v1.1/auth/login` 
Request Body
```json
{
  "username": "your_username",
  "password": "your_password"
}
```

```shell
Authorization: Bearer YOUR_JWT_TOKEN
```
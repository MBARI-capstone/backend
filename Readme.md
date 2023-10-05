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
Response Body
```json
{
    "accessToken": "JWT Token",
    "tokenType": "Bearer "
}
```
You need to call api with Authorization JWT which you can get after login post method.
![image](https://github.com/MBARI-capstone/backend/assets/75482698/78f0fb0c-e669-4ecc-8d6c-965981bbeb7a)

package com.MBARI.security;

public class SecurityConstants {
    public static final long ACCESS_TOKEN_EXPIRATION = 900000; // 15 minutes
    public static final long REFRESH_TOKEN_EXPIRATION = 604800000; // 7 days


    // TODO: put JWT_SECRET_STRING in env file
    public static final String JWT_SECRET_STRING = "secretkeyneedstobemorethanthirtytwolettershelloworldhappybirthday";
}

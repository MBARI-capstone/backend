package com.MBARI.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // すべてのパスに適用
                        .allowedOrigins("http://localhost:3000") // 許可するオリジン
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 許可するHTTPメソッド
                        .allowedHeaders("*") // すべてのヘッダーを許可
                        .allowCredentials(true); // クレデンシャルを許可
            }
        };
    }
}


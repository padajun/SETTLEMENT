package com.example.settlement.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public GroupedOpenApi pubicApi() {
        return GroupedOpenApi.builder()
                .group("settlement")
                .pathsToMatch("/api/settlements/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenApi(){
        return new OpenAPI()
              .info(new Info().title("정상관리 API")
              .description("정산관리 서비스 API 명세서 입니다.")
              .version("v0.0.1")
              .license(new License().name("Apache 2.0").url("http://springdoc.org")))
              .externalDocs(new ExternalDocumentation()
              .description("SpringShop Wiki Documentation")
              .url("https://springshop.wiki.github.org/docs"));
    }
}

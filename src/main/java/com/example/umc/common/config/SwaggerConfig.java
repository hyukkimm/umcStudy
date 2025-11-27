package com.example.umc.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Swagger (OpenAPI) 설정 클래스.
 * - Profile: "prod" 환경을 제외한 환경에서만 활성화됩니다.
 * - OpenAPI Bean: 전역적인 API 문서 정보를 설정합니다. (인증, 서버 URL 등)
 * - GroupedOpenApi Beans: API를 기능/도메인별로 그룹화합니다.
 */
@Profile("!prod")
@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    /**
     * 전역 OpenAPI 설정을 담는 Bean.
     * API의 기본 정보, 서버 정보, 보안 관련 설정을 중앙에서 관리합니다.
     */
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Project API Document")
                .version("v1")
                .description("해커톤 API 명세서입니다.");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

    /**
     * 사용자 API 그룹.
     * /api/v1/users/** 경로의 API들을 그룹화합니다.
     */
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("1. User API") // 그룹 이름 지정
                .pathsToMatch("/**") // 이 패턴에 해당하는 경로만 포함
                .build();
    }

    /**
     * 관리자 API 그룹.
     * /api/v1/admin/** 경로의 API들을 그룹화합니다.
     */
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("2. Admin API")
                .pathsToMatch("/api/v1/admin/**")
                .build();
    }
}

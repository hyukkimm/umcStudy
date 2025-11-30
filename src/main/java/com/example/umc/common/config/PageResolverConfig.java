package com.example.umc.common.config;

import com.example.umc.common.annotation.ValidPage;
import com.example.umc.common.apiPayload.code.GeneralErrorCode;
import com.example.umc.common.exception.GeneralException;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class PageResolverConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ValidPageArgumentResolver());
    }

    private static class ValidPageArgumentResolver implements HandlerMethodArgumentResolver {

        private static final int MINIMUM_PAGE = 1;

        @Override
        public boolean supportsParameter(org.springframework.core.MethodParameter parameter) {
            return parameter.hasParameterAnnotation(ValidPage.class)
                    && (Integer.class.isAssignableFrom(parameter.getParameterType())
                    || int.class.isAssignableFrom(parameter.getParameterType()));
        }

        @Override
        public Object resolveArgument(
                org.springframework.core.MethodParameter parameter,
                org.springframework.web.context.request.NativeWebRequest webRequest,
                org.springframework.web.bind.support.WebDataBinderFactory binderFactory,
                org.springframework.web.method.support.ModelAndViewContainer mavContainer
        ) {
            ValidPage annotation = parameter.getParameterAnnotation(ValidPage.class);
            String pageParamName = annotation != null ? annotation.value() : "page";
            String pageValue = webRequest.getParameter(pageParamName);

            int page = (pageValue == null || pageValue.isBlank()) ? MINIMUM_PAGE : Integer.parseInt(pageValue);

            if (page < MINIMUM_PAGE) {
                throw new GeneralException(GeneralErrorCode.INVALID_PAGE);
            }

            return page;
        }
    }
}


package study.crudboard.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import study.crudboard.interceptor.AuthorCheckInterceptor;
import study.crudboard.interceptor.LoginCheckInterceptor;
import study.crudboard.service.ArticleService;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final ArticleService articleService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/articles/**")
                .excludePathPatterns("/", "/login", "/logout", "/signup","/css/**", "/js/**", "/images/**");

        registry.addInterceptor(new AuthorCheckInterceptor(articleService))
                .addPathPatterns("/articles/*/edit", "/articles/*/delete");
    }
}
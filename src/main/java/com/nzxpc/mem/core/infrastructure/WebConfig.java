package com.nzxpc.mem.core.infrastructure;

import com.nzxpc.handler.web.WebAppConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class WebConfig extends WebAppConfig{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorityInterceptor()).addPathPatterns("/**").excludePathPatterns("/images/**");
        super.addInterceptors(registry);
    }
}

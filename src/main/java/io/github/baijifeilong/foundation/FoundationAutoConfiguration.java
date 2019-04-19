package io.github.baijifeilong.foundation;

import io.github.baijifeilong.foundation.cache.CacheHelper;
import io.github.baijifeilong.foundation.http.RestHelper;
import io.github.baijifeilong.foundation.web.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by BaiJiFeiLong@gmail.com at 2019-04-18 21:16
 */
@Configuration
@EnableConfigurationProperties(FoundationProperties.class)
@ConditionalOnProperty("baijifeilong.foundation.enabled")
@Import({RestHelper.class, CacheHelper.class})
public class FoundationAutoConfiguration {

    @Configuration
    @ConditionalOnProperty("baijifeilong.foundation.web.global-exception-handler-enabled")
    @Import(GlobalExceptionHandler.class)
    static class GlobalExceptionHandlerConfiguration {
    }
}

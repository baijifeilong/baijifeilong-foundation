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
@ConditionalOnProperty(value = "baijifeilong.foundation.enabled", matchIfMissing = true)
@EnableConfigurationProperties(FoundationProperties.class)
@Import({RestHelper.class, CacheHelper.class})
public class FoundationAutoConfiguration {

    @Configuration
    @ConditionalOnProperty(value = "baijifeilong.foundation.web.global-exception-handler-enabled", matchIfMissing = true)
    @Import(GlobalExceptionHandler.class)
    static class GlobalExceptionHandlerConfiguration {
    }
}

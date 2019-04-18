package io.github.baijifeilong.foundation;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by BaiJiFeiLong@gmail.com at 2019-04-18 21:17
 */
@ConfigurationProperties(prefix = "foundation")
@Data
@SuppressWarnings("WeakerAccess")
public class FoundationProperties {

    private boolean enabled;

    private Web web;

    @Data
    public static class Web {
        private boolean globalExceptionHandlerEnabled;
    }
}

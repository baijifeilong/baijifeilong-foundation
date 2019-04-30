package io.github.baijifeilong.foundation;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by BaiJiFeiLong@gmail.com at 2019-04-18 21:17
 */
@ConfigurationProperties(prefix = "baijifeilong.foundation")
@Data
public class FoundationProperties {

    private boolean enabled = true;

    private Web web = new Web();

    @Data
    public static class Web {
        private boolean globalExceptionHandlerEnabled = true;
        private int defaultExceptionCode = 110000;
        private String defaultExceptionMessageTemplate = "未知错误: %s";
        private int notFoundExceptionCode = 110000;
        private String notFoundExceptionMessageTemplate = "接口(%s)不存在";
    }
}

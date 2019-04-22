package io.github.baijifeilong.foundation;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;

/**
 * Created by BaiJiFeiLong@gmail.com at 2019-04-22 16:45
 */
public class FoundationEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @SneakyThrows
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        environment.getPropertySources().addLast(new ResourcePropertySource("classpath:baijifeilong-foundation.properties"));
    }
}

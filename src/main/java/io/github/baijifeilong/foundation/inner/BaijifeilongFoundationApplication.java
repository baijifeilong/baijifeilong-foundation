package io.github.baijifeilong.foundation.inner;

import io.github.baijifeilong.foundation.web.ApiResponseWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@RestController
public class BaijifeilongFoundationApplication implements ApiResponseWrapper {

    public static void main(String[] args) {
        SpringApplication.run(BaijifeilongFoundationApplication.class, args);
    }

    @RequestMapping("/")
    public Object index() {
        if (ThreadLocalRandom.current().nextBoolean()) {
            throw new RuntimeException("没办法");
        }
        return successOf("OK");
    }
}

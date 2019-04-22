package io.github.baijifeilong.foundation.inner;

import io.github.baijifeilong.foundation.cache.CacheHelper;
import io.github.baijifeilong.foundation.web.ApiResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
public class BaijifeilongFoundationApplication implements ApiResponseWrapper {

    @Autowired
    private CacheHelper cacheHelper;

    public static void main(String[] args) {
        SpringApplication.run(BaijifeilongFoundationApplication.class, args);
    }

    @RequestMapping("/")
    public Object index() {
        System.out.println(cacheHelper);
        Point nothing = cacheHelper.takeOrPutWithTtl("nothing", () -> new Point(1, 1), 1, TimeUnit.MINUTES);
        if (ThreadLocalRandom.current().nextBoolean()) {
            throw new RuntimeException("没办法");
        }
        return successOf(nothing);
    }
}

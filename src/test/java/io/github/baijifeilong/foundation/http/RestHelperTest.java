package io.github.baijifeilong.foundation.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by BaiJiFeiLong@gmail.com at 2019-04-19 11:11
 */
public class RestHelperTest {

    private RestHelper restHelper = new RestHelper();

    @SneakyThrows
    private void dumpIt(Object object) {
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object));
    }

    @Test
    public void doGet() {
        JsonNode jsonNode = restHelper.doGet("https://httpbin.org/get");
        dumpIt(jsonNode);
        assertThat(jsonNode).isNotNull();
        assertThat(jsonNode.get("url").asText()).isEqualTo("https://httpbin.org/get");
    }

    @Test(expected = IOException.class)
    public void doGetOnHtmlPage() {
        restHelper.doGet("https://www.baidu.com");
    }

    @Test
    public void doPost() {
        JsonNode jsonNode = restHelper.doPost("https://httpbin.org/post", new HashMap<String, String>() {{
            put("hello", "world");
        }});
        dumpIt(jsonNode);
        assertThat(jsonNode).isNotNull();
    }
}
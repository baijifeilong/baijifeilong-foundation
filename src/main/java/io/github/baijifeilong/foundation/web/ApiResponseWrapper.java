package io.github.baijifeilong.foundation.web;

import io.github.baijifeilong.standard.api.domain.ApiContextPage;
import io.github.baijifeilong.standard.api.domain.ApiFailure;
import io.github.baijifeilong.standard.api.domain.ApiPage;
import io.github.baijifeilong.standard.api.domain.ApiSuccess;
import io.github.baijifeilong.standard.exception.BizException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by BaiJiFeiLong@gmail.com at 2019-04-18 21:34
 */
@SuppressWarnings("unused")
public interface ApiResponseWrapper {

    default <T> ApiSuccess<T> successOf(T t) {
        return ApiSuccess.of(t);
    }

    default <T> ApiSuccess<ApiPage<T>> successOfPage(Page<T> t) {
        return ApiSuccess.ofPage(t);
    }

    default <T> ApiSuccess<ApiContextPage<T>> successOfContextPage(List<T> items, Object previousIndex, Object nextIndex) {
        return ApiSuccess.ofContextPage(items, previousIndex, nextIndex);
    }

    default <T extends Throwable> ApiFailure failureOf(T t) {
        return ApiFailure.of(t);
    }

    default <T extends BizException> ApiFailure failureOf(T t) {
        return ApiFailure.of(t);
    }

    default ApiFailure failureOf(int code, String message) {
        return ApiFailure.of(code, message);
    }

    default ApiFailure failureOf(int code) {
        return ApiFailure.of(code);
    }

    default ApiFailure failureOf(String message) {
        return ApiFailure.of(message);
    }
}

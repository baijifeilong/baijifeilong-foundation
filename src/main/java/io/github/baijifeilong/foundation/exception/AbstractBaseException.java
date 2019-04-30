package io.github.baijifeilong.foundation.exception;

import io.github.baijifeilong.standard.exception.AbstractBizException;

/**
 * Created by BaiJiFeiLong@gmail.com at 2019-04-19 09:52
 */
abstract public class AbstractBaseException extends AbstractBizException {
    @Override
    public int getCode() {
        return 110000;
    }

    @Override
    protected String getDefaultMessage() {
        return "未知错误";
    }

    public AbstractBaseException(Throwable throwable, Object... args) {
        super(throwable, args);
    }

    public AbstractBaseException(Object... args) {
        super(args);
    }
}


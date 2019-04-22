# BaijifeilongFoundation

白季飞龙的Java开发基础框架

## 概述

框架目前主要提供以下功能:

- 统一接口响应，包括成功响应与失败响应
- 统一异常处理，内置全局异常处理器
- 基于OkHttp的REST客户端封装
- 基于Redis的缓存服务封装

参考:

- 框架使用的接口规范 [BaijifeilongStandard](https://github.com/baijifeilong/baijifeilong-standard)
- 框架使用示例 [BaijifeilongFoundationExample](https://github.com/baijifeilong/baijifeilong-foundation-example)

## 应用到项目

POM示例：

```xml
<project>
    <dependencies>
        <dependency>
            <groupId>io.github.baijifeilong</groupId>
            <artifactId>baijifeilong-foundation</artifactId>
            <version>1.2.1.RELEASE</version>
        </dependency>
    </dependencies>

    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
</project>
```

Properties示例(默认可不填写任何配置)

```yaml
baijifeilong:
  foundation:
    web:
      default-exception-code: 55555
      not-found-exception-code: 44444
      default-exception-message-template: '服务器开小差了: %s'
      not-found-exception-message-template: '请求的接口不存在: %s'
```

## 详解

### 1. 统一接口响应

如果是控制器，继承`io.github.baijifeilong.foundation.web.AbstractBaseController`即可

如果不是控制器，实现`io.github.baijifeilong.foundation.web.ApiResponseWrapper`即可

- `io.github.baijifeilong.foundation.web.ApiResponseWrapper.successOf` 成功响应
- `io.github.baijifeilong.foundation.web.ApiResponseWrapper.successOfPage` 成功响应带分页
- `successOfRelativePage` 成功响应带上下文分页
- `io.github.baijifeilong.foundation.web.ApiResponseWrapper.failureOf` 失败响应

### 2. 统一异常处理

通过注册全局异常处理器`io.github.baijifeilong.foundation.web.GlobalExceptionHandler`，对系统中的所有异常进行了统一处理。

在Controller层或Service层直接抛出业务异常或任何`Throwable`对象即可

所有业务异常的基类是`io.github.baijifeilong.foundation.exception.AbstractBaseException`，业务系统中的所有异常都应该继承此类

可能需要覆写的方法有:

- 两个有参构造函数(需要通过构造函数传递异常信息) `AbstractBaseException(java.lang.Throwable, java.lang.Object...)` 和 `AbstractBaseException.AbstractBaseException(java.lang.Object...)`。
- `AbstractBizException.getTemplate` 自定义消息模板
- `AbstractBaseException.getDefaultMessage` 默认消息(构造函数不传参时使用此默认异常消息)
- `AbstractBaseException.getCode` 整数异常代码

所有异常类通过`io.github.baijifeilong.standard.exception.IBizException`接口，暴露`code`与`message`两个字段

对于404异常，BaijifeilongFoundation通过自动载入以下配置，输出JSON格式错误信息，覆盖了Spring的默认行为:

```yaml
spring:
  resources:
    add-mappings: false
  mvc:
    throw-exception-if-no-handler-found: true
```

### 3. 基于OkHTTP的REST客户端封装

`io.github.baijifeilong.foundation.http.RestHelper`封装了OkHttp，并将接口响应用Jackson解析为`com.fasterxml.jackson.databind.JsonNode`

常用的方法:

- `io.github.baijifeilong.foundation.http.RestHelper.doGet(java.lang.String, java.util.Map<java.lang.String,java.lang.String>)`
- `io.github.baijifeilong.foundation.http.RestHelper.doPost`
- `io.github.baijifeilong.foundation.http.RestHelper.doPut`
- `io.github.baijifeilong.foundation.http.RestHelper.doDelete`

### 4. 基于Redis的缓存服务封装

`io.github.baijifeilong.foundation.cache.CacheHelper`通过`org.springframework.data.redis.core.RedisTemplate`，封装了Redis缓存功能，实现了对象的序列化与反序列化

常用的方法:

- `io.github.baijifeilong.foundation.cache.CacheHelper.take` 取缓存
- `io.github.baijifeilong.foundation.cache.CacheHelper.put` 写缓存
- `io.github.baijifeilong.foundation.cache.CacheHelper.takeOrPut` 取不到则写
- `io.github.baijifeilong.foundation.cache.CacheHelper.takeOrPutWithTtl` 取不到则写(带TTL)

## 协议

LGPL-3.0

package com.zh.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author zh
 * @date 2022/4/12 13:41
 * @description:说明 公共返回对象 枚举类
 */
@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {

    //通用枚举
    SUCCESS(200,"success"),
    ERROR(500,"服务异常"),

    //登录枚举
    LOGIN_ERROR(501,"登录名或密码不正确"),
    MOBILE_ERROR(502,"手机号码格式不正确"),

    //参数校验异常
    BIND_ERROR(503,"参数校验异常")

    //TODO 添加其他枚举


    ;

    private final Integer code;
    private final String message;

}

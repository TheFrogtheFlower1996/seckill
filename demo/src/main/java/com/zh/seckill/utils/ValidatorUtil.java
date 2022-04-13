package com.zh.seckill.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zh
 * @date 2022/4/12 15:22
 * @description:说明 手机号码校验
 */
public class ValidatorUtil {

    //正则表达式校验格式
    private final static Pattern mobile_pattern = Pattern.compile("[1]([3-9])[0-9]{9}$");

    //校验方法
    public static boolean isMobile(String mobile){

        if (StringUtils.isEmpty(mobile)){
            return false;
        }
        Matcher matcher = mobile_pattern.matcher(mobile);
        return matcher.matches();
    }
}

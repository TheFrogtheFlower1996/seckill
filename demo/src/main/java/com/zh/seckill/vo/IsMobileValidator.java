package com.zh.seckill.vo;

import com.zh.seckill.utils.ValidatorUtil;
import com.zh.seckill.validator.IsMobile;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author zh
 * @date 2022/4/12 16:14
 * @description:说明 手机号码验证规则
 *
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {

    //初始化参数 required 为 false
    private boolean required = false;

    //重写初始化方法 接收参数
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    //重写验证方法
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (required){ //必填
            return ValidatorUtil.isMobile(value);
        }else { //非必填
            if (StringUtils.isEmpty(value)){
                return true;
            }else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}

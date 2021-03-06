package com.zh.seckill.exception;

import com.zh.seckill.vo.RespBean;
import com.zh.seckill.vo.RespBeanEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author zh
 * @date 2022/4/13 14:57
 * @description:说明 全局异常处理类
 * 定义 @ExceptionHandler 注解，声明拦截哪种异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) //申明捕获那种异常类
    public RespBean ExceptionHandler(Exception e){

        System.out.println("统一返回异常信息：" + e);

        //具体异常分析
        if (e instanceof GlobalException){
            GlobalException ge = (GlobalException) e;
            return RespBean.error(ge.getRespBeanEnum());
        }else if (e instanceof BindException){
            BindException be = (BindException) e;
            RespBean respBean = RespBean.error(RespBeanEnum.BIND_ERROR);
            respBean.setMessage("参数校验异常："+be.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return respBean;
        }
        return RespBean.error(RespBeanEnum.ERROR);
    }
}

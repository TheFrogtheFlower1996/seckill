package com.zh.seckill.vo;

import lombok.*;

/**
 * @author zh
 * @date 2022/4/12 13:40
 * @description:说明 公共返回对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {


    private long code;
    private String message;
    private Object obj;

    /**
     * 成功返回结果
     * */
    public static RespBean success(){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果 重载带Object
     * */
    public static RespBean success(Object obj){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(), RespBeanEnum.SUCCESS.getMessage(), obj);
    }

    /**
     * 失败返回结果
     * */
    public static RespBean error(RespBeanEnum beanenum){
        return new RespBean(beanenum.getCode(),beanenum.getMessage(),null);
    }

    /**
     * 失败返回结果 重载带Object
     * */
    public static RespBean error(RespBeanEnum beanenum,Object obj){
        return new RespBean(beanenum.getCode(),beanenum.getMessage(),obj);
    }



}

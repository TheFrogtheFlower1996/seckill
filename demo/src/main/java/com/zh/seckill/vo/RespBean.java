package com.zh.seckill.vo;

import lombok.*;

/**
 * @author zh
 * @date 2022/4/12 13:40
 * @description:说明 统一返回对象
 */
@Data //省略get set方法
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {


    private long code;//状态码
    private String message;//消息
    private Object obj;//对象

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

    //TODO 扩展


}

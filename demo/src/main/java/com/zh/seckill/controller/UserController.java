package com.zh.seckill.controller;


import com.zh.seckill.pojo.User;
import com.zh.seckill.vo.RespBean;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zh
 * @since 2022-04-12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 测试：用户信息
     * **/
    @RequestMapping("/info")
    public @ResponseBody RespBean info(User user){
        return RespBean.success(user);
    }

}

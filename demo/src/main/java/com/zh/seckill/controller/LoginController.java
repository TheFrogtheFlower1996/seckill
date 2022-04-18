package com.zh.seckill.controller;

import com.zh.seckill.service.IUserService;
import com.zh.seckill.vo.LoginVo;
import com.zh.seckill.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author zh
 * @date 2022/4/12 11:31
 * @description:说明
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private IUserService iUserService;

    /**
     * 请求转发到登录页面
     * */
    @RequestMapping("/forwardLogin")
    public String forwardLogin(){

        return "login";
    }

    /**
     * 登录功能
     * */
    @RequestMapping("/doLogin")
    public @ResponseBody RespBean doLogin(@Valid LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){

        log.info("{}",loginVo);
            return iUserService.doLogin(loginVo,request,response);
    }

}

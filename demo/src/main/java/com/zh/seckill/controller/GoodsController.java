package com.zh.seckill.controller;

import com.zh.seckill.pojo.User;
import com.zh.seckill.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author zh
 * @date 2022/4/15 15:46
 * @description:说明 跳转到商品列表页
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private IUserService iUserService;

    /**
     * 跳转到商品列表页
     * */
    @RequestMapping("/toList")
//    public String toList(HttpServletRequest request, HttpServletResponse response, Model model, @CookieValue(value = "userTicket",required=false) String ticket){
    public String toList(Model model,User user){

        //判断用户是否登录
/*        if (ObjectUtils.isEmpty(ticket)){
            return "login";
        }
        //User user = (User) session.getAttribute(ticket);

        User user = iUserService.getUserByCookie(ticket, request, response);
        System.out.println("查询到user信息："+user);
        if (user == null){
            return "login";
        }*/

        model.addAttribute("user",user);

        return "goodsList";
    }
}

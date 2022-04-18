package com.zh.seckill.controller;

import com.zh.seckill.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author zh
 * @date 2022/4/15 15:46
 * @description:说明 跳转到商品列表页
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    /**
     * 跳转到商品列表页
     * */
    @RequestMapping("/toList")
    public String toList(HttpSession session, Model model,@CookieValue(value = "userTicket",required=false) String ticket){

        //判断用户是否登录
        if (ObjectUtils.isEmpty(ticket)){
            return "login";
        }
        User user = (User) session.getAttribute(ticket);
        if (user == null){
            return "login";
        }
        model.addAttribute("user",user);

        return "goodsList";
    }
}

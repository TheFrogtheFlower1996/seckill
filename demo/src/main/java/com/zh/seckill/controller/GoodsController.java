package com.zh.seckill.controller;

import com.zh.seckill.pojo.User;
import com.zh.seckill.service.IGoodsService;
import com.zh.seckill.service.IUserService;
import com.zh.seckill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author zh
 * @date 2022/4/15 15:46
 * @description:说明 跳转到商品列表页
 */
@Controller
@RequestMapping("/goods")
@Slf4j
public class GoodsController {

    @Resource
    private IUserService iUserService;

    @Autowired
    private IGoodsService iGoodsService;

    /**
     * 跳转到商品列表页
     *
     * Windows优化前QPS: 2200
     * Linux优化前QPS: 650
     *
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
        model.addAttribute("goodsList",iGoodsService.getGoodsVoLists());

        return "goodsList";
    }

    /**
     * 跳转商品详情页
     *
     * */
    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(@PathVariable long goodsId,Model model,User user){
        model.addAttribute("user",user);
        GoodsVo goodsVo = iGoodsService.findGoodsVoByGoodsId(goodsId);
        Date startDate = goodsVo.getStartDate();
        Date endDate = goodsVo.getEndDate();
        Date nowDate = new Date();

        log.info("nowDate："+nowDate);
        log.info("startDate："+startDate);
        log.info("endDate："+endDate);

        //秒杀状态
        int secKillStatus = 0;
        //秒杀倒计时
        int remainSeconds = 0;

        if (nowDate.before(startDate)){
            log.info("秒杀还未开始");
            /**
             * startDate.getTime() 毫秒
             * /1000 除以1000，返回秒
             * */
            remainSeconds = ((int) ((startDate.getTime() - nowDate.getTime()) / 1000));

        }else if (nowDate.after(endDate)){
            log.info("秒杀结束");
            secKillStatus = 2;
            remainSeconds = -1;
        }else {
            log.info("正在秒杀");
            secKillStatus = 1;
            remainSeconds = 0;
        }

        model.addAttribute("secKillStatus",secKillStatus);
        model.addAttribute("remainSeconds",remainSeconds);
        model.addAttribute("goods",goodsVo);
        return "goodsDetail";
    }

}

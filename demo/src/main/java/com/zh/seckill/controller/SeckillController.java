package com.zh.seckill.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zh.seckill.pojo.Order;
import com.zh.seckill.pojo.SeckillOrder;
import com.zh.seckill.pojo.User;
import com.zh.seckill.service.IGoodsService;
import com.zh.seckill.service.IOrderService;
import com.zh.seckill.service.ISeckillOrderService;
import com.zh.seckill.vo.GoodsVo;
import com.zh.seckill.vo.RespBean;
import com.zh.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zh
 * @date 2022/4/24 13:57
 * @description:说明 秒杀接口
 *
 * win优化前QPS: 700
 * Linux优化前QPS: 600
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private ISeckillOrderService seckillOrderService;

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/doSeckill")
    public String doSeckill(Model model, User user,long goodsId){
        if (user == null){
            return "login";
        }
        model.addAttribute("user",user);
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);

        //判断库存
        if (goodsVo.getStockCount() < 1){

            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
            return "secKillFail";
        }
        //判断是否重复抢购
        Long userId = seckillOrderService.getByUserId(user.getId());

        SeckillOrder seckillOrder = seckillOrderService.getOne(new QueryWrapper<SeckillOrder>()
                .eq("user_id", user.getId())
                .eq("goods_id", goodsId));

        if (seckillOrder != null){
            model.addAttribute("errmsg",RespBeanEnum.REPEATE_ERROR.getMessage());
            return "secKillFail";
        }

        //秒杀下订单
        Order order = orderService.seckill(user,goodsVo);
        model.addAttribute("order",order);
        model.addAttribute("goods",goodsVo);
        return "orderDetail";
    }
}

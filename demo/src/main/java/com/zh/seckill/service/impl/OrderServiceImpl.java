package com.zh.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.zh.seckill.pojo.Order;
import com.zh.seckill.mapper.OrderMapper;
import com.zh.seckill.pojo.SeckillGoods;
import com.zh.seckill.pojo.SeckillOrder;
import com.zh.seckill.pojo.User;
import com.zh.seckill.service.IGoodsService;
import com.zh.seckill.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.seckill.service.ISeckillGoodsService;
import com.zh.seckill.service.ISeckillOrderService;
import com.zh.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zh
 * @since 2022-04-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ISeckillGoodsService seckillGoodsService;

    @Autowired
    private ISeckillOrderService seckillOrderService;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order seckill(User user, GoodsVo goodsVo) {

        //秒杀商品减库存
        SeckillGoods goodsServiceOne = seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>()
                .eq("goods_id", goodsVo.getId()));
        goodsServiceOne.setStockCount(goodsServiceOne.getStockCount() - 1);
        seckillGoodsService.updateById(goodsServiceOne);

        //生成订单
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goodsVo.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goodsVo.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(goodsVo.getGoodsPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);

        //生成秒杀订单
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setGoodsId(goodsVo.getId());
        seckillOrderService.save(seckillOrder);

        return order;
    }
}

package com.zh.seckill.service;

import com.zh.seckill.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.seckill.pojo.User;
import com.zh.seckill.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zh
 * @since 2022-04-21
 */
public interface IOrderService extends IService<Order> {

    Order seckill(User user, GoodsVo goodsVo);
}

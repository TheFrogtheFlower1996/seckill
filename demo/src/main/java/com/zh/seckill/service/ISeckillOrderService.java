package com.zh.seckill.service;

import com.zh.seckill.pojo.SeckillOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zh
 * @since 2022-04-21
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {

    Long getByUserId(Long id);
}

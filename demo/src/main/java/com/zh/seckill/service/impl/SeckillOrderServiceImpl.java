package com.zh.seckill.service.impl;

import com.zh.seckill.pojo.SeckillOrder;
import com.zh.seckill.mapper.SeckillOrderMapper;
import com.zh.seckill.service.ISeckillOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zh
 * @since 2022-04-21
 */
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements ISeckillOrderService {

    @Autowired
    private SeckillOrderMapper seckillOrderMapper;


    @Override
    public Long getByUserId(Long id) {
        return seckillOrderMapper.getByUserId(id);
    }
}

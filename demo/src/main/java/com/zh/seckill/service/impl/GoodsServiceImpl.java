package com.zh.seckill.service.impl;

import com.zh.seckill.pojo.Goods;
import com.zh.seckill.mapper.GoodsMapper;
import com.zh.seckill.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zh
 * @since 2022-04-21
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    /**
     * 获取商品列表
     * */
    @Override
    public List<GoodsVo> getGoodsVoLists() {
        return goodsMapper.findGoodsVoLists();

    }
}

package com.zh.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.seckill.pojo.Goods;
import com.zh.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zh
 * @since 2022-04-21
 */
public interface IGoodsService extends IService<Goods> {

    /**
     * 获取商品列表
     * */
    List<GoodsVo> getGoodsVoLists();

    GoodsVo findGoodsVoByGoodsId(long goodsId);
}

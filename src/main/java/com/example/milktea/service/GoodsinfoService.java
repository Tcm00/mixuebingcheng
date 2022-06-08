package com.example.milktea.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.milktea.pojo.DO.GoodsInfoDO;
import com.example.milktea.pojo.Goodsinfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.milktea.pojo.VO.GoodsInfoVO;
import com.example.milktea.pojo.entity.ResultBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-23
 */
public interface GoodsinfoService extends IService<Goodsinfo> {

    ResultBody getMenu(Page<GoodsInfoVO> page);

    ResultBody addGoods(GoodsInfoDO goodsInfoDO);

    ResultBody updateGoods(GoodsInfoDO goodsInfoDO);

    ResultBody deleteGoods(int goodsId);
}

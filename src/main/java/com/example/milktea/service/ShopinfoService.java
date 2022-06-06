package com.example.milktea.service;

import com.example.milktea.pojo.Shopinfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.milktea.pojo.VO.ShopInfoVO;
import com.example.milktea.pojo.entity.ResultBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-23
 */
public interface ShopinfoService extends IService<Shopinfo> {

    ShopInfoVO getShopInfo(String id);
}

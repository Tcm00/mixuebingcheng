package com.example.milktea.service.impl;

import com.example.milktea.pojo.Shopinfo;
import com.example.milktea.mapper.ShopinfoMapper;
import com.example.milktea.pojo.VO.ShopInfoVO;
import com.example.milktea.pojo.entity.ResultBody;
import com.example.milktea.service.ShopinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-23
 */
@Service
public class ShopinfoServiceImpl extends ServiceImpl<ShopinfoMapper, Shopinfo> implements ShopinfoService {

    @Override
    public ShopInfoVO getShopInfo(String id) {
        ShopInfoVO shopInfo = baseMapper.getShopInfo(Integer.parseInt(id));
        return shopInfo;
    }
}

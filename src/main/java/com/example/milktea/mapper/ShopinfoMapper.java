package com.example.milktea.mapper;

import com.example.milktea.pojo.Shopinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.milktea.pojo.VO.ShopInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-23
 */
@Repository
@Mapper
public interface ShopinfoMapper extends BaseMapper<Shopinfo> {

    ShopInfoVO getShopInfo(int parseInt);
}

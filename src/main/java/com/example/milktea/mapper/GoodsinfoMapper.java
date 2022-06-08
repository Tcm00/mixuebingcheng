package com.example.milktea.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.milktea.pojo.Goodsinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.milktea.pojo.VO.GoodsInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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
public interface GoodsinfoMapper extends BaseMapper<Goodsinfo> {

    List<GoodsInfoVO> getMenu();

    void updateByIda(Date deleteTime, int goodsId);
}

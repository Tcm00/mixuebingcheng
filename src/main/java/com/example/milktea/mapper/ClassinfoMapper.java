package com.example.milktea.mapper;

import com.example.milktea.pojo.Classinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-26
 */
@Mapper
@Repository
public interface ClassinfoMapper extends BaseMapper<Classinfo> {

    int addTypeA(int goodsId);

    int addTypeB(Integer goodsId);

    int addTypeC(Integer goodsId);
}

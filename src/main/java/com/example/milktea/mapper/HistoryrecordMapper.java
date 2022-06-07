package com.example.milktea.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.milktea.pojo.Historyrecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.milktea.pojo.VO.GoodsInfoVO;
import com.example.milktea.pojo.VO.HistoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
public interface HistoryrecordMapper extends BaseMapper<Historyrecord> {

    List<HistoryVO> getHistory(Integer userId, Integer status);

    String selectUrl(String goodsId);

    List<HistoryVO> getHistoryNum(Integer userId);
}

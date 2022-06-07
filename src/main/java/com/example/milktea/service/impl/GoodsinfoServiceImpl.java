package com.example.milktea.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.milktea.pojo.Goodsinfo;
import com.example.milktea.mapper.GoodsinfoMapper;
import com.example.milktea.pojo.VO.GoodsInfoVO;
import com.example.milktea.pojo.entity.ResultBody;
import com.example.milktea.service.GoodsinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-23
 */
@Service
public class GoodsinfoServiceImpl extends ServiceImpl<GoodsinfoMapper, Goodsinfo> implements GoodsinfoService {

    @Override
    public ResultBody getMenu(Page<GoodsInfoVO> page) {
        List<GoodsInfoVO> menu = baseMapper.getMenu();
        return ResultBody.ok().data("menu",menu);
    }


}

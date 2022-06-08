package com.example.milktea.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.milktea.mapper.*;
import com.example.milktea.pojo.Attributea;
import com.example.milktea.pojo.Classinfo;
import com.example.milktea.pojo.DO.GoodsInfoDO;
import com.example.milktea.pojo.Goodsinfo;
import com.example.milktea.pojo.VO.GoodsInfoVO;
import com.example.milktea.pojo.entity.ResultBody;
import com.example.milktea.service.GoodsinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ClassinfoMapper classinfoMapper;

    @Override
    public ResultBody getMenu(Page<GoodsInfoVO> page) {
        List<GoodsInfoVO> menu = baseMapper.getMenu();
        return ResultBody.ok().data("menu",menu);
    }

    @Override
    @Transactional
    public ResultBody addGoods(GoodsInfoDO goodsInfoDO) {
        QueryWrapper<Classinfo> classinfoQueryWrapper = new QueryWrapper<>();
        classinfoQueryWrapper.eq("classInfo",goodsInfoDO.getClassInfo());
        Classinfo one = classinfoMapper.selectOne(classinfoQueryWrapper);
        System.out.println("========>>>"+one);
        if (one==null){
            classinfoMapper.insert(new Classinfo(0,goodsInfoDO.getClassInfo()));
            classinfoQueryWrapper.orderByDesc("id");
            one = classinfoMapper.selectOne(classinfoQueryWrapper);
        }

        System.out.println("--------------->"+goodsInfoDO);
        Goodsinfo goodsinfo = new Goodsinfo();
        goodsinfo.setId(0);
        goodsinfo.setGoodsname(goodsInfoDO.getGoodsname());
        goodsinfo.setGoodsprice(goodsInfoDO.getGoodsprice());
        goodsinfo.setDescribes(goodsInfoDO.getDescribe());
        goodsinfo.setClassId(one.getId());
        goodsinfo.setShopId(goodsInfoDO.getShopId());
        baseMapper.insert(goodsinfo);

        QueryWrapper<Goodsinfo> wrapper = new QueryWrapper<>();
        wrapper.eq("goodsName",goodsInfoDO.getGoodsname());
        wrapper.orderByDesc("id");
        wrapper.last("limit 1");
        Goodsinfo selectOne = baseMapper.selectOne(wrapper);

        System.out.println("========>"+selectOne);
        classinfoMapper.addTypeA(selectOne.getId());
        classinfoMapper.addTypeB(selectOne.getId());
        classinfoMapper.addTypeC(selectOne.getId());

        return ResultBody.ok().message("商品添加成功");
    }

    @Override
    @Transactional
    public ResultBody updateGoods(GoodsInfoDO goodsInfoDO) {

        Goodsinfo goodsinfo = new Goodsinfo();
        goodsinfo.setId(goodsInfoDO.getId());
        goodsinfo.setGoodsname(goodsInfoDO.getGoodsname());
        goodsinfo.setGoodsprice(goodsInfoDO.getGoodsprice());
        goodsinfo.setDescribes(goodsInfoDO.getDescribe());
        goodsinfo.setShopId(goodsInfoDO.getShopId());

        /**
         * 如果没有新命名的类型，则修改类型名字，有则修改classInfoId指向原有classinfo的ID
         */
        QueryWrapper<Classinfo> wrapper = new QueryWrapper<>();
        wrapper.eq("classInfo",goodsInfoDO.getClassInfo());
        Classinfo classinfo = classinfoMapper.selectOne(wrapper);
        System.out.println("======>"+classinfo);
        if (classinfo==null){
            //没有就新建一个类型信息
            classinfoMapper.insert(new Classinfo(0,goodsInfoDO.getClassInfo()));
            Classinfo one = classinfoMapper.selectOne(wrapper);
            goodsinfo.setClassId(one.getId());
        }else {
            goodsinfo.setClassId(classinfo.getId());
        }
        baseMapper.updateById(goodsinfo);
        return ResultBody.ok().message("修改商品信息成功");
    }

    @Override
    public ResultBody deleteGoods(int goodsId) {
        Goodsinfo goodsinfo1 = baseMapper.selectById(goodsId);
        if (goodsinfo1==null){
            return ResultBody.error().message("未找到该商品");
        }

        baseMapper.updateByIda(new Date(),goodsId);
        return ResultBody.ok().message("删除商品成功");
    }
}

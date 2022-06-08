package com.example.milktea.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.milktea.pojo.DO.GoodsInfoDO;
import com.example.milktea.pojo.Goodsinfo;
import com.example.milktea.pojo.Userinfo;
import com.example.milktea.pojo.VO.GoodsInfoVO;
import com.example.milktea.pojo.entity.ResultBody;
import com.example.milktea.service.GoodsinfoService;
import com.example.milktea.utils.LocalUser;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-23
 */
@Api(tags = "商品信息管理")
@RestController
@RequestMapping( "/goodsinfo")
public class GoodsinfoController {

    @Autowired
    private GoodsinfoService goodsinfoService;


    @ApiOperation(value = "查询所有商品",response =GoodsInfoVO.class)
    @RequestMapping("/getGoodsInfo")
    public ResultBody getGoodsInfo(@RequestParam(defaultValue = "1") @NotEmpty Long pageNumber,
                                   @RequestParam(defaultValue = "10") @NotEmpty Long size ){
        Page<GoodsInfoVO> Page = new Page<>(pageNumber, size);
        return goodsinfoService.getMenu(Page);
    }


    @ApiOperation(value = "增加商品")
    @RequestMapping("/addGoods")
    public ResultBody addGoods(@RequestBody @ApiParam GoodsInfoDO goodsInfoDO){
        Userinfo userinfo = LocalUser.USER.get();
        System.out.println(userinfo);
        return goodsinfoService.addGoods(goodsInfoDO);
    }

    @ApiOperation(value = "修改商品信息")
    @RequestMapping("/updateGoods")
    public ResultBody updateGoods(@RequestBody @ApiParam GoodsInfoDO goodsInfoDO){
        return goodsinfoService.updateGoods(goodsInfoDO);
    }

    @ApiOperation(value = "删除商品")
    @ApiImplicitParam(name = "goodsId",value = "商品ID",example = "18")
    @RequestMapping("/deleteGoods")
    public ResultBody deleteGoods(@RequestParam int goodsId){
        return goodsinfoService.deleteGoods(goodsId);
    }

}


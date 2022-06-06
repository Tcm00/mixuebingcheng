package com.example.milktea.controller;


import com.example.milktea.pojo.VO.ShopInfoVO;
import com.example.milktea.pojo.entity.ResultBody;
import com.example.milktea.service.ShopinfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-23
 */

@Api(tags = "店铺信息管理")
@RestController
@RequestMapping("/shopInfo")
public class ShopinfoController {

    @Autowired
    private ShopinfoService shopinfoService;


    @ApiOperation(value = "查看店铺信息")
    @ApiImplicitParam(name = "id",value = "店铺id",dataType = "int",example = "1")
    @RequestMapping("/getShopInfo")
    public ResultBody getShopInfo(@RequestParam(defaultValue = "1") String id) {

        ShopInfoVO shopInfo = shopinfoService.getShopInfo(id);
        System.out.println(shopInfo);
        return ResultBody.ok().data("shopInfo", shopInfo);
    }
}


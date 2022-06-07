package com.example.milktea.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.milktea.pojo.Historyrecord;
import com.example.milktea.pojo.Userinfo;
import com.example.milktea.pojo.VO.GoodsInfoVO;
import com.example.milktea.pojo.VO.HistoryVO;
import com.example.milktea.pojo.entity.ResultBody;
import com.example.milktea.service.HistoryrecordService;
import com.example.milktea.utils.LocalUser;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-26
 */
@Api(tags = "历史订单and充值")
@RestController
@RequestMapping("/historyrecord")
public class HistoryrecordController {

    @Autowired
    private HistoryrecordService historyrecordService;

    @ApiOperation(value = "零钱充值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "money",value = "充值金额",example = "10"),
            @ApiImplicitParam(name = "userId",value = "用户Id",example = "0，不传默认冲自己"),
    })
    @RequestMapping("/addMoney")
    public ResultBody addMoney(@RequestParam Double money,
                               @RequestParam(defaultValue = "0") int userId){
        Userinfo userinfo = LocalUser.USER.get();
        System.out.println("===>"+userinfo);
        if (userinfo==null){
            return ResultBody.error().message("请传token码");
        }
        return historyrecordService.addMoney(userinfo,money);
    }

//    [{
//        "shopId":1,
//                "goodsId":1,
//                "num":2
//    },{
//        "shopId":1,
//                "goodsId":2,
//                "num":2
//    }]
    @ApiOperation(value = "提交商品订单",response = Historyrecord.class)
    @RequestMapping("/orderTea")
    public ResultBody orderTea(@RequestBody @ApiParam List<Historyrecord> historyrecords){
        Userinfo userinfo = LocalUser.USER.get();
        return historyrecordService.addOrder(historyrecords,userinfo);
    }

    @ApiOperation(value = "查询历史订单记录",response = HistoryVO.class)
    @ApiImplicitParam(name = "status",value = "订单状态(0:未使用 ; 1:已使用)",example = "0")
    @RequestMapping("/getHistory")
    public ResultBody getHistory(@RequestParam(defaultValue = "1") @NotEmpty Integer pageNumber,
                                 @RequestParam(defaultValue = "10") @NotEmpty Integer size,
                                 @RequestParam int status){
        Userinfo userinfo = LocalUser.USER.get();
        return historyrecordService.getHistory(pageNumber,size,userinfo.getId(),status);
    }

    @ApiOperation(value = "查询历史充值记录",response = HistoryVO.class)
    @ApiImplicitParam(name = "userId",value = "被查用户id",example = "5")
    @RequestMapping("/getRecharge")
    public ResultBody recharge(@RequestParam(defaultValue = "1") @NotEmpty Integer pageNumber,
                               @RequestParam(defaultValue = "10") @NotEmpty Integer size,
                               @RequestParam(defaultValue = "0") int userId){
        Userinfo userinfo = LocalUser.USER.get();
        if (userId==0){
            userId = userinfo.getId();
        }
        Page<Historyrecord> page = new Page<>(pageNumber, size);
        return historyrecordService.recharge(page,userId);
    }
}


package com.example.milktea.pojo.VO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.milktea.pojo.Goodsinfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author 小明
 * @date 2022/5/30
 * @description
 */
@JsonIgnoreProperties(value = "handler")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryVO {
    @TableId(value = "recordId")
    private int recordId;

    @ApiModelProperty(name = "shopName",value = "店铺名字")
    private String shopName;

    @ApiModelProperty(name = "shopAddress",value = "店铺地址")
    private String shopAddress;

    @ApiModelProperty(name = "goodsId",value = "商品ID")
    private String goodsId;

    @ApiModelProperty(name = "userId",value = "用户ID(不用传)")
    private Integer userId;

    @ApiModelProperty(name = "details",value = "可选参数",example = "大/糖/冰")
    private String  details;

    @ApiModelProperty(name = "money",value = "单杯价格")
    private Double money;

    @ApiModelProperty(name = "num",value = "购买数量",example = "1")
    private Integer num;

    @ApiModelProperty(name = "orderSum",value = "该订单总价格")
    private Double orderSum;

    @ApiModelProperty(name = "type",value = "订单类型（1;充值 ; 2:消费）")
    private Integer type;

    @ApiModelProperty(name = "orderId",value = "订单ID")
    @TableField("orderId")
    private String orderid;

    @ApiModelProperty(name = "status",value = "卡卷状态(0:未使用  ; 1:已使用)")
    private Integer status;

    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "updateTime",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "deleteTime",fill = FieldFill.UPDATE)
    private Date deleteTime;

    @ApiModelProperty(name = "goodsInfo",value = "商品信息")
    private List<GoodsVO> goodsinfos;
}

package com.example.milktea.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;

import com.example.milktea.utils.CustomerDoubleSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-26
 */
@ApiModel(value = "historyRecord",description = "历史订单")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("historyRecord")
public class Historyrecord extends Model<Historyrecord> {

    private static final long serialVersionUID = 1L;


    @TableId(value = "recordId", type = IdType.AUTO)
    private int recordId;

    @ApiModelProperty(name = "shopId",value = "店铺ID",example = "1")
    private Integer shopId;

    @ApiModelProperty(name = "goodsId",value = "商品ID",example = "1")
    private Integer goodsId;

    @ApiModelProperty(name = "userId",value = "用户ID(不用传)")
      private Integer userId;

    @ApiModelProperty(name = "details",value = "可选参数",example = "大/糖/冰")
    private String  details;

    @ApiModelProperty(name = "money",value = "单杯价格")
    private Double money;

    @ApiModelProperty(name = "num",value = "购买数量",example = "1")
    private Integer num;

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
}

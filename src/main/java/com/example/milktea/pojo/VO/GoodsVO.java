package com.example.milktea.pojo.VO;

import com.example.milktea.utils.CustomerDoubleSerialize;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

/**
 * @author 小明
 * @date 2022/5/30
 * @description
 */
@JsonIgnoreProperties(value = "handler")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsVO {
    @ApiModelProperty(name = "goodsId",value = "商品ID")
    private String goodsId;

    @ApiModelProperty(name = "goodsName",value = "商品名字")
    private String goodsName;
    @ApiModelProperty(name = "details",value = "订单描述")
    private String details;

    @ApiModelProperty(name = "goodsUrl",value = "商品图片")
    private String goodsUrl;

    @JsonSerialize(using = CustomerDoubleSerialize.class)
    @ApiModelProperty(name = "goodsPrice",value = "商品价格")
    private Double goodsPrice;

    @ApiModelProperty(name = "num",value = "该商品购买数量")
    private int num;

    @JsonSerialize(using = CustomerDoubleSerialize.class)
    @ApiModelProperty(name = "sum",value = "价格 * 数量")
    private Double sum;

    @ApiModelProperty(name = "orderId",value = "订单编号")
    private String orderId;
}

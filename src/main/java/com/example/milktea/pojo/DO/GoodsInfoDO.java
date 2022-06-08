package com.example.milktea.pojo.DO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.milktea.utils.CustomerDoubleSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 小明
 * @date 2022/6/6
 * @description
 */
@ApiModel(value = "GoodsInfoDO",description = "添加商品信息表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfoDO {

    @ApiModelProperty(name = "id",value = "商品ID,(修改用）",example = "11")
    @TableField("id")
    private int id;

    @ApiModelProperty(name = "goodsName",value = "商品名字",example = "商品名字")
    @TableField("goodsName")
    private String goodsname;

    @ApiModelProperty(name = "goodsPrice",value = "商品价格",example = "8.80")
    @JsonSerialize(using = CustomerDoubleSerialize.class)
    @TableField("goodsPrice")
    private Double goodsprice;

    @ApiModelProperty(name = "describe",value = "商品描述",example = "商品描述")
    private String describe;

    @ApiModelProperty(name = "classInfo",value = "左边栏商品类型",example = "当季新品")
    private String classInfo;


    @ApiModelProperty(name = "shopId",value = "店铺唯一ID",example = "1")
    private Integer shopId;

//    @ApiModelProperty(name = "shopname",value = "商品名字")
//    @TableField("shopName")
//    private String shopname;

//    @ApiModelProperty(name = "address",value = "店铺地址")
//    private String address;

    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "updateTime",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "deleteTime",fill = FieldFill.UPDATE)
    private Date deleteTime;
//    {
//        "classInfo":"测试用例",
//        "goodsname":"测试用例",
//        "describe":"测试用例",
//        "goodsprice":"8.8",
//        "shopId":"1"
//    }

//    @ApiModelProperty(name = "url",value = "商品图片")
//    private List<String> url;
//    @ApiModelProperty(name = "typea",value = "商品属性_加料")
//    private List<String> typea;
//    @ApiModelProperty(name = "typeb",value = "商品属性_温度")
//    private List<String> typeb;
//    @ApiModelProperty(name = "typec",value = "商品属性_糖度")
//    private List<String> typec;
}


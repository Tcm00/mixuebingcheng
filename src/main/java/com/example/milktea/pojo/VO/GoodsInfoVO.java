package com.example.milktea.pojo.VO;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.milktea.utils.CustomerDoubleSerialize;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * @date 2022/5/24
 * @description
 */
@JsonIgnoreProperties(value = "handler")
@ApiModel("商品信息表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("商品名字")
    @TableField("goodsName")
    private String goodsname;

    @ApiModelProperty("商品价格")
    @JsonSerialize(using = CustomerDoubleSerialize.class)
    @TableField("goodsPrice")
    private Double goodsprice;

    @ApiModelProperty(name = "describe",value = "商品描述")
    private String describe;

    @ApiModelProperty(name = "classInfo",value = "左边栏商品类型",example = "当季新品")
    private String classInfo;


    @ApiModelProperty(name = "shopId",value = "店铺唯一ID")
    private Integer shopId;

    @ApiModelProperty(name = "shopname",value = "商品名字")
    @TableField("shopName")
    private String shopname;

    @ApiModelProperty(name = "address",value = "店铺地址")
    private String address;

    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "updateTime",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "deleteTime",fill = FieldFill.UPDATE)
    private Date deleteTime;

    @ApiModelProperty(name = "url",value = "商品图片")
    private List<String> url;
    @ApiModelProperty(name = "typea",value = "商品属性_加料")
    private List<String> typea;
    @ApiModelProperty(name = "typeb",value = "商品属性_温度")
    private List<String> typeb;
    @ApiModelProperty(name = "typec",value = "商品属性_糖度")
    private List<String> typec;
}

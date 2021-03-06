package com.example.milktea.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2022-05-23
 */
@ApiModel(value = "goodsInfo",description = "商品信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goodsinfo extends Model<Goodsinfo> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(name = "goodsName",value = "商品名字")
    @TableField("goodsName")
    private String goodsname;

    @ApiModelProperty(name = "goodsprice",value = "商品价格")
    @JsonSerialize(using = CustomerDoubleSerialize.class)
    @TableField("goodsPrice")
    private Double goodsprice;

    @ApiModelProperty(name = "describes",value = "商品描述")
    private String describes;

    private int classId;

    private int shopId;

//    @ApiModelProperty(name = "typea",value = "加料")
//    private String typea;
//
//    @ApiModelProperty(name = "typeb",value = "温度")
//    private String typeb;
//
//    @ApiModelProperty(name = "typec",value = "糖度")
//    private String typec;

    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "updateTime",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "deleteTime",fill = FieldFill.UPDATE)
    private Date deleteTime;

}

package com.example.milktea.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.example.milktea.utils.CustomerDoubleSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@ApiModel(value = "Userinfo",description = "用户信息")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Userinfo extends Model<Userinfo> {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(name = "phone",value = "手机号",example = "15223650215")
    private String phone;

    @ApiModelProperty(name = "password",value = "密码",example = "123456")
    private String password;

    @ApiModelProperty(name = "sex",value = "性别")
    private String sex;

    @ApiModelProperty(name = "birthday",value = "生日")
    private String birthday;

    @ApiModelProperty(name = "noun",value = "积分")
    private Integer noun;

    @ApiModelProperty(name = "balance",value = "余额")
    @JsonSerialize(using = CustomerDoubleSerialize.class)
    private Double balance;

    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "updateTime",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "deleteTime",fill = FieldFill.UPDATE)
    private Date deleteTime;
}

package com.example.milktea.pojo.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.milktea.pojo.Shopswipe;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 小明
 * @date 2022/5/23
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
public class ShopInfoVO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("shopName")
    private String shopname;

    private String address;

    private List<Shopswipe> shopswipes;
}

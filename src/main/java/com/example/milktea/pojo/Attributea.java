package com.example.milktea.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * <p>
 * 
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-26
 */
public class Attributea extends Model<Attributea>{

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("goosId")
    private Integer goosid;

    private String add;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoosid() {
        return goosid;
    }

    public void setGoosid(Integer goosid) {
        this.goosid = goosid;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Attributea{" +
        "id=" + id +
        ", goosid=" + goosid +
        ", add=" + add +
        "}";
    }

}

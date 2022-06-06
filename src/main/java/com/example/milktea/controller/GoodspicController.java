package com.example.milktea.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.milktea.pojo.Goodspic;
import com.example.milktea.pojo.Userinfo;
import com.example.milktea.pojo.entity.ResultBody;
import com.example.milktea.service.GoodspicService;
import com.example.milktea.utils.LocalUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-26
 */
@Api(tags = "商品展示照片")
@RestController
@RequestMapping("/goodspic")
public class
GoodspicController {

    @Autowired
    private GoodspicService goodspicService;


    @ApiOperation(value = "查看该商品的图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",value = "当前页码",example = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页容量",example = "10"),
            @ApiImplicitParam(name = "goodsId",value = "商品id",example = "1")
    })
    @RequestMapping("getList")
    public ResultBody getPic(@RequestParam(defaultValue = "1") @NotEmpty long pageNum,
                             @RequestParam(defaultValue = "10") @NotEmpty long pageSize,
                             @RequestParam int goodsId){
        Userinfo userinfo = LocalUser.USER.get();
        //管理员鉴权
//        if (!Objects.equals(userinfo.getPhone(), "15223650215")){
//            return ResultBody.error().message("没有权限");
//        }
        Page<Goodspic> page = new Page<>(pageNum, pageSize);
        return goodspicService.getList(page,goodsId);
    }

    @ApiOperation("上传新的商品图片")
    @ApiImplicitParam(name = "file",value = "文件")
    @RequestMapping("/upload")
    public ResultBody addPic(@RequestParam MultipartFile file,@RequestParam int goodsId){
        if (file.isEmpty()){
            return ResultBody.error().message("上传失败：文件为空");
        }
//        return goodspicService.upload(file,file2,file3);
        return goodspicService.upload(file,goodsId);
    }

    @ApiOperation("删除商品图片")
    @ApiImplicitParam(name = "id",value = "图片id",dataType = "int",example = "1")
    @RequestMapping("/deletePic")
    public ResultBody deletePic(@RequestParam String id){
        return goodspicService.delete(id);
    }

    @ApiOperation("生成图片链接")
    @GetMapping(value = "/show",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPic(String picName) throws Exception {

        File file = new File("//root//milkTea//goodsPic//"+picName);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }
}


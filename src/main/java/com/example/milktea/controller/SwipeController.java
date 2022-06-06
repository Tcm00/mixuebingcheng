package com.example.milktea.controller;


import com.example.milktea.pojo.entity.ResultBody;
import com.example.milktea.service.SwipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-23
 */
@Api(tags = "首页轮播图")
@RestController
@RequestMapping("/swipe")
public class SwipeController {

    @Autowired
    private SwipeService swipeService;

    @ApiOperation("查询首页轮播图链接")
    @RequestMapping("/getSwipe")
    public ResultBody getSwipe(){
        return swipeService.getSwipeList();
    }

    @ApiOperation("上传新的轮播图")
    @ApiImplicitParam(name = "file",value = "文件")
    @RequestMapping("/upload")
    public ResultBody upload(@RequestParam @NotEmpty MultipartFile file){
        if (file.isEmpty()){
            return ResultBody.error().message("上传失败：文件为空");
        }
        return swipeService.upload(file);
    }

    @ApiOperation("删除图片")
    @ApiImplicitParam(name = "id",value = "图片id",dataType = "int",example = "1")
    @RequestMapping("/deletePic")
    public ResultBody deletePic(@RequestParam String id){
        return swipeService.delete(id);
    }

    @ApiOperation("生成图片链接")
    @GetMapping(value = "/swipe",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPic(String picName) throws Exception {

        File file = new File("//root//milkTea//swipe//"+picName);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }
}


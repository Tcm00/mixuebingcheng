package com.example.milktea.controller;


import com.example.milktea.pojo.entity.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-23
 */
@Api(tags = "头像信息")
@Controller
@RequestMapping("/shopswipe")
public class ShopswipeController {

    @ApiOperation("生成图片链接")
    @GetMapping(value = "/title",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPic(String picName) throws Exception {
//        aaa.jpg
        File file = new File("//root//milkTea//title//"+picName);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }
}


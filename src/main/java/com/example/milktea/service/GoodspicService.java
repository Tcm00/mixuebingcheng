package com.example.milktea.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.milktea.pojo.Goodspic;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.milktea.pojo.entity.ResultBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-26
 */
public interface GoodspicService extends IService<Goodspic> {


    ResultBody getList(Page<Goodspic> page,int goodsId);

    ResultBody upload(MultipartFile file,int goodsId);

    ResultBody delete(String id);
}

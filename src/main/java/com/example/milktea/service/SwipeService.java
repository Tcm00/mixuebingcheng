package com.example.milktea.service;

import com.example.milktea.pojo.Swipe;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.milktea.pojo.entity.ResultBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-23
 */
public interface SwipeService extends IService<Swipe> {

    ResultBody getSwipeList();

    ResultBody upload(MultipartFile file);

    ResultBody delete(String id);
}

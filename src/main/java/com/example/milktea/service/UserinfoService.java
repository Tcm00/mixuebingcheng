package com.example.milktea.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.milktea.pojo.Userinfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.milktea.pojo.VO.GoodsInfoVO;
import com.example.milktea.pojo.entity.ResultBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-23
 */
public interface UserinfoService extends IService<Userinfo> {

    ResultBody addUser(Userinfo userinfo);

    ResultBody getUser(String phone, String password);

    ResultBody updateUser(Userinfo userinfo);


    ResultBody getInfo(Integer id);

    ResultBody getAllUser(Page<Userinfo> page);
}

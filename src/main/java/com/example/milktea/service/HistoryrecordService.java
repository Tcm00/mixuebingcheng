package com.example.milktea.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.milktea.pojo.Historyrecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.milktea.pojo.Userinfo;
import com.example.milktea.pojo.VO.GoodsInfoVO;
import com.example.milktea.pojo.VO.HistoryVO;
import com.example.milktea.pojo.entity.ResultBody;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-26
 */
public interface HistoryrecordService extends IService<Historyrecord> {

    ResultBody addMoney(Userinfo userinfo, Double money);

    ResultBody addOrder(List<Historyrecord> historyrecords,Userinfo userinfo);

    ResultBody getHistory(Integer pageNumber,Integer size, Integer id, Integer status);


    ResultBody recharge(Page<Historyrecord> page,int userId);

    ResultBody getHistoryNum(Integer pageNumber, Integer size, Integer id);

    ResultBody updateStatus(String orderId);
}

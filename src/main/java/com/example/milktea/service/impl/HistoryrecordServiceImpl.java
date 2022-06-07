package com.example.milktea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.metadata.PageList;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.milktea.mapper.GoodsinfoMapper;
import com.example.milktea.mapper.UserinfoMapper;
import com.example.milktea.pojo.Goodsinfo;
import com.example.milktea.pojo.Goodspic;
import com.example.milktea.pojo.Historyrecord;
import com.example.milktea.mapper.HistoryrecordMapper;
import com.example.milktea.pojo.Userinfo;
import com.example.milktea.pojo.VO.GoodsInfoVO;
import com.example.milktea.pojo.VO.GoodsVO;
import com.example.milktea.pojo.VO.HistoryVO;
import com.example.milktea.pojo.entity.ResultBody;
import com.example.milktea.service.HistoryrecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-26
 */
@Service
public class HistoryrecordServiceImpl extends ServiceImpl<HistoryrecordMapper, Historyrecord> implements HistoryrecordService {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Autowired
    private GoodsinfoMapper goodsinfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBody addMoney(Userinfo userinfo, Double money) {
        Historyrecord record = new Historyrecord();

        record.setOrderid(generateOrderNo());
        //设置用户id
        record.setUserId(userinfo.getId());
//        充值金额
        record.setMoney(money);
//        类型：  1：充值，2：消费
        record.setType(1);

        boolean b = false;
        try {
//        充值记录
            baseMapper.insert(record);
            Userinfo userinfo1 = userinfoMapper.selectById(userinfo.getId());
//        新的余额
            Double newBalance = money+userinfo1.getBalance();
//        更新余额
            userinfo1.setBalance(newBalance);
            userinfoMapper.updateById(userinfo1);
        } catch (Exception e) {
            //            手动回滚事务
            b = true;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
        if (b){
            return ResultBody.error().message("充值失败，钱已退回账户");
        }
        return ResultBody.ok().data("money",money).message("充值成功：");
    }

    @Override
    @Transactional
    public ResultBody addOrder(List<Historyrecord> historyrecords,Userinfo userinfoOld) {
        Userinfo userinfo = userinfoMapper.selectById(userinfoOld.getId());
        String orderId = generateOrderNo();
        double sumMoney = 0.00;

        for (Historyrecord historyrecord : historyrecords) {
            if (historyrecord.getNum()==0){
                return ResultBody.error().message("下单失败：数量不能为空");
            }
            historyrecord.setUserId(userinfo.getId());
            //1:充值  2:购买
            historyrecord.setType(2);
            Goodsinfo goodsinfo = goodsinfoMapper.selectById(historyrecord.getGoodsId());
            if (goodsinfo==null){
                return ResultBody.error().message("下单失败:商品ID错误");
            }
            historyrecord.setMoney(goodsinfo.getGoodsprice());
            historyrecord.setOrderid(orderId);
            historyrecord.setStatus(0);
            baseMapper.insert(historyrecord);
            sumMoney = sumMoney + historyrecord.getMoney()*historyrecord.getNum();
        }
        if (sumMoney>userinfo.getBalance()){
            QueryWrapper<Historyrecord> wrapper = new QueryWrapper<>();
            wrapper.eq("orderId",orderId);
            baseMapper.delete(wrapper);
            return ResultBody.error().message("下单失败:余额不足");
        }
        Userinfo userinfo1 = new Userinfo();
        userinfo1.setId(userinfo.getId());
        userinfo1.setBalance(userinfo.getBalance()-sumMoney);
        //修改积分
        userinfo1.setNoun(userinfo.getNoun()+(int)sumMoney);
        userinfoMapper.updateById(userinfo1);
        return ResultBody.ok().message("下单成功");
    }

    @Override
    public ResultBody getHistory(Integer pageNumber,Integer size,Integer userId, Integer status) {

        List<HistoryVO> history = baseMapper.getHistory( userId, status);
        for (HistoryVO historyVO : history) {
            List<GoodsVO> goodsinfos = historyVO.getGoodsinfos();
            Double orderSum = 0.00;
            for (GoodsVO goodsinfo : goodsinfos) {
                orderSum = orderSum + goodsinfo.getSum();
                String url = baseMapper.selectUrl(goodsinfo.getGoodsId());
                goodsinfo.setGoodsUrl(url);
            }
            historyVO.setOrderSum(orderSum);
        }
        Page pages = getPages(pageNumber, size, history);
        return ResultBody.ok().data("history",pages).message("查询历史订单成功");
    }

    @Override
    public ResultBody recharge(Page<Historyrecord> page,int userId) {
        QueryWrapper<Historyrecord> wrapper = new QueryWrapper<>();
        wrapper.eq("userId",userId);
        wrapper.eq("type",1);
        wrapper.orderByDesc("recordId");
        Page<Historyrecord> historyrecordPage = baseMapper.selectPage(page, wrapper);
        return ResultBody.ok().data("recharge",historyrecordPage);
    }

    @Override
    public ResultBody getHistoryNum(Integer pageNumber, Integer size, Integer userId) {
        List<HistoryVO> history = baseMapper.getHistoryNum(userId);
        for (HistoryVO historyVO : history) {
            List<GoodsVO> goodsinfos = historyVO.getGoodsinfos();
            Double orderSum = 0.00;
            for (GoodsVO goodsinfo : goodsinfos) {
                orderSum = orderSum + goodsinfo.getSum();
                String url = baseMapper.selectUrl(goodsinfo.getGoodsId());
                goodsinfo.setGoodsUrl(url);
            }
            historyVO.setOrderSum(orderSum);
        }
        Page pages = getPages(pageNumber, size, history);
        return ResultBody.ok().data("history",pages).message("查询历史订单成功");
    }

    /** 订单号生成(NEW) **/
    private static final AtomicInteger SEQ = new AtomicInteger(1000);
    private static final DateTimeFormatter DF_FMT_PREFIX = DateTimeFormatter.ofPattern("yyMMddHHmmssSS");
    private static ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");
    public static String generateOrderNo(){
        LocalDateTime dataTime = LocalDateTime.now(ZONE_ID);
        if(SEQ.intValue()>9990){
            SEQ.getAndSet(1000);
        }
        return  dataTime.format(DF_FMT_PREFIX)+SEQ.getAndIncrement();
    }

    /**
     * 分页函数
     * @author pochettino
     * @param currentPage   当前页数
     * @param pageSize  每一页的数据条数
     * @param list  要进行分页的数据列表
     * @return  当前页要展示的数据
     */
    private Page getPages(Integer currentPage, Integer pageSize, List list) {
        Page page = new Page();
        if(list==null){
            return  null;
        }
        int size = list.size();

        if(pageSize > size) {
            pageSize = size;
        }
        if (pageSize!=0){
            // 求出最大页数，防止currentPage越界
            int maxPage = size % pageSize == 0 ? size / pageSize : size / pageSize + 1;

//            if(currentPage > maxPage) {
//                currentPage = maxPage;
//            }
        }
        // 当前页第一条数据的下标
        int curIdx = currentPage > 1 ? (currentPage - 1) * pageSize : 0;

        List pageList = new ArrayList();

        // 将当前页的数据放进pageList
        for(int i = 0; i < pageSize && curIdx + i < size; i++) {
            pageList.add(list.get(curIdx + i));
        }

        page.setCurrent(currentPage).setSize(pageSize).setTotal(list.size()).setRecords(pageList);
        return page;
    }

}

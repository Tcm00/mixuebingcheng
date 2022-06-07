package com.example.milktea.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.milktea.pojo.Userinfo;
import com.example.milktea.mapper.UserinfoMapper;
import com.example.milktea.pojo.VO.GoodsInfoVO;
import com.example.milktea.pojo.entity.ResultBody;
import com.example.milktea.service.UserinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.milktea.utils.JWTUtil;
import com.example.milktea.utils.MobileUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-23
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements UserinfoService {

    @Override
    public ResultBody addUser(Userinfo userinfo) {
        QueryWrapper<Userinfo> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",userinfo.getPhone());
        List<Userinfo> userinfos = baseMapper.selectList(wrapper);
        if (!userinfos.isEmpty()){
            return ResultBody.error().message("注册失败：该账号已注册，请直接登录");
        }
        if (userinfo.getPhone()==null || userinfo.getPassword() == null){
            return ResultBody.error().message("注册失败：账号和密码不能为空");
        }
        userinfo.setNoun(0);
        userinfo.setBalance(0.00);

        boolean br = MobileUtil.checkPhone("15223650215");
        if (!br){
            return ResultBody.error().message("注册失败：请输入正确的手机号");
        }
        int insert = baseMapper.insert(userinfo);
        if (insert>0){
            return ResultBody.ok().message("注册成功");
        }
        return ResultBody.error().message("注册失败");
    }

    @Override
    public ResultBody getUser(String phone, String password) {
        QueryWrapper<Userinfo> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        wrapper.eq("password",password);
        Userinfo userinfo = baseMapper.selectOne(wrapper);
        try {
            if (!userinfo.toString().isEmpty()){
                HashMap<String, Object> map = new HashMap<>();
                try {
                    HashMap<String, String> payload = new HashMap<>();
                    payload.put("id", userinfo.getId().toString());
                    payload.put("phone",phone);
                    payload.put("sex", userinfo.getSex());
                    payload.put("birthday", userinfo.getBirthday());
                    //生成JWT令牌
                    String token = JWTUtil.getToken(payload);
                    map.put("state",true);
                    map.put("msg","认证成功");
                    //响应token
                    map.put("token",token);
                } catch (Exception e) {
                    map.put("state",false);
                    map.put("msg",e.getMessage());
                    e.printStackTrace();
                }
                System.out.println(map);
                return ResultBody.ok().data("info",userinfo).data("token",map).message("登录成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultBody.error().message("账号或密码错误！");
    }

    /**
     * 设置积分和余额为null，在此不受更改，更改接口需要鉴权。
     * @param userinfo
     * @return
     */
    @Override
    public ResultBody updateUser(Userinfo userinfo) {
        userinfo.setNoun(null);
        userinfo.setBalance(null);
        int i = baseMapper.updateById(userinfo);
        System.out.println("==>"+i);
        if (i>0){
            return ResultBody.ok().message("更新成功");
        }
        return ResultBody.error().message("更新失败");
    }

    @Override
    public ResultBody getInfo(Integer id) {
        Userinfo userinfo = baseMapper.selectById(id);
        System.out.println("+++++++"+userinfo);
        if (userinfo==null){
            return ResultBody.error().message("未找到该用户");
        }
        return ResultBody.ok().data("info",userinfo);
    }

    @Override
    public ResultBody getAllUser(Page<Userinfo> page) {
        QueryWrapper<Userinfo> wrapper = new QueryWrapper<>();
        Page<Userinfo> userinfoPage = baseMapper.selectPage(page, wrapper);
        return ResultBody.ok().data("allUser",userinfoPage);
    }


}

package com.example.milktea.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.milktea.pojo.Userinfo;
import com.example.milktea.pojo.VO.GoodsInfoVO;
import com.example.milktea.pojo.entity.ResultBody;
import com.example.milktea.service.UserinfoService;
import com.example.milktea.utils.JWTUtil;
import com.example.milktea.utils.LocalUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-23
 */
@Api(tags = "用户管理信息")
@RestController
@RequestMapping("/userinfo")
public class UserinfoController {

    @Autowired
    private UserinfoService userinfoService;


    @ApiOperation(value = "用户注册")
    @RequestMapping("/register")
    public ResultBody register(@RequestBody @ApiParam Userinfo userinfo){
        return userinfoService.addUser(userinfo);
    }

    @ApiOperation(value = "用户登录")
    @RequestMapping("/login")
    public ResultBody login(@RequestParam String phone,@RequestParam String password){
        return userinfoService.getUser(phone,password);
    }

    @ApiOperation(value = "更新用户信息")
    @RequestMapping("/update")
    public ResultBody updateUser(@RequestBody @ApiParam Userinfo userinfo){
        Userinfo userinfo1 = LocalUser.USER.get();
        //如果没有传用户id，则默认修改当前用户的信息
        if (userinfo.getId()==null){
            userinfo.setId(userinfo1.getId());
        }
        return userinfoService.updateUser(userinfo);
    }

    @ApiOperation(value = "查询该用户信息")
    @RequestMapping("/getInfo")
    public ResultBody getUserInfo(){
        Userinfo userinfo = LocalUser.USER.get();
        return userinfoService.getInfo(userinfo.getId());
    }

    @ApiOperation(value = "查询所有用户")
    @RequestMapping("/getAllUser")
    public ResultBody getAllUser(@RequestParam(defaultValue = "1") @NotEmpty Long pageNumber,
                                 @RequestParam(defaultValue = "10") @NotEmpty Long size){
        Page<Userinfo> Page = new Page<>(pageNumber, size);
        return userinfoService.getAllUser(Page);
    }
    /**
     * 通过当前携带的token获取当前用户信息
     * @param request
     * @return
     */
    @ApiOperation(value = "解析基本信息")
    @RequestMapping("/explain")
    public ResultBody test(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        //token=xx.xx.xx的头信息
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtil.verify(token);
        //通过k-v中的键取出（k不可以重复）
        String userId = verify.getClaim("id").asString();
        String phone = verify.getClaim("phone").asString();
        String sex = verify.getClaim("sex").asString();
        String birthday = verify.getClaim("birthday").asString();

        map.put("userId",userId);
        map.put("phone",phone);
        map.put("sex",sex);
        map.put("birthday",birthday);

        return ResultBody.ok().data("info",map);
    }

}


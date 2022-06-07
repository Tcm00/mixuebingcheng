package com.example.milktea.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.milktea.pojo.Userinfo;

import java.util.Calendar;
import java.util.Map;

/**
 * @author Tcm
 */
//抑制警告
@SuppressWarnings("all")
public class JWTUtil {
    //签名,自己设置一串复杂的字符串
    private static final String SING = "!Q@W#E$R%t";

    /**
     * 生成token
     * @param map 用户信息（userId，2019211715）
     * @return
     */
    public static String getToken(Map<String,String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,100);//默认7天过期

        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        //指定令牌过期时间
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));//sign
        return token;
    }
    /**
     * 解析token
     * @param token
     * @return
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    public static Userinfo getUser(String token){
        final Userinfo userinfo = new Userinfo();
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();
        String phone = verify.getClaim("phone").asString();
        String birthday = verify.getClaim("birthday").asString();

        userinfo.setId(Integer.parseInt(id));
        userinfo.setPhone(phone);
        userinfo.setBirthday(birthday);

        return userinfo;
    }
}

package com.example.milktea.utils;

import com.example.milktea.pojo.Userinfo;
import org.springframework.stereotype.Component;

/**
 * @author 小明
 * @date 2021/10/27
 * @description
 */
@Component
public class LocalUser {
    public static ThreadLocal<Userinfo> USER = new ThreadLocal<>();
}

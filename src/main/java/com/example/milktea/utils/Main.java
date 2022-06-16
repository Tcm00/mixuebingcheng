package com.example.milktea.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author 小明
 * @date 2022/6/9
 * @description
 */
public class Main {
    public static void main(String[] args) throws Exception{
        StringBuffer sb = new StringBuffer();
        StringBuilder stringBuilder1 = StringBuilder.class.newInstance();
        String.class.getClassLoader();
        sb.append("aa");
        sb.insert(1,"b");
        System.out.println(sb);
        StringBuilder stringBuilder = new StringBuilder();

        ArrayList<String> strings = new ArrayList<>();
        System.out.println("============================");
        String a = "aaaa";
        String replace = a.replace('a', 'b');
//        String s = a.replaceAll("aa", "b");
        System.out.println(a);
        System.out.println(replace);
//        System.out.println(s);
        System.out.println("============================");
        try {
            String saaaaa = "";
            int i =1/0;
            System.out.println("sssss+"+saaaaa);
        } catch (Exception e) {
            System.out.println("out打印的报错");
            System.err.println("err的打印顺序");
            e.printStackTrace();
        }
        Runnable test = () -> System.out.println("Test");

        Interface params2 = (s) -> {
            System.out.println("Lambda: " + s);
            return "def";
        };
        System.out.println("============================");
        LocalDateTime now = LocalDateTime.now();
        LocalDate now1 = LocalDate.now();
        LocalTime now2 = LocalTime.now();
        ZonedDateTime now3 = ZonedDateTime.now();
        Instant now4 = Instant.now();
        System.out.println(now);
        System.out.println(now1);
        System.out.println(now2);
        System.out.println(now3);
        System.out.println(now4);
        System.out.println("============================");
        Date date = new Date();
        LocalDateTime nowDteTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime ldt = LocalDateTime.now();
        String s1 = ldt.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(s1);
        System.out.println(s2);

    }
    public void aa(){
        create table tb_dept(
                9     Id int primary key auto_increment,#部门编号 整形 主键 自增长
        10     Name varchar(18),#部门名称
        11     description varchar(100)#描述
        12 );
    }
}

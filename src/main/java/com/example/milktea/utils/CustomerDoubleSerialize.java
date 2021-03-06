package com.example.milktea.utils;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 价格保留两位小数
 */
public class CustomerDoubleSerialize extends JsonSerializer<Double> {
    //原本这里是  ##.00 ,带来的问题是如果数据库数据为0.00返回“ .00 “经评论指正，改为0.00
    private DecimalFormat df = new DecimalFormat("0.00");
    @Override
    public void serialize(Double arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
        if(arg0 != null) {
            arg1.writeString(df.format(arg0));
        }
    }

}


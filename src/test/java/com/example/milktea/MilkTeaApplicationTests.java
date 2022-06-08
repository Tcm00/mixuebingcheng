package com.example.milktea;

import com.example.milktea.pojo.Attributea;
import com.example.milktea.pojo.entity.ResultBody;
import com.example.milktea.service.HistoryrecordService;
import com.example.milktea.service.SwipeService;
import com.example.milktea.utils.MobileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Hashtable;


@SpringBootTest
class MilkTeaApplicationTests {

    @Test
    void phone(){
        System.out.println(MobileUtil.checkPhone("15223650215"));
    }

    @Autowired
    private SwipeService swipeService;
    @Test
    void upload(){
        Attributea attributea = new Attributea();
    }

    @Autowired
    private HistoryrecordService historyrecordService;

    @Test
    void updateStatus(){
        ResultBody resultBody = historyrecordService.updateStatus("220608123201811000");
    }
}

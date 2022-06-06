package com.example.milktea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.milktea.pojo.Swipe;
import com.example.milktea.mapper.SwipeMapper;
import com.example.milktea.pojo.entity.ResultBody;
import com.example.milktea.service.SwipeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-23
 */
@Service
public class SwipeServiceImpl extends ServiceImpl<SwipeMapper, Swipe> implements SwipeService {

    @Override
    public ResultBody getSwipeList() {
        List<Swipe> swipes = baseMapper.selectList(new QueryWrapper<>());

        return ResultBody.ok().data("swipes",swipes);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBody upload(MultipartFile file) {
        String filePath = "//root//milkTea//swipe//";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = dateFormat.format(new Date());
        String fileName = format+".jpg";
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        boolean b = false;
        try {
            //写入
            file.transferTo(dest);
            Swipe swipe = new Swipe(0,"http://facetocode.xyz:9091/swipe/swipe?picName="+fileName);
            baseMapper.insert(swipe);
        } catch (IOException e) {
            e.printStackTrace();
            b = true;
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        if (b){
            return ResultBody.error().message("图片上传失败");
        }
        return ResultBody.ok().message("成功:图片长传成功");
    }

    @Override
    public ResultBody delete(String id) {
        Swipe swipe = baseMapper.selectById(id);
        if (Objects.equals(swipe.getUrl(), "")){
            return ResultBody.ok().message("删除失败：未找到该文件");
        }
        String filePath = "//root//milkTea//swipe//";
        String substring = swipe.getUrl().substring(swipe.getUrl().indexOf("=")+1);
        System.out.println(filePath+substring);
        boolean b = deleteFile(filePath + substring);
        baseMapper.deleteById(id);
        if (b){
            return ResultBody.ok().message("删除成功");
        }
        return ResultBody.error().message("删除失败");
    }

    //删除轮播图照片
    public static boolean deleteFile(String fileName){
        File file = new File(fileName);
        if(file.isFile() && file.exists()){
            file.delete();
            System.out.println("删除单个文件"+fileName+"成功！");
            return true;
        }else{
            System.out.println("删除单个文件"+fileName+"失败！");
            return false;
        }
    }
}

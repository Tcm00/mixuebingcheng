package com.example.milktea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.milktea.pojo.Goodspic;
import com.example.milktea.mapper.GoodspicMapper;
import com.example.milktea.pojo.Swipe;
import com.example.milktea.pojo.entity.ResultBody;
import com.example.milktea.service.GoodspicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.milktea.utils.DeleteFileUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SixFeet Under
 * @since 2022-05-26
 */
@Service
public class GoodspicServiceImpl extends ServiceImpl<GoodspicMapper, Goodspic> implements GoodspicService {


    @Override
    public ResultBody getList(Page<Goodspic> page,int goodsId) {
        QueryWrapper<Goodspic> wrapper = new QueryWrapper<>();
        wrapper.eq("goodsId",goodsId);
        Page<Goodspic> goodspicPage = baseMapper.selectPage(page, wrapper);
        return ResultBody.ok().data("pic",goodspicPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBody upload(MultipartFile file,int goodsId) {
        String filePath = "//root//milkTea//goodsPic//";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = dateFormat.format(new Date());
        String fileName = format+goodsId+".jpg";
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        boolean b = false;
        try {
            //写入
            file.transferTo(dest);
            Goodspic goodspic = new Goodspic(0, goodsId, "http://facetocode.xyz:9091/goodspic/show?picName=" + fileName);
            baseMapper.insert(goodspic);
        } catch (IOException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            b = true;
            e.printStackTrace();
        }
        if (b) {
            return ResultBody.error().message("图片上传失败");
        }
        return ResultBody.ok().message("成功:图片长传成功");
    }

    @Override
    public ResultBody delete(String id) {
        Goodspic goodspic = baseMapper.selectById(id);
        if (Objects.equals(goodspic.getGoodsurl(), "")){
            return ResultBody.error().message("删除失败：未找到该文件");
        }
        String filePath = "//root//milkTea//goodsPic//";
        String substring = goodspic.getGoodsurl().substring(goodspic.getGoodsurl().indexOf("=")+1);
        System.out.println(filePath+substring);
        boolean b = DeleteFileUtil.delete(filePath + substring);
        baseMapper.deleteById(id);
        if (b){
            return ResultBody.ok().message("删除成功");
        }
        return ResultBody.error().message("删除失败");
    }
}

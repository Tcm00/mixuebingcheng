package com.example.milktea;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

/**
 * @author 小明
 * @date 2022/5/26
 * @description
 */
public class CodeGenerator {

    /**
     * 自动代码,别动！！！
     * 34行文件覆盖 == false
     */
    @Test
    void generator(){
        //1.全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(true) //是否支持AR模式
                .setAuthor("小明晚安了") //作者
                .setOutputDir("E:\\APoject\\milkTea\\src\\main\\java") //生成路径
                .setIdType(IdType.AUTO) //主键策略
                .setDateType(DateType.ONLY_DATE)//定义生成的实体类中日期类型
                .setSwagger2(true) //开启Swagger2模式
                .setServiceName("%sService") //设置生成的service接口的名字的手字母是否为I
                .setBaseResultMap(true) //外部命名引用
                .setBaseColumnList(true) //生成SQL 片段
                .setFileOverride(false); //文件覆盖
        //2.数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL) //设置数据库类型
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://42.192.224.189:3306/milkytea")
                .setUsername("milkyTea")
                .setPassword("002582");
        //3.策略配置
        StrategyConfig config = new StrategyConfig();
        config.setCapitalMode(true) //全局大写命名
                .setNaming(NamingStrategy.underline_to_camel); //数据库表映射到实体的命名策略
//                .setTablePrefix("tbl") //设置表名前缀
//                .setInclude("tbl_employee"); //生成的表
        //4.包名策略配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.example.milktea")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("pojo")
                .setXml("mapper");

        //5.整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(config)
                .setPackageInfo(packageConfig);
        //6.执行
        autoGenerator.execute();

    }
}

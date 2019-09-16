package com.zzx.humor;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class CodeGenerator {
    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("zhang");//作者
        gc.setOutputDir("F:/data"); //生成代码路径
        gc.setBaseResultMap(true);
        generator.setGlobalConfig(gc);
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);//sql数据库 类型
        dsc.setUrl("jdbc:mysql://120.79.35.220:3306/humor?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("@zzxsy");
        generator.setDataSource(dsc);
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setRestControllerStyle(true);//加restController
        strategy.setInclude(new String[]{"HU_USER"});//要生成的表
        strategy.setEntityLombokModel(true);
        generator.setStrategy(strategy);
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.eshop");//父包名
        generator.setPackageInfo(pc);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();
    }
}

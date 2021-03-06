package com.dingxin.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 代码生成器演示
 * </p>
 */
public class MpGenerator {

    public static final String prefix = "D:\\code";

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(prefix);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor("mal");

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sDao");
        // gc.setServiceName("MP%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
        mpg.setGlobalConfig(gc);

        final GlobalConfig finalGc = gc;
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            public IColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。\
                IColumnType dbColumnType = super.processTypeConvert(finalGc, fieldType);
                if ("bit(1)".equals(fieldType)) {
                    dbColumnType = DbColumnType.BOOLEAN;
                }
                if (dbColumnType.equals(DbColumnType.DATE)) {
                    if ("datetime".equals(fieldType)) {
                        dbColumnType = DbColumnType.LOCAL_DATE_TIME;
                    }
                    if ("date".equals(fieldType)) {
                        dbColumnType = DbColumnType.LOCAL_DATE;
                    }

                }
                return dbColumnType;
            }
        });
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("yyq!@apache.");
        dsc.setUrl("jdbc:mysql://182.61.61.83:3306/ykt?serverTimezone=CTT&characterEncoding=utf-8&allowMultiQueries=true&rewriteBatchedStatements=true&connectTimeout=2000&socketTimeout=15000");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setTablePrefix(new String[]{"nkd_", "sys_", "ccr_","cas_"});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
                strategy.setInclude(new String[]{"ccr_achievement","ccr_achievement_user"}); // 需要生成的表
        strategy.setExclude(new String[]{}); // 排除生成的表
        // 自定义实体父类
        // strategy.setSuperEntityClass("com.baomidou.demo.TestEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuilderModel(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.dingxin.cas");
        pc.setController("web.controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setEntity("pojo.po");
        pc.setMapper("dao.mapper");
        //        pc.setModuleName("demo");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        // 自定义 xxList.jsp 生成
        //        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        //        focList.add(new FileOutConfig("/template/list.jsp.vm") {
        //            @Override
        //            public String outputFile(TableInfo tableInfo) {
        //                // 自定义输入文件名称
        //                return "/Users/van/test/my_" + tableInfo.getEntityName() + ".jsp";
        //            }
        //        });
        //        cfg.setFileOutConfigList(focList);
        //        mpg.setCfg(cfg);

        // 调整 xml 生成目录演示
        //        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
        //            @Override
        //            public String outputFile(TableInfo tableInfo) {
        //                return "/Users/van/test/develop/code/xml/" + tableInfo.getEntityName() + ".xml";
        //            }
        //        });
        //        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 关闭默认 xml 生成，调整生成 至 根目录
        //        TemplateConfig tc = new TemplateConfig();
        //        tc.setXml("/");
        //        mpg.setTemplate(tc);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templates/Controller.java.vm");
        tc.setEntity("/templates/Entity.java.vm");
        tc.setMapper("/templates/mapper.java.vm");
        tc.setXml("/templates/mapper.xml.vm");
        tc.setService("/templates/Service.java.vm");
        tc.setServiceImpl("/templates/ServiceImpl.java.vm");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();

        // 打印注入设置【可无】
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }

}
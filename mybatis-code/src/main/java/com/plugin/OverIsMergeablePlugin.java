package com.plugin;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @ClassName : OverIsMergeablePlugin
 * @Description : 解决重复生产时单吗覆盖的问题
 * @Author : fmx
 * @Date: 2021-05-06 17:43
 */
public class OverIsMergeablePlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return false;
    }
    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable){
        try{
            Field field = sqlMap.getClass().getDeclaredField("isMergeable");
            field.setAccessible(true);
            field.setBoolean(sqlMap,false);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

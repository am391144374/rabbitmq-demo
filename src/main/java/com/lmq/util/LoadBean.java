package com.lmq.util;

import com.lmq.bean.MqPropertiesBean;
import com.lmq.bean.PropertiesBean;
import com.lmq.contains.Contain;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Properties;

@Slf4j
public class LoadBean {

    public static Object loadMqBeanFromProperties(Class c, Properties properties,String commonPropertiesPath){
        Object o = null;
        try {
            o = c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Field[] fileds = c.getDeclaredFields();
        for(Field field : fileds){
            String fieldName = field.getName();
            Object value = properties.get(commonPropertiesPath+ Contain.POINT+fieldName);
            if(value != null){
                try {
                    field.setAccessible(true);
                    field.set(o,value);
                } catch (IllegalAccessException e) {
                    log.error("解析properties失败，{}未有对应字段",fieldName);
                    e.printStackTrace();
                }
            }
        }

        return o;
    }
}

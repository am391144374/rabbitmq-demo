package com.lmq.resouce;

import com.lmq.bean.MqPropertiesBean;
import com.lmq.contains.MqContain;
import com.lmq.core.MqProperties;
import com.lmq.util.LoadBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MqDefaultResource extends MqProperties implements ResourceProperties {

    @Override
    public void loadPerties() {
        MqPropertiesBean mqPropertiesBean = new MqPropertiesBean();
        setProperties(load(mqPropertiesBean));
    }

    private MqPropertiesBean load(MqPropertiesBean mqPropertiesBean){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(this.propertiesPath)));
            mqPropertiesBean = (MqPropertiesBean) LoadBean.loadMqBeanFromProperties(mqPropertiesBean.getClass(),properties, MqContain.COMMONPROPERTIESPATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mqPropertiesBean;
    }

}

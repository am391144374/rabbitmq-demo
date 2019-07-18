package com.lmq.resource;

import com.lmq.bean.MqPropertiesBean;
import com.lmq.exception.PropertiestIsNullException;
import com.lmq.resouce.MqDefaultResource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class MqResourceTest {

    @Test
    public void ResourceTest(){
        MqDefaultResource mqDefaultResource = new MqDefaultResource();
        mqDefaultResource.loadPerties();
        try {
            MqPropertiesBean mqPropertiesBean = (MqPropertiesBean) mqDefaultResource.getProperties();
            log.info(mqPropertiesBean.toString());
        } catch (PropertiestIsNullException e) {
            e.printStackTrace();
        }

    }

}

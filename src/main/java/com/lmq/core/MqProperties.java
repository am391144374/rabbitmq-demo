package com.lmq.core;

import com.lmq.bean.PropertiesBean;
import com.lmq.contains.MqContain;
import com.lmq.util.FileUtilPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

@Slf4j
public class MqProperties extends AbstractPropertiesConfig {

    protected final String propertiesPath = FileUtilPath.getPropertiesPath(MqContain.MQFILENAME);

    @Override
    public void setProperties(PropertiesBean properties) {
        if(properties != null){
            log.warn("Properties已经初始化，无需再次加载。");
        }
        this.propertiesBean = properties;
    }
}

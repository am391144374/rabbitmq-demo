package com.lmq.core;

import com.lmq.bean.PropertiesBean;
import com.lmq.exception.PropertiestIsNullException;

public abstract class AbstractPropertiesConfig implements PropertiesConfig{

    protected PropertiesBean propertiesBean;

    @Override
    public PropertiesBean getProperties() throws PropertiestIsNullException {
        if(this.propertiesBean == null){
            throw new PropertiestIsNullException("Properties为空");
        }
        return this.propertiesBean;
    }

}

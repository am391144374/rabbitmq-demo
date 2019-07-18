package com.lmq.core;

import com.lmq.bean.PropertiesBean;
import com.lmq.exception.PropertiestIsNullException;

public interface PropertiesConfig {

    PropertiesBean getProperties() throws PropertiestIsNullException;

    void setProperties(PropertiesBean properties);

}

package com.lmq.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MqPropertiesBean implements PropertiesBean {

    private String host;
    private String port;
    private String vhost;
    private String userName;
    private String passwd;
    private String exchangeName;
    private String queueName;
    private String routingKey;

}

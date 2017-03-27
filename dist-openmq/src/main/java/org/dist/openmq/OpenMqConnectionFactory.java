package org.dist.openmq;

import java.util.Enumeration;
import java.util.Properties;

import javax.jms.XAConnectionFactory;

public class OpenMqConnectionFactory {
	   
    private Properties props;

    public void setProperties(Properties props) {
        this.props = props;
    }

    public XAConnectionFactory createConnectionFactory(){
        com.sun.messaging.XAConnectionFactory cf =
                new com.sun.messaging.XAConnectionFactory();
        try{
            Enumeration<?> keys = props.propertyNames();
            while (keys.hasMoreElements()) {
                String name = (String)keys.nextElement();
                String value = props.getProperty(name);
                cf.setProperty(name, value);
            }
        } catch (Exception e){
            throw new RuntimeException(
            "MQConnectionFactoryFactory.createConnectionFactory() failed: "+
            e.getMessage(), e);
        }
        return cf;
    }
}
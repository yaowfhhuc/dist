package com.eastcom.ipnet.orders.entry;

import java.io.Serializable;

public class ScriptEntry implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String deviceName,command,scriptTxt,deviceId,city,factory;

    
    public ScriptEntry()
    {
        super();
    }

    public ScriptEntry(String command, String scriptTxt,String deviceName, 
            String deviceId, String city, String factory)
    {
        super();
        this.deviceName = deviceName;
        this.command = command;
        this.scriptTxt = scriptTxt;
        this.deviceId = deviceId;
        this.city = city;
        this.factory = factory;
    }

    public String getDeviceName()
    {
        return deviceName;
    }

    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    public String getCommand()
    {
        return command;
    }

    public void setCommand(String command)
    {
        this.command = command;
    }

    public String getScriptTxt()
    {
        return scriptTxt;
    }

    public void setScriptTxt(String scriptTxt)
    {
        this.scriptTxt = scriptTxt;
    }

    public String getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getFactory()
    {
        return factory;
    }

    public void setFactory(String factory)
    {
        this.factory = factory;
    }

}

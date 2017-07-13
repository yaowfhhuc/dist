package me.test.util.unix.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import me.test.util.unix.message.Constants;

/**
 * Title: Followinds 广东巡检项目改造组 <br>
 * Description: 结果封装对象<br>
 * Copyright: eastcom Copyright (C) 2012 <br>
 * 
 * @author xiazy<br>
 * @version 1.0 <br>
 * @creatdate 2012-3-19 下午03:17:33 <br>
 * 
 */
public class ExecuteResult implements Serializable
{
    private static final long serialVersionUID = 5868503223154985448L;

    private Boolean executeResult;// 是否执行成功

    private String log = "";// 日志

    private String desc = "";// 描述

    private Map<String, Object> param;//脚本的输出参数
    
    private String resultCode=Constants.ORDERS_NOT_FOUND_DEVICE;//错误码,默认为 1006
    
    private String message="";//如发生错误，则是错误的描述信息
    
    private Boolean ret;//脚本结果判断
    
    private String summary;//总结
    
    private String error;
    
    
    public ExecuteResult()
    {

        param = new HashMap<String, Object>();
    }

    public Boolean getExecuteResult()
    {
        return executeResult;
    }

    public void setExecuteResult(Boolean executeResult)
    {
        this.executeResult = executeResult;
    }

    public String getLog()
    {
        return log;
    }

    public void setLog(String log)
    {
        this.log = log;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public void addParam(String key, Object value)
    {
        param.put(key, value);
    }

    public Object getObject(String key)
    {
        if (key == null)
            return null;
        return param.get(key);
    }

    public Map<String, Object> getParam()
    {
        return param;
    }

    public String getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(String resultCode)
    {
        this.resultCode = resultCode;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Boolean getRet()
    {
        return ret;
    }

    public void setRet(Boolean ret)
    {
        this.ret = ret;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getError()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }

}

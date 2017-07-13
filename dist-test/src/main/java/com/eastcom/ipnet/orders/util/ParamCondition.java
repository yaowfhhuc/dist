package com.eastcom.ipnet.orders.util;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * 
 * Title: Followinds 广东指令平台改造组 <br>
 * @author xiazy<br>
 * @version 1.0 <br>
 * @creatdate 2012-7-20 上午11:35:45 <br>
 *参数工具
 */
public class ParamCondition
{
    
    private Map<String,String> map;
    public ParamCondition(){
        map=new HashMap<String, String>();
    }
    
    public void putValue(String key,String value){
        map.put(key, value);
    }
    
    public String getValue(String key){
       return  map.get(key);
    }

    public void setMap(Map<String, String> map)
    {
        this.map = map;
    }
}

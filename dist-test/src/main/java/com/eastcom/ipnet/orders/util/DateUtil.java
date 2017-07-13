package com.eastcom.ipnet.orders.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Title: Followinds 广东指令平台改造组 <br>
 * @author xiazy<br>
 * @e-mail: xiazy@east-sw.com <br>
 * @version 1.0 <br>
 * @creatdate 2012-7-20 下午07:01:48 <br>
 *
 */
public class DateUtil
{
    /**
     * * 根据日期格式，返回日期按datePattern格式转换后的字符串 * * 
     * @param aDate * 日期对象 * 
     * @return 格式化后的日期的页面显示字符串
     */
    public static final String getDateStrShort(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (aDate != null) {
            df = new SimpleDateFormat("yyyy-MM-dd");
            returnValue = df.format(aDate);
        }
        return returnValue;
    }
    
    public static final String getDateStrLong(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (aDate != null) {
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            returnValue = df.format(aDate);
        }
        return returnValue;
    }
    
    /**
     * 解析字符串时期为Date类型
     * @param dateStr
     * @return
     */
    public static final Date pareDate(String dateStr){
        SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=null;
        try
        {
           date= df.parse(dateStr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            date=new Date();
        }
        return date;
    }
    
    /**
     * 传入的时期加上相应天数
     * @param date
     * @param days
     * @return
     */
    public static final Date addDays(Date date,int days){
        Calendar c = Calendar.getInstance(); 
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }
    
    public static void main(String[] args)
    {
        //pareDate(new Date());
        System.out.println(pareDate(getDateStrShort(new Date())+" 00:00:00"));
    }
    
}

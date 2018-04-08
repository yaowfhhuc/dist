package orders.util;

import java.util.List;

/**
 * Title: Followinds 广东指令平台改造组 <br>
 * Description:页面展示数据使用 <br>
 * @e-mail: xiazy@east-sw.com <br>
 * @version 1.0 <br>
 * @creatdate 2012-7-20 下午04:04:15 <br>
 *
 */
public class PageCondition
{
    private List  listData;
    private int countSize;//总记录数
    public List getListData()
    {
        return listData;
    }
    public void setListData(List listData)
    {
        this.listData = listData;
    }
    public int getCountSize()
    {
        return countSize;
    }
    public void setCountSize(int countSize)
    {
        this.countSize = countSize;
    }
    

}

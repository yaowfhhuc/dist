package fm5.datatask.idc;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BillingFlow implements Serializable{

	
	private static Map<String, String> citys = new HashMap<String, String>();
	
	static {
		citys.put("郑州市","A");
		citys.put("开封市","B" );
		citys.put("洛阳市","C");
		citys.put("平顶山市","D");
		citys.put("安阳市","E");
		citys.put("鹤壁市","F");
		citys.put("新乡市","G");
		citys.put("焦作市","H" );
		citys.put("濮阳市","J");
		citys.put("许昌市","K" );
		citys.put("漯河市","L");
		citys.put("三门峡市","M");
		citys.put("商丘市","N" );
		citys.put("周口市","P" );
		citys.put("驻马店市","Q");
		citys.put("南阳市","R");
		citys.put("信阳市","S");
		citys.put("济源市","U");
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1395070504426016259L;

	private Date timeStamp;//时间  月度，格式为yyyy-mm
	
	private String chargeCode;//计费代码   作为主键
	
	private String guaranteedBandwidth;//保底带宽  格式数字+单位，比如2.5G
	
	private float price;//订购单价  默认不带单位
	
	private String chargeMode ;//计费模式   用枚举值传递，端口计费：DKJF；95流量计费：95JF

	private String ecid;//ECID（集团客户编号）
	
	private float traffic;//月度95流量   Gbps
	
	private String city;
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return city;
	}
	
	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public float getTraffic() {
		return traffic;
	}
	public void setTraffic(float traffic) {
		this.traffic = traffic;
	}
	
	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getGuaranteedBandwidth() {
		return guaranteedBandwidth;
	}

	public void setGuaranteedBandwidth(String guaranteedBandwidth) {
		this.guaranteedBandwidth = guaranteedBandwidth;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getChargeMode() {
		return chargeMode;
	}

	public void setChargeMode(String chargeMode) {
		this.chargeMode = chargeMode;
	};
	
	public String getEcid() {
		return ecid;
	}

	public void setEcid(String ecid) {
		this.ecid = ecid;
	}

	public String toString(String serperator){
		return System.getProperty("line.separator")+new SimpleDateFormat("yyyyMM").format(timeStamp)+
				serperator+chargeCode+
				serperator+guaranteedBandwidth+
				serperator+price+
				serperator+(chargeMode==null?"":chargeMode)+
				serperator+(ecid==null?"":ecid)+
				serperator+traffic+
				serperator+city;
	};
	
	public static void main(String[] args) {
		BillingFlow billingFlow = new BillingFlow();
		
		billingFlow.setTimeStamp(new Date());
		
		System.out.println(billingFlow.toString("|"));
	}
}

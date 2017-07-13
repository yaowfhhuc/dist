package fm5.datatask.idc;

import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Component
public class Idc2BossTask extends Thread{
	
	private Logger logger = LoggerFactory.getLogger( Idc2BossTask.class);
	
	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SftpSource sftpSource;
	
	public void init(){
		super.start();
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void setSftpSource(SftpSource sftpSource) {
		this.sftpSource = sftpSource;
	}


	@Scheduled(cron="${sftp.uploadTime}")
	@Override
	public void run() {
		logger.info("get data from database >>> ");
		List<BillingFlow> list = jdbcTemplate.query("select * from  NHM.NHM_BOSS_95_MONTH where time_stamp = ( select max(time_stamp) from  NHM.NHM_BOSS_95_MONTH)", 
				new RowMapper<BillingFlow>(){
				BillingFlow flow = null;
					@Override
					public BillingFlow mapRow(ResultSet rs, int rowNum) throws SQLException {
						flow = new BillingFlow();
						try {
							flow.setTimeStamp(new SimpleDateFormat("yyyyMM").parse(rs.getString("TIME_STAMP")));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						flow.setChargeCode(rs.getString("BUSINESS_NO"));
						flow.setGuaranteedBandwidth(rs.getFloat("BANDWIDTH")+"");
						flow.setPrice(rs.getFloat("PRICE"));
						flow.setChargeMode(rs.getString("CHARGING_MODE"));
						flow.setEcid(rs.getString("ECID"));
						flow.setTraffic(rs.getFloat("TRAFFIC"));
						flow.setCity(rs.getString("CITY"));
						return flow;
					}});
		
		
		//List<BillingFlow> list = jdbcTemplate.queryForList("select * from  NHM.NHM_BOSS_95_MONTH where time_stamp = ( select max(time_stamp) from  NHM.NHM_BOSS_95_MONTH)");
		logger.info("data size {}" ,list.size() );
		
		outputEntity(list);
	}
	
	private void outputEntity(List<BillingFlow> list){
		ChannelSftp sftp = null;
		Channel channel = null;
		String filename="IDC95_"+new SimpleDateFormat("yyyyMM").format(new Date())+".txt";
		String src = sftpSource.getLocalPath()+"/"+filename;
		String dst =filename;
		try {
			makeFile(list,src);
		} catch (Exception e1) {
			e1.printStackTrace();
			logger.error("生成文件失败 {}",e1.getMessage());
		}
		try {
			JSch jSch = new JSch();
			Session session = jSch.getSession(sftpSource.getUsername(), sftpSource.getUrl(), sftpSource.getPort());
			session.setPassword(sftpSource.getPassword());
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			session.setConfig(sshConfig);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			logger.info("connected to sftp server ");
			
			sftp.cd(sftpSource.getRemotePath());
			sftp.put(src,dst,ChannelSftp.OVERWRITE);
			logger.info("upload file successed ");
			//new File(src).delete();
			
		} catch (JSchException e) {
			e.printStackTrace();
			logger.error("get connect from {} failed ",sftpSource.getUrl());
		} catch (SftpException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}finally {
			if(sftp!=null)
				sftp.exit();
		}
	}
	private void makeFile(List<BillingFlow> list,String src) throws Exception{
	
		FileWriter fileWriter = new FileWriter(src);
		fileWriter.write("时间"+sftpSource.getSeperatekey()+
				"计费代码"+sftpSource.getSeperatekey()+
				"保底带宽"+sftpSource.getSeperatekey()+
				"订购单价"+sftpSource.getSeperatekey()+
				"计费模式"+sftpSource.getSeperatekey()+
				"ECID（集团客户编号）"+sftpSource.getSeperatekey()+
				"月度95流量"+sftpSource.getSeperatekey()+"地市");
		if(list!=null&&list.size()>0){
			for (BillingFlow entity : list){
				fileWriter.write(entity.toString(sftpSource.getSeperatekey()));
			}
		}
		fileWriter.flush();
		fileWriter.close();
		logger.info("create file succcessed ");
	}
	
	public static void main(String[] args) {
		try {
			
			
		/*	SftpSource sftpSource = new SftpSource();
					sftpSource.setSeperatekey("|");
			Idc2BossTask idc2BossTask = new Idc2BossTask();
			idc2BossTask.setSftpSource(sftpSource);
			
			List<BillingFlow> list= new ArrayList<BillingFlow>();
			BillingFlow billingFlow = new BillingFlow();
			billingFlow.setChargeCode("chargeCode");
			billingFlow.setChargeMode("chargeMode");
			billingFlow.setEcid("ecid");
			billingFlow.setGuaranteedBandwidth("guaranteedBandwidth");
			billingFlow.setTraffic(1000);
			billingFlow.setPrice(20);
			billingFlow.setTimeStamp(new Date());
			list.add(billingFlow);
			
			idc2BossTask.makeFile(list, "./2017-01.txt");*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

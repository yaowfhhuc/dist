package me.test.util.unix.prococol.ssh2;

public class Ssh2WrapperExample {
	public static void main(String args[]) {
		Ssh2Wrapper ssh = new Ssh2Wrapper();
		try {
			
			long start_time = System.currentTimeMillis();
			
			
			ssh.setLogin("dc");
			ssh.setPassword("dc");
			ssh.setPrompt("[dc@RedhatAS4 ~]$");
			
			ssh.connect("192.168.1.159", 22, 30);
			
			System.out.println(" Ssh2WrapperExample  ----   after login");

//			String result = ssh.send("", 30);
//			System.out.println("result = " + result);
			ssh.waitfor("[dc@RedhatAS4 ~]", 5);
			String result = ssh.send("pwd", 30);
			System.out.println("result = " + result);
			result = ssh.send("ls -l", 30);
			System.out.println("result = " + result);
			result = ssh.send("df -k", 30);
			System.out.println("result = " + result);
			
			//Thread.sleep(10000);

			long end_time = System.currentTimeMillis();

			long waste_time = end_time - start_time;

			System.out.println("waste_time = " + waste_time);

			

			ssh.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

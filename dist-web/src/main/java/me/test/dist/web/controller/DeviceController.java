/**   
* @Title: DeviceController.java 
* @Package me.test.dist.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author yaowf
* @date 2016年6月28日 上午10:58:17 
* @version V1.0   
*/
package me.test.dist.web.controller;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: DeviceController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author yaowf
 * @date 2016年6月28日 上午10:58:17
 * 
 */
@Controller
public class DeviceController {

	@RequestMapping("/detect-device")
	public @ResponseBody String detectDevice(Device device) {
		String deviceType = "unknown";
		if (device.isNormal()) {
			deviceType = "normal";
		} else if (device.isMobile()) {
			deviceType = "mobile";
		} else if (device.isTablet()) {
			deviceType = "tablet";
		}
		return "Hello " + deviceType + " browser!";
	}
}

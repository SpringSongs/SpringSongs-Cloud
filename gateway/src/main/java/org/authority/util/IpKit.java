/**
 * 请勿将俱乐部专享资源复制给其他人，保护知识产权即是保护我们所在的行业，进而保护我们自己的利益
 * 即便是公司的同事，也请尊重 JFinal 作者的努力与付出，不要复制给同事
 * 
 * 如果你尚未加入俱乐部，请立即删除该项目，或者现在加入俱乐部：http://jfinal.com/club
 * 
 * 俱乐部将提供 jfinal-club 项目文档与设计资源、专用 QQ 群，以及作者在俱乐部定期的分享与答疑，
 * 价值远比仅仅拥有 jfinal club 项目源代码要大得多
 * 
 * JFinal 俱乐部是五年以来首次寻求外部资源的尝试，以便于有资源创建更加
 * 高品质的产品与服务，为大家带来更大的价值，所以请大家多多支持，不要将
 * 首次的尝试扼杀在了摇篮之中
 */

package org.authority.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

/**
 * IpKit 获取 ip 地址的工具类
 */
public class IpKit {

	public static Map<String, String> getBrowerInfo(HttpServletRequest request) {
		Map<String, String> browerMap = new HashMap<String, String>();
		String ua = request.getHeader("User-Agent");
		UserAgent userAgent = UserAgent.parseUserAgentString(ua);
		Browser browser = userAgent.getBrowser();
		OperatingSystem os = userAgent.getOperatingSystem();
		String system = os.getName();
		browerMap.put("os", system);
		String browserName = browser.getName();
		browerMap.put("browserName", browserName);
		return browerMap;
	}

	public static String SystemName() {
		try {
			Properties props = System.getProperties();
			return props.getProperty("os.name");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "N/A";
	}

	public static String SystemVersion() {
		try {
			Properties props = System.getProperties();
			return props.getProperty("os.version");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "N/A";
	}

	public static String getRealIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getRealIpV2(HttpServletRequest request) {
		String accessIP = request.getHeader("x-forwarded-for");
		if (null == accessIP)
			return request.getRemoteAddr();
		return accessIP;
	}

	/**
	 * 获取本机 ip
	 * 
	 * @return 本机IP
	 */
	public static String getLocalIp() throws SocketException {
		String localip = null; // 本地IP，如果没有配置外网IP则返回
		String netip = null; // 外网IP

		Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress ip = null;
		boolean finded = false; // 是否找到外网IP
		while (netInterfaces.hasMoreElements() && !finded) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
					netip = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					localip = ip.getHostAddress();
				}
			}
		}

		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}

	public static String getMacAddr() {
		try {
			// 首先获取想要查看的ip地址，这个地址唯一对应一个网卡信息
			InetAddress ip = InetAddress.getLocalHost();
			// 根据ip地址获得对应的网卡信息
			NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ip);
			// 获取网卡的mac地址字节数组，这个字节数组的长度是6，读者可以自行断点查看
			byte[] macAddr = networkInterface.getHardwareAddress();
			// 将字节数组转成16进制表示
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < macAddr.length; i++) {
				// 将每个字节的值转为16进制数
				String byteToHex = Integer.toHexString(macAddr[i] & 0xff);
				sb.append(byteToHex);
				// 使用-来区分每个字节的16进制数表示
				if (i != macAddr.length - 1) {
					sb.append("-");
				}
			}
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException("get mac addr error", e);
		}
	}
}

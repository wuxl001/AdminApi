package cn.szag.oms.manager.common.domain.manager;

public class FtpDataSource {
	private static String ip;
	private static String user;
	private static String password;
	private static Integer port;
	private static String filePath;
	public static String getIp() {
		return ip;
	}
	public static void setIp(String ip) {
		FtpDataSource.ip = ip;
	}
	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		FtpDataSource.user = user;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		FtpDataSource.password = password;
	}
	
	public static Integer getPort() {
		return port;
	}
	public static void setPort(Integer port) {
		FtpDataSource.port = port;
	}
	public static String getFilePath() {
		return filePath;
	}
	public static void setFilePath(String filePath) {
		FtpDataSource.filePath = filePath;
	}
	
}

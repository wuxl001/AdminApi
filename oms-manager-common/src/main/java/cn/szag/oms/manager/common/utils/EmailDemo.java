package cn.szag.oms.manager.common.utils;

import cn.szag.oms.manager.common.domain.manager.ManagerNotice;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.sun.mail.util.MailSSLSocketFactory;

public class EmailDemo {

	// 发件人的 邮箱  密码（替换为自己的邮箱和密码
	// PS: 某些邮箱服务器为了增加邮箱本身密码的安全性， SMTP 客户端设置了独立密码（有的邮箱称为授权码”）,
	// 对于启了独立密码的邮, 这里的邮箱密码必使用这个独立密码（授权码）
	public static String myEmailAccount = "oms@szag.cn";
	public static String myEmailPassword = "oms2019.cn";
	public static String rise = "泛亚物流";// 邮件抬头

	// 发件人邮箱的 SMTP 服务器地, 必须准确, 不同邮件服务器地不同, (只是, 绝非绝对)格式:
	// smtp.xxx.com
	// 网易163邮箱 SMTP 服务器地: smtp.163.com aelvqumzubxeghbg
	public static String myEmailSMTPHost = "mail.szag.cn";
	// public static String myEmailSMTPHost163 = "smtp.163.com";
	// 收件人邮箱（替换为自己知道的有效邮箱
	public static String receiveMailAccount = "1097950045@qq.com";
	public String code;
	// public void sendEmail(){
	// createEmail();
	// send();
	// }

	public void createEmail() {
		try {
			// 1. 创建封邮
			Properties props = new Properties(); // 用于连接邮件服务器的参数配置（发送邮件时才需要用到）
			Session session = Session.getInstance(props); // 根据参数配置，创建会话对象（为了发邮件准备的
			MimeMessage message = new MimeMessage(session); // 创建邮件对象

			/*
			 * 也可以根据已有的eml邮件文件创建 MimeMessage 对象 MimeMessage message = new
			 * MimeMessage(session, new FileInputStream("MyEmail.eml"));
			 */

			// 2. From: 发件
			// 其中 InternetAddress 的三个参数分别为: 邮箱, 显示的昵(只用于显, 没有特别的要),
			// 昵称的字符集编码
			// 真正要发送时, 邮箱必须是真实有效的邮箱
			message.setFrom(new InternetAddress("cattsoft_luokun@163.com", "USER_AA", "UTF-8"));

			// 3. To: 收件
			message.setRecipient(MimeMessage.RecipientType.TO,
					new InternetAddress("308140902@qq.com", "USER_CC", "UTF-8"));
					// To: 增加收件人（可）
					// message.addRecipient(MimeMessage.RecipientType.TO, new
					// InternetAddress("dd@receive.com", "USER_DD", "UTF-8"));
					// Cc: 抄（可）
					// message.setRecipient(MimeMessage.RecipientType.CC, new
					// InternetAddress("ee@receive.com", "USER_EE", "UTF-8"));
					// Bcc: 密（可）
					// message.setRecipient(MimeMessage.RecipientType.BCC, new
					// InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));

			// 4. Subject: 邮件主题
			message.setSubject("TEST邮件主题", "UTF-8");

			// 5. Content: 邮件正文（可以使用html标签
			message.setContent("TEST这是邮件正文。", "text/html;charset=UTF-8");

			// 6. 设置显示的发件时
			message.setSentDate(new Date());

			// 7. 保存前面的设
			message.saveChanges();

			// 8. 将该邮件保存到本
			OutputStream out = new FileOutputStream("email/MyEmail.eml");
			message.writeTo(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private ExecutorService executor = Executors.newCachedThreadPool();

	public void send(final String receiveMailAccount, final String emailSubject, final String emailContent) {
		executor.submit(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					// 1. 创建参数配置, 用于连接邮件服务器的参数配置
					Properties props = new Properties(); // 参数配置
					props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求
					props.setProperty("mail.smtp.host", myEmailSMTPHost); // 发件人的邮箱
																			// SMTP
																			// 服务器地
					props.setProperty("mail.smtp.auth", "true"); // 要请求认

					// PS: 某些邮箱服务器要 SMTP 连接要使 SSL 安全认证 (为了提高安全,
					// 邮箱支持SSL连接, 也可以自己开),
					// 如果无法连接邮件服务, 仔细查看控制台打印的 log, 如果有有类似 “连接失, 要求 SSL
					// 安全连接 等错,
					// 打开下面 /* ... */ 之间的注释代,  SSL 安全连接
					/*
					 * // SMTP 服务器的端口 ( SSL 连接的端口一般默认为 25, 可以不添, 如果启了 SSL
					 * 连接, // 要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助, //
					 * QQ邮箱的SMTP(SLL)端口465587, 其他邮箱自行去查) final String
					 * smtpPort = "465"; props.setProperty("mail.smtp.port",
					 * smtpPort);
					 * props.setProperty("mail.smtp.socketFactory.class",
					 * "javax.net.ssl.SSLSocketFactory");
					 * props.setProperty("mail.smtp.socketFactory.fallback",
					 * "false");
					 * props.setProperty("mail.smtp.socketFactory.port",
					 * smtpPort);
					 */

					// 2. 根据配置创建会话对象, 用于和邮件服务器交互
					Session session = Session.getInstance(props);
					session.setDebug(true); // 设置为debug模式, 可以查看详细的发 log

					// 3. 创建封邮
					MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount, emailSubject,
							emailContent);

					// 4. 根据 Session 获取邮件传输对象
					Transport transport = session.getTransport();

					// 5. 使用 邮箱账号  密码 连接邮件服务, 这里认证的邮箱必须与 message 中的发件人邮箱一,
					// 否则报错
					//
					// PS_01: 成败的判断关键在此一, 如果连接服务器失, 都会在控制台输出相应失败原因 log,
					// 仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链, 根据给出的错
					// 类型到对应邮件服务器的帮助网站上查看具体失败原因
					//
					// PS_02: 连接失败的原因常为以下几, 仔细查代:
					// (1) 邮箱没有 SMTP 服务;
					// (2) 邮箱密码错误, 例如某些邮箱启了独立密码;
					// (3) 邮箱服务器要求必须要使用 SSL 安全连接;
					// (4) 请求过于频繁或其他原, 被邮件服务器拒绝服务;
					// (5) 如果以上几点都确定无, 到邮件服务器网站查找帮助
					//
					// PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明
					transport.connect(myEmailAccount, myEmailPassword);

					SpiltTest st = new SpiltTest();
					String str = emailContent;
					String filename = "";
					if (str.indexOf("base64") > 0) {
						for (int i = 0; i < str.split("base64,").length; i++) {
							if (i != 0) {
								String string = str.split("base64,")[i].split("/>")[0];
								st.base2Img(string, "//szag-oms-images//" + i + ".jpeg");
								filename += "//szag-oms-images//" + i + ".jpeg,";
							}
						}
					}
					String results = str.replaceAll("\\<.*>", "");
					sendMailss(receiveMailAccount, emailSubject, results, filename);
					// 附件部分
					// BodyPart messageBodyPart = new MimeBodyPart();
					////
					// messageBodyPart.setText("ddddddd");
					// messageBodyPart = new MimeBodyPart();
					// // 设置要发送附件的文件路径
					// DataSource source = new
					// FileDataSource("C://Users//123//Desktop//002.txt");
					// messageBodyPart.setDataHandler(new DataHandler(source));
					// // 创建多重消息
					// Multipart multipart = new MimeMultipart();
					////
					// multipart.addBodyPart(messageBodyPart);
					// message.setContent(multipart);

					// Multipart mainPart = new MimeMultipart();
					// // 创建一个包含HTML内容的MimeBodyPart
					// BodyPart html = new MimeBodyPart();
					// // 设置HTML内容
					// html.setContent("<h1>11111111<h1>", "text/html;
					// charset=utf-8");
					// mainPart.addBodyPart(html);
					// message.setContent(multipart);

					// 6. 发邮, 发到有的收件地址, message.getAllRecipients()
					// 获取到的是在创建邮件对象时添加的有收件人, 抄人, 密人
					// transport.sendMessage(message,
					// message.getAllRecipients());

					// 7. 关闭连接
					transport.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});

	}

	public static boolean sendMailss(final String receiveMailAccount, final String emailSubject, String results,
			String filename) throws GeneralSecurityException {
		if (StringUtils.isEmpty(receiveMailAccount)) {
			return false;
		}

		// 发件人电子邮箱
		final String from = myEmailAccount;
		// 发件人电子邮箱密码
		final String pass = myEmailPassword;

		// 指定发送邮件的主机为 smtp.qq.com
		String host = myEmailSMTPHost; // 邮件服务器

		// 获取系统属性
		Properties properties = System.getProperties();

		// 设置邮件服务器
		properties.setProperty("mail.smtp.host", host);

		properties.put("mail.smtp.auth", "true");
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);
		// 获取默认session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() { // qq邮箱服务器账户、第三方登录授权码
				return new PasswordAuthentication(from, pass); // 发件人邮件用户名、密码
			}
		});
		session.setDebug(true);

		try {
			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);

			// Set From: 头部头字段
			message.setFrom(new InternetAddress(from));

			// Set To: 头部头字段
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveMailAccount));

			// Set Subject: 主题文字
			message.setSubject(emailSubject);

			// 创建消息部分
			BodyPart messageBodyPart = new MimeBodyPart();

			// 消息
			messageBodyPart.setText(results);

			// 创建多重消息
			Multipart multipart = new MimeMultipart();

			// 设置文本消息部分
			multipart.addBodyPart(messageBodyPart);

			// 附件部分
			// messageBodyPart = new MimeBodyPart();
			// 设置要发送附件的文件路径
			// DataSource source = new FileDataSource(filename);
			// messageBodyPart.setDataHandler(new DataHandler(source));

			// messageBodyPart.setFileName(filename);
			// 处理附件名称中文（附带文件路径）乱码问题
			// messageBodyPart.setFileName(MimeUtility.encodeText(filename));
			// multipart.addBodyPart(messageBodyPart);
			String[] file = filename.split(",");
			if(!"".equals(filename)){
				for (int i = 0; i < file.length; i++) {
					BodyPart messageBodyPart1 = new MimeBodyPart();
					messageBodyPart1 = new MimeBodyPart();
					DataSource source1 = new FileDataSource(filename.split(",")[i]);
					messageBodyPart1.setDataHandler(new DataHandler(source1));
					messageBodyPart1.setFileName(MimeUtility.encodeText(filename.split(",")[i]));
					multipart.addBodyPart(messageBodyPart1);
				}
			}
			
			// 发送完整消息
			message.setContent(multipart);

			// 发送消息
			Transport.send(message);
			// System.out.println("Sent message successfully....");
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 创建封只包含文本的简单邮
	 *
	 * @param session
	 *            和服务器交互的会
	 * @param sendMail
	 *            发件人邮
	 * @param receiveMail
	 *            收件人邮
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,
			String emailSubject, String emailContent) throws Exception {
		// 1. 创建封邮
		MimeMessage message = new MimeMessage(session);

		// 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称
		message.setFrom(new InternetAddress(sendMail, "OMS", "UTF-8"));

		// 3. To: 收件人（可以增加多个收件人抄送密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "注册用户", "UTF-8"));
		// Cc: 抄（可）
		// message.setRecipient(MimeMessage.RecipientType.CC, new
		// InternetAddress("lizhizhi@gdcattsoft.com", "USER_EE", "UTF-8"));
		// Cc: 抄（可）
		// message.setRecipient(MimeMessage.RecipientType.CC, new
		// InternetAddress("liangzhe@gdcattsoft.com", "USER_EE", "UTF-8"));

		// 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
		message.setSubject(rise + emailSubject, "UTF-8");

		// 5. Content:
		// 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发内容）
		message.setContent(emailContent, "text/html;charset=UTF-8");

		// 6. 设置发件时间
		message.setSentDate(new Date());

		// 7. 保存设置
		message.saveChanges();

		return message;
	}

	public boolean checkout(String email) {
		String rule = "[\\w!#$%&'*+/=^_`{|}~-]+(:\\.[\\w!#$%&'*+/=^_`{|}~-]+)*@(:[\\w](:[\\w-]*[\\w])\\.)+[\\w](:[\\w-]*[\\w])";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(rule);
		matcher = pattern.matcher(email);
		if (matcher.matches())
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		// createMimeMessage(session, sendMail, receiveMail);
		EmailDemo ed = new EmailDemo();
		ManagerNotice managerNotice = new ManagerNotice();
		managerNotice.setCreatetime(new Date());
		// ed.send("124107529@qq.com", "ooo", "test");
		String receive = "1097950045@qq.com";
		String subject = "邮件主题";
		String msg = "邮件内容";
		String filename = "C://Users//123//Desktop//b.jpeg,C://Users//123//Desktop//2.jpeg";
		System.out.println(filename.replaceAll("\\<.*>", ""));
		try {
			// SpiltTest st = new SpiltTest();
			//
			// for (int i = 0; i < str.split("base64,").length; i++) {
			// String string = str.split("base64,")[i].split("/>")[0];
			// st.base2Img(string, "C://Users//123//Desktop//"+i+".jpeg");
			// }
			// String result = str.split("base64,")[1].split("/>")[0];
			// String results = str.replaceAll("\\<.*>", "");
			// //results = results.substring(3,results.length()-4);
			// st.base2Img(result, filename);
			// ed.sendMailss(receive, subject, results, filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

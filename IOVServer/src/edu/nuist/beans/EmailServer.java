package edu.nuist.beans;



import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class EmailServer {
	public static void main(String[] args) throws AddressException, MessagingException, GeneralSecurityException {
		Email email=new Email("18262602537@sina.cn","邮箱测试","成功");
//		Email email=new Email();
//		email.setReceiver("835745627@qq.com");
//		email.setTitle("邮箱测试");
//		email.setContent("成功");
		EmailServer.sendEmail(email);
		}
	public static void sendEmail(Email email) throws GeneralSecurityException, AddressException, MessagingException{
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");//发送邮件协议
		properties.setProperty("mail.smtp.auth", "true");//需要验证
		// properties.setProperty("mail.debug", "true");//设置debug模式 后台输出邮件发送的过程
		Session session = Session.getInstance(properties);
		session.setDebug(true);//debug模式
		
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
	    sf.setTrustAllHosts(true);
	    properties.put("mail.smtp.ssl.enable", "true");
	    properties.put("mail.smtp.ssl.socketFactory", sf);
		
		//邮件信息
		Message messgae = new MimeMessage(session);
		messgae.setFrom(new InternetAddress("835745627@qq.com"));//设置发送人
		messgae.setText(email.getContent());//设置邮件内容
		messgae.setSubject(email.getTitle());//设置邮件主题
		
		//发送邮件
		Transport tran = session.getTransport();
		//tran.connect("smtp.sohu.com", 25, "wuhuiyao@sohu.com", "xxxx");//连接到新浪邮箱服务器
		//tran.connect("smtp.sina.com", 25, "whyao@sina.cn", "xxxxxxx");//连接到新浪邮箱服务器
		tran.connect("smtp.qq.com", "835745627@qq.com", "wwnlprhdafylbedh");//连接到QQ邮箱服务器
		tran.sendMessage(messgae, new Address[]{ new InternetAddress(email.getReceiver())});//设置邮件接收人
		tran.close();
	}
}

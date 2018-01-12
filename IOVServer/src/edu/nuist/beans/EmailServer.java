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
		Email email=new Email("18262602537@sina.cn","�������","�ɹ�");
//		Email email=new Email();
//		email.setReceiver("835745627@qq.com");
//		email.setTitle("�������");
//		email.setContent("�ɹ�");
		EmailServer.sendEmail(email);
		}
	public static void sendEmail(Email email) throws GeneralSecurityException, AddressException, MessagingException{
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");//�����ʼ�Э��
		properties.setProperty("mail.smtp.auth", "true");//��Ҫ��֤
		// properties.setProperty("mail.debug", "true");//����debugģʽ ��̨����ʼ����͵Ĺ���
		Session session = Session.getInstance(properties);
		session.setDebug(true);//debugģʽ
		
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
	    sf.setTrustAllHosts(true);
	    properties.put("mail.smtp.ssl.enable", "true");
	    properties.put("mail.smtp.ssl.socketFactory", sf);
		
		//�ʼ���Ϣ
		Message messgae = new MimeMessage(session);
		messgae.setFrom(new InternetAddress("835745627@qq.com"));//���÷�����
		messgae.setText(email.getContent());//�����ʼ�����
		messgae.setSubject(email.getTitle());//�����ʼ�����
		
		//�����ʼ�
		Transport tran = session.getTransport();
		//tran.connect("smtp.sohu.com", 25, "wuhuiyao@sohu.com", "xxxx");//���ӵ��������������
		//tran.connect("smtp.sina.com", 25, "whyao@sina.cn", "xxxxxxx");//���ӵ��������������
		tran.connect("smtp.qq.com", "835745627@qq.com", "wwnlprhdafylbedh");//���ӵ�QQ���������
		tran.sendMessage(messgae, new Address[]{ new InternetAddress(email.getReceiver())});//�����ʼ�������
		tran.close();
	}
}

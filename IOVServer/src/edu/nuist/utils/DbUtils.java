package edu.nuist.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtils {

	
	private DbUtils(){}
	private static String driverclass;
	private static String dburl;
	private static String user;
	private static String password;
	
	
	private static  ThreadLocal<Connection> tl=new ThreadLocal<Connection>();
	static{
		InputStream in =DbUtils.class.getClassLoader()
				       .getResourceAsStream("db-config.properties");
		Properties prop=new Properties();
		//��InputStream����װ��һ��Properties
		try {
			prop.load(in);
			driverclass=prop.getProperty("driverClass");
			dburl=prop.getProperty("dburl");
			user=prop.getProperty("user");
			password=prop.getProperty("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(in!=null){
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		
	}
	
	public static Connection getConnection(){
		Connection con=null;
		try {
			Class.forName(driverclass);
			con=DriverManager.getConnection(dburl, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				throw new Exception("��������س����쳣������������ݿ������Ƿ��룬�Լ�·���Ƿ�д��");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				throw new Exception("��ȡ���ݿ����ӳ����쳣�������������������Ϣ��");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return con;
	}
	
	public static Connection getThreadLocalConnection(){
		Connection con=null;
		//�ȴ�ThreadLocal��ȡ���ӣ����û���ڴ����������õ���ǰ�߳�����
		con=tl.get();
		if(con==null){
			con=getConnection();
			tl.set(con);
		}
		return con;
	}
	
	
	public static void beginTransaction(){
		Connection con=getThreadLocalConnection();
		//ĬȻ������ύ��ʽΪ�Զ��ģ�Ϊ�˽����������,���ڱ���ֶ�
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	public static void commitTransaction(){
		Connection con=tl.get();
		
		if(con!=null){
			try {
				con.commit();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}finally{
				close(con);
			}
		}
	}
	
	public static void rollbackTransaction(){
		Connection con=tl.get();
		if(con!=null){
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}finally{
				close(con);
			}
		}
	}
	

//	public static void close(){
//		Connection con=tl.get();
//		if(con!=null){
//			//�����Ӵӵ�ǰ�߳����Ƴ�
//			tl.remove();
//			close(con);
//		}
//	}
	
	public static void close(Connection con){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection con,Statement st){
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close(con);
	}
	
	
	public static void close(Connection con,Statement st,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		close(con,st);
	}
	
}

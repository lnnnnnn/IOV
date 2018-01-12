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
		//把InputStream流包装成一个Properties
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
				throw new Exception("驱动类加载出现异常，请检查你的数据库驱动是否导入，以及路径是否写错！");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				throw new Exception("获取数据库连接出现异常，请检查你的连接配置信息！");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return con;
	}
	
	public static Connection getThreadLocalConnection(){
		Connection con=null;
		//先从ThreadLocal获取连接，如果没有在创建，并设置到当前线程中来
		con=tl.get();
		if(con==null){
			con=getConnection();
			tl.set(con);
		}
		return con;
	}
	
	
	public static void beginTransaction(){
		Connection con=getThreadLocalConnection();
		//默然事物的提交方式为自动的，为了进行事物管理,现在变成手动
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public static void commitTransaction(){
		Connection con=tl.get();
		
		if(con!=null){
			try {
				con.commit();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}finally{
				close(con);
			}
		}
	}
	

//	public static void close(){
//		Connection con=tl.get();
//		if(con!=null){
//			//把连接从当前线程中移除
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

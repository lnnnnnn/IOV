
package edu.nuist.beans;

import java.util.LinkedList;

import edu.nuist.abenablebean.util.DBTableInitor;
import edu.nuist.dbenablebean.*;
import edu.nuist.dbenablebeananntation.Relation;
import edu.nuist.dbenablebeananntation.SP;;


//看一下这个简单User例子，对数据库的增删改查
@SP(
		//table指定user表
		table="user", 
		
		//使用的sql语句
		sps="queryByName : select * from {tableName} where name={name};"
			+ "loginSQL : select * from {tableName} where name={name} and password={password};",
		
		//先建表
		create = "create table user (" +
				"id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
				"name varchar(255), password varchar(255),email varchar(255) );"		 
	)

//@SP这是一个标注，就是告诉框架一这个BEAN要做哪些数据库操作



public class User extends DBEnableBean{

	private String name;
	private String password;
	private String email;
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String name, String password, String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	
	
	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
//		User u = new User();
//	 
//		u.setName("test_zhang");
//		u.setPassword("123456");//初始化
//    	new DBTableInitor(User.class, u);//生成一张表  
//		
		
//		
    	/* String name = "zzz";
    	 String password="123456";
    	 User u = new User();
    	 u.setName(name).setPassword(password).setEmail("757165407@qq.com");
    	 
    		//new DBTableInitor(User.class, u);//生成一张表  
    		
    		
        u.insert();	*/	
//		//插入用户
    	
//    	User u = new User();
//    	u.setName(name);
//    	u.setPassword(password);
//    LinkedList<DBEnableBean> users = u.query("Search");//用到sps中的sql语句了	
//     if(users.size() > 0){
//     u = (User)users.get(0);
//     u.delete();
//     }
    //  删除用户
     
     
    //   User u = new User().setName(name).setPassword(password);
    //   LinkedList<DBEnableBean> users = u.query("Search");
    //		if(users.size() > 0){
    //			u = (User)users.get(0);
    //			u.setPassword("1111");
    //			u.setName("cjx1");
    //		u.update();
    //		}
    	//修改用户
//	
//    	User u = new User();
//    			u.setName(name);
//    			u.setPassword(password);
//		LinkedList<DBEnableBean> users = u.query("loginSQL");
//		if(users.size() > 0){
//			u = (User)users.get(0);
//			//u.delete();
//			System.out.println( "Login Success!!" );
//		}else{
//			System.out.println( "Login Error!!" );
//		}
////		
		//查找用户
		
		
		
	}
	
	
}

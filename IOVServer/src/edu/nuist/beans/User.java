
package edu.nuist.beans;

import java.util.LinkedList;

import edu.nuist.abenablebean.util.DBTableInitor;
import edu.nuist.dbenablebean.*;
import edu.nuist.dbenablebeananntation.Relation;
import edu.nuist.dbenablebeananntation.SP;;


//��һ�������User���ӣ������ݿ����ɾ�Ĳ�
@SP(
		//tableָ��user��
		table="user", 
		
		//ʹ�õ�sql���
		sps="queryByName : select * from {tableName} where name={name};"
			+ "loginSQL : select * from {tableName} where name={name} and password={password};",
		
		//�Ƚ���
		create = "create table user (" +
				"id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
				"name varchar(255), password varchar(255),email varchar(255) );"		 
	)

//@SP����һ����ע�����Ǹ��߿��һ���BEANҪ����Щ���ݿ����



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
//		u.setPassword("123456");//��ʼ��
//    	new DBTableInitor(User.class, u);//����һ�ű�  
//		
		
//		
    	/* String name = "zzz";
    	 String password="123456";
    	 User u = new User();
    	 u.setName(name).setPassword(password).setEmail("757165407@qq.com");
    	 
    		//new DBTableInitor(User.class, u);//����һ�ű�  
    		
    		
        u.insert();	*/	
//		//�����û�
    	
//    	User u = new User();
//    	u.setName(name);
//    	u.setPassword(password);
//    LinkedList<DBEnableBean> users = u.query("Search");//�õ�sps�е�sql�����	
//     if(users.size() > 0){
//     u = (User)users.get(0);
//     u.delete();
//     }
    //  ɾ���û�
     
     
    //   User u = new User().setName(name).setPassword(password);
    //   LinkedList<DBEnableBean> users = u.query("Search");
    //		if(users.size() > 0){
    //			u = (User)users.get(0);
    //			u.setPassword("1111");
    //			u.setName("cjx1");
    //		u.update();
    //		}
    	//�޸��û�
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
		//�����û�
		
		
		
	}
	
	
}

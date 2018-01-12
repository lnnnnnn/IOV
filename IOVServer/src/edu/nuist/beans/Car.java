package edu.nuist.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import edu.nuist.abenablebean.util.DBTableInitor;
import edu.nuist.dbenablebean.DBEnableBean;
import edu.nuist.dbenablebeananntation.Relation;
import edu.nuist.dbenablebeananntation.SP;
import edu.nuist.utils.DbUtils;
//在看这个car例子
@SP(
		table="car", 
		sps="queryByBrand : select * from {tableName} where brand={brand};"
			+ "queryByGasLimit : select  * from {tableName} where gas<40; ",
		create = "create table car (" +
				"id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
				"sid varchar(255), brand varchar(255), gas double );"		 
	)

//定义的关系表
public class Car extends DBEnableBean{
	private String sid;
	private String brand;
	
	@Relation(rTable="user_car_relation", sIdCol="carId", dIdCol="userId", coDeal=true)
	private User owner;
	
	private double gas;

	public String getSid() {
		return sid;
	}

	public Car setSid(String sid) {
		this.sid = sid;
		return this;
	}

	public String getBrand() {
		return brand;
	}

	public Car setBrand(String brand) {
		this.brand = brand;
		return this;
	}

	public double getGas() {
		return gas;
	}

	public Car setGas(double gas) {
		this.gas = gas;
		return this;
	}
	
	public User getOwner() {
		return owner;
	}

	public Car setOwner(User owner) {
		this.owner = owner;
		return this;
	}


	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		
		
		//需要将user和car连接起来，多个car可对应一个user，而一个car只能有一个主人，所以需要将之对应起来
		User u = new User();
		u.setName("zzz");
		LinkedList <DBEnableBean> us=u.query("queryByName");
		//u = (User)u.get();
		u=(User)us.get(0);
		
		System.out.println(u.getId());
	/*	Car c = new Car().setBrand("Baniz").setSid("12fvsfsdf").setOwner(u).setGas(50.0);
		new DBTableInitor(Car.class, c);//生成一张表
*/		/*
		LinkedList<DBEnableBean> c = new Car().setBrand("Baniz").query("queryByBrand");
		
		if(c.size()>0){
			Car car = (Car)c.get(0);
			System.out.println( ((User)car.getOwner().get()).getName() );
		}else{
			System.out.println("null result");
			
		}*/
		
//	我们想通过车的gas来查找到这辆车的信息和车主信息
//		String sql = "select * from car where sid = ?";
//		String sql1 = "select * from user where name =?";
//		String sql2 = "select * from user where id =?";
//		String sql3 = "select userid from car_user_relation where carid = ?";
//		String sql4 = "select a.*,c.name from car a ,user_car_relation b ,user c  where a.id = b.carid and b.userid = c.id and Sid = '12fvsfsdf' ";
//	 
//		
//		String diysid= "12fvsfsdf";
//		String diyname = "zhanghx";
//		
//		String name_test;
//		int diyid = 1;
//		
//		
		/*Car car = new Car();
		//User user = new User();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
	
		try {
			
			con=DbUtils.getConnection();
			if(con!=null){
				
				User us=new User();
				ps=con.prepareStatement(sql4);
			 
				rs=ps.executeQuery();
				//遍历结果集对象
				//ps.setString(1, diyname);
				//设置“？”中的变量
				while(rs.next()){
					//显示记录的每个字段值
			         car.setId(rs.getInt(1));
				     car.setSid(rs.getString(2));
				     car.setBrand(rs.getString(3));
				     car.setGas(rs.getDouble(4));
				     us.setName(rs.getString(5));
				}
		     System.out.println(car.getId()+" " +car.getSid()+ " " + car.getBrand()+ " "+ car.getGas()+ " " + us.getName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbUtils.close(con, ps,rs);
		}*/

	}
	

}

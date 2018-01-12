package edu.nuist.beans;

import edu.nuist.abenablebean.util.DBTableInitor;
import edu.nuist.dbenablebean.DBEnableBean;
import edu.nuist.dbenablebeananntation.SP;

@SP(
		table="oilorder", 
		//sps="search:select * from {tablename} where sid = {sid};",
				sps="search:select * from {tablename};",
				/*create = "create table order (" +
						"id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
						"name varchar(255), password varchar(255) );"*/
		create = "create table order(" +
				"id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
				"name varchar(255), carDesc varchar(255),date datetime,gasStation varchar(255),type varchar(255),"+
				"volume varchar(255) );" 
	)
public class Order extends DBEnableBean{
	private String name;
	private String carDesc;
	private String date;
	private String gasStation;
	private String type;
	private String volume;
	public String getName() {
		return name;
	}
	public Order setName(String name) {
		this.name = name;
		return this;
	}
	public String getCarDesc() {
		return carDesc;
	}
	public Order setCarDesc(String carDesc) {
		this.carDesc = carDesc;
		return this;	
	}
	public String getDate() {
		return date;
	}
	public Order setDate(String date) {
		this.date = date;
		return this;
	}
	public String getGasStation() {
		return gasStation;
	}
	public Order setGasStation(String gasStation) {
		this.gasStation = gasStation;
		return this;
	}
	public String getType() {
		return type;
	}
	public Order setType(String type) {
		this.type = type;
		return this;
	}
	public String getVolume() {
		return volume;
	}
	public Order setVolume(String volume) {
		this.volume = volume;
		return this;
	}
	
	public static void main(String[] args){
		Order o=new Order();
		/*o.setName("zzz").setCarDesc("奥迪A4L 苏C26958").setDate("2016-6-6")
		.setGasStation("中油燕宾北邮加油站").setType("97").setVolume("10升");*/
		//o.insert();
		new DBTableInitor(Order.class,o);
		
	}

}

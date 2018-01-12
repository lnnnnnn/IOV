package edu.nuist.beans;

import java.util.ArrayList;
import java.util.LinkedList;

import edu.nuist.dbenablebean.DBEnableBean;
import edu.nuist.dbenablebeananntation.SP;
import net.sf.json.JSONObject;


@SP(
		table="carNet", 
		//sps="search:select * from {tablename} where sid = {sid};",
				sps="search:select * from {tablename} where username={username};"+
		            "queryByHphm:select *from {tablename} where hphm={hphm} and username={username};"+
					"updateByHphm:update {tablename} set symbol={symbol},type={type},"+
		            "classno={classno},engineno={engineno},level={level},mileage={mileage},"+
					"gas_percent={gas_percent},engineP={engineP},transmissionP={transmissionP},"+
		            "lightS={lightS} where hphm={hphm} and username={username};",
						
		create = "create table carNet (" +
				"id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
				"sid varchar(255), brand varchar(255),symbol varchar(255),type varchar(255),"+
				"hphm varchar(255),classno varchar(255),engineno varchar(255),"+ 
				"level varchar(255),mileage double, gas_percent int ,engineP varchar(255),"+
				"transmissionP varchar(255),lightS varchar(255),username varchar(255) );"		 
	)

public class car_net extends DBEnableBean {

	private String sid;
	private String brand;
	private String symbol;
	private String type;
	private String hphm;
	private String classno;
	private String engineno;
	private String level;
	private double mileage;
	private int gas_percent;
	private String  engineP;
	private String transmissionP;
	private String lightS;
	private String username;
	
	
	public String getSid() {
		return sid;
	}

	public car_net setSid(String sid) {
		this.sid = sid;
		return this;
	}

	
	public String getBrand() {
		return brand;
	}

	public car_net setBrand(String brand) {
		this.brand = brand;
		return this;
	}

	public String getSymbol() {
		return symbol;
	}

	public car_net setSymbol(String symbol) {
		this.symbol = symbol;
		return this;
	}

	public String getType() {
		return type;
	}

	public car_net setType(String type) {
		this.type = type;
		return this;
	}

	public String getHphm() {
		return hphm;
	}

	public car_net setHphm(String hphm) {
		this.hphm = hphm;
		return this;
	}

	public String getClassno() {
		return classno;
	}

	public car_net setClassno(String classno) {
		this.classno = classno;
		return this;
	}

	public String getEngineno() {
		return engineno;
	}

	public car_net setEngineno(String engineno) {
		this.engineno = engineno;
		return this;
	}

	public String getLevel() {
		return level;
	}

	public car_net setLevel(String level) {
		this.level = level;
		return this;
	}

	public double getMileage() {
		return mileage;
	}

	public car_net setMileage(double mileage) {
		this.mileage = mileage;
		return this;
	}

	

	
	public int getGas_percent() {
		return gas_percent;
	}

	public car_net setGas_percent(int gas_percent) {
		this.gas_percent = gas_percent;
		return this;
	}

	public String getEngineP() {
		return engineP;
	}

	public car_net setEngineP(String engineP) {
		this.engineP = engineP;
		return this;
	}

	public String getTransmissionP() {
		return transmissionP;
	}

	public car_net setTransmissionP(String transmissionP) {
		this.transmissionP = transmissionP;
		return this;
	}

	public String getLightS() {
		return lightS;
	}

	public car_net setLightS(String lightS) {
		this.lightS = lightS;
		return this;
	}
  
	
    
	

	public String getUsername() {
		return username;
	}

	public car_net setUsername(String username) {
		this.username = username;
		return this;
	}

	public car_net() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public car_net(String sid, String brand, String symbol, String type, String hphm, String classno, String engineno,
			String level, double mileage, int gas_percent, String engineP, String transmissionP, String lightS,
			String username) {
		super();
		this.sid = sid;
		this.brand = brand;
		this.symbol = symbol;
		this.type = type;
		this.hphm = hphm;
		this.classno = classno;
		this.engineno = engineno;
		this.level = level;
		this.mileage = mileage;
		this.gas_percent = gas_percent;
		this.engineP = engineP;
		this.transmissionP = transmissionP;
		this.lightS = lightS;
		this.username = username;
	}
    
	@Override
	public String toString() {
		return "car_net [sid=" + sid + ", brand=" + brand + ", symbol=" + symbol + ", type=" + type + ", hphm=" + hphm
				+ ", classno=" + classno + ", engineno=" + engineno + ", level=" + level + ", mileage=" + mileage
				+ ", gas_percent=" + gas_percent + ", engineP=" + engineP + ", transmissionP=" + transmissionP
				+ ", lightS=" + lightS + ", username=" + username + "]";
	}

	public void updateCarByHphm(car_net now){
		
		// String hphm=car.getHphm();
		// car_net car=new car_net();
		// this.setHphm(hphm);
		 LinkedList<DBEnableBean> cs=now.query("queryByHphm");
		 if(cs.size()>0){
			car_net c=(car_net) cs.get(0);
			c.setLightS(now.getLightS());
			c.setMileage(now.getMileage());
			c.setTransmissionP(now.getTransmissionP());
			c.setEngineP(now.getEngineP());
			c.setLevel(now.getLevel());
			c.setGas_percent(now.getGas_percent());
			//System.out.println(c.getLightS());
			c.update();
		 }
		 
	 }
	
	public ArrayList<String> getMaintainInfo(car_net pre,car_net now ){
		//String[] res=new String[3];
		ArrayList<String> list=new ArrayList<String>();
		if(now.getGas_percent()<20) list.add("您的车辆"+now.getBrand()+now.getType()+"油量仅剩"+gas_percent+"%,需要加油!");
		if(now.getMileage()/15000>0&&(now.getMileage()/15000)>(pre.getMileage()/15000)){
			list.add("您的车辆"+now.getBrand()+now.getType()+"走了很多路了,需要进行维护!");
		}
		if(now.getEngineP().equals("异常")) list.add("您的车辆"+now.getBrand()+now.getType()+"发动机异常,需要维修!");
		if(now.getTransmissionP().equals("异常")) list.add("您的车辆"+now.getBrand()+now.getType()+"变速器异常,需要维修!");
		if(now.getLightS().equals("坏")) list.add("您的车辆"+now.getBrand()+now.getType()+"车灯损坏,需要维修!");
		
		return list;
	}
	
	public String getJsonArrByUsername(String username){
		
		String res="{\"data\":[";
		car_net c=new car_net();
		c.setUsername(username);
		 LinkedList<DBEnableBean> cs=c.query("search");
		 int len=cs.size();
		 if(len>0){
			 for(int i=0;i<len-1;i++){
				   c=(car_net) cs.get(i);
				   JSONObject obj=JSONObject.fromObject(c);
				   String str=obj.toString();
				   System.out.println(str);
				   res+=str+",";
			 }
			 c=(car_net) cs.get(len-1);
			   JSONObject obj=JSONObject.fromObject(c);
			   String str=obj.toString();
			 res+=str+"]}";
		 }
		 return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
////		
//		car_net carNet = new car_net();
//		
//		carNet.setBrand("�µ�").setSymbol("A4L").
//		setType("���ͳ�").setHphm("��CEV583")
//		.setClassno("64090329").setEngineno("64090329").setLevel("4��4��").setMileage(120)
//		.setGas(0.6);
//		
//		new DBTableInitor(car_net.class, carNet);//����һ�ű�  
//		String sid="1";
//		String brand="auto";
//		String synbol="A4l";
////		
//		carNet.setSid(sid);
//		carNet.setBrand(brand);
//		carNet.setSymbol(synbol);
//		carNet.insert();
	/*	System.out.println("ll");
		car_net c = new car_net();
		LinkedList<DBEnableBean> car = c.query("search");
		//String[] res = new String[999];
		if( car.size()>0 ){
			
			for(int i=0;i<car.size();i++){
				c = (car_net)car.get(i);
				System.out.println(c);
			}
			
			
		}else{
			System.out.println("nonono");
		}*/
	/*	double x=150000.77;
		System.out.println(x>15000);*/
		
		
		
		//carNet.setHphm("浙CEF155");
		/*car_net carNet = new car_net();
		
		
		carNet.setBrand("宝马").setSymbol("").
		setType("3系").setHphm("浙CEF155")
		.setClassno("64090329").setEngineno("64090399").setLevel("四车五座").setMileage(30000)
		.setGas_percent(10).setEngineP("坏").setLightS("坏").setTransmissionP("坏");
		carNet.updateCarByHphm(carNet);*/
		 //carNet.query("updateByHphm");
		//carNet.update();
	//carNetcarNet.update();
		
		/*car_net car=new car_net();
		car.setBrand("宝马").setSymbol("").
		setType("3系").setHphm("浙CEF155")
		.setClassno("64090329").setEngineno("64090399").setLevel("四车五座").setMileage(60000)
		.setGas_percent(10).setEngineP("异常").setLightS("坏").setTransmissionP("异常");
		
		car_net pre=(car_net)car.query("queryByHphm").get(0);
		
		car.updateCarByHphm(car);
		
		ArrayList<String> infoList=new ArrayList<String>();
		infoList=car.getMaintainInfo(pre, car);
		String ans="";
		for(int i=0;i<infoList.size()-1;i++){ans+=infoList.get(i)+"&";}
		ans+=infoList.get(infoList.size()-1);
		System.out.println(ans);*/
		
		
		/*String username=pre.getUsername();
		System.out.println(username);
		User u=new User();
		u.setName(username);
		String email=((User)(u.query("queryByName").get(0))).getEmail();
		System.out.println(email);
		Email em=new Email(email,"来自车联网的维护信息",res);
		try {
			EmailServer.sendEmail(em);
		} catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		car_net car=new car_net();
		System.out.println(car.getJsonArrByUsername("zzj"));
	}

}

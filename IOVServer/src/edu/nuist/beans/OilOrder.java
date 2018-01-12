package edu.nuist.beans;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import edu.nuist.abenablebean.util.DBTableInitor;
import edu.nuist.dbenablebean.DBEnableBean;
import edu.nuist.dbenablebeananntation.SP;
import net.sf.json.JSONObject;
@SP(
		table="oilorder", 
		//sps="search:select * from {tablename} where sid = {sid};",
				sps="search:select * from {tablename} where username={username};"+
		            "searchById:select * from {tablename} where id={id};"+
		                 "reviseStatus:update oilorder set finished=true where id={id}",
				/*create = "create table order (" +
						"id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
						"name varchar(255), password varchar(255) );"*/
		create = "create table oilorder(" +
				"id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
				"finished bool,name varchar(255), carDesc varchar(255),date datetime,gasStation varchar(255),type varchar(255),"+
				"volume varchar(255),money double,barcodeSrc varchar(255),username varchar(255) );" 
	)
public class OilOrder extends DBEnableBean {
	private boolean   finished; 
	
	private String name;
	private String carDesc;
	private String date;
	private String gasStation;
	private String type;
	private String volume;
	private double money;
	private String barcodeSrc;
	private String username;
	
	
	
	public OilOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
    
	

	
   






	
	@Override
	public String toString() {
		return "OilOrder [finished=" + finished + ", name=" + name + ", carDesc=" + carDesc + ", date=" + date
				+ ", gasStation=" + gasStation + ", type=" + type + ", volume=" + volume + ", money=" + money
				+ ", barcodeSrc=" + barcodeSrc + ", username=" + username + "]";
	}














	private static String jsonString(String s){
		
		//System.out.println(s);
        char[] temp = s.toCharArray();       
        int n = temp.length;
        for(int i =0;i<n;i++){
           /* if(temp[i]==':'&&temp[i+1]=='"'){
                    for(int j =i+2;j<n;j++){*/
                        if(temp[i]=='"'){
                           /* if(temp[j+1]!=',' &&  temp[j+1]!='}'){
                                temp[j]='~';
                            }else if(temp[j+1]==',' ||  temp[j+1]=='}'){
                                break ;
                            }*/
                        	temp[i]='“';
                       // }
                   // }   
            }
        }       
        return new String(temp);
    }

	public String toJsonString() {
		//java.sql.Date sdate= java.sql.Date.valueOf(date);
		//System.out.println(sdate.toString());
		date=date.split(" ")[0];
		barcodeSrc=jsonString(barcodeSrc);
		System.out.println(barcodeSrc);
		//String string = "A B C";//这是一个字符串
		//String [] s = string.split(" ")
		//System.out.println(s[0]);
		return "{\"id\":" + +getId() +", "+"\"finished\":" +finished+", "+"\"name\":" + "\""+name +"\","+"\"carDesc\":" + "\""+carDesc +"\","+"\"date\":" + "\""+date +"\","+"\"gasStation\":" + "\""+gasStation +"\","
				+ "\"type\":" + "\""+type +"\","+"\"volume\":" + "\""+volume +"\","+"\"money\":" +money+","+"\"barcodeSrc\":" + "\""+barcodeSrc +"\"}";
	}


	





	
	public OilOrder(boolean finished, String name, String carDesc, String date, String gasStation, String type,
			String volume, double money, String barcodeSrc, String username) {
		super();
		this.finished = finished;
		this.name = name;
		this.carDesc = carDesc;
		this.date = date;
		this.gasStation = gasStation;
		this.type = type;
		this.volume = volume;
		this.money = money;
		this.barcodeSrc = barcodeSrc;
		this.username = username;
	}

	public boolean isFinished() {
		return finished;
	}
	public OilOrder setFinished(boolean finished) {
		this.finished = finished;
		return this;
	}
	public double getMoney() {
		return money;
	}
	public OilOrder setMoney(double money) {
		this.money = money;
		return this;
	}
	public String getName() {
		return name;
	}
	public OilOrder setName(String name) {
		this.name = name;
		return this;
	}
	public String getCarDesc() {
		return carDesc;
	}
	public OilOrder setCarDesc(String carDesc) {
		this.carDesc = carDesc;
		return this;	
	}
	public String getDate() {
		return date;
	}
	public OilOrder setDate(String date) {
		this.date = date;
		
		//this.date = date;
		return this;
	}
	public String getGasStation() {
		return gasStation;
	}
	public OilOrder setGasStation(String gasStation) {
		this.gasStation = gasStation;
		return this;
	}
	public String getType() {
		return type;
	}
	public OilOrder setType(String type) {
		this.type = type;
		return this;
	}
	public String getVolume() {
		return volume;
	}
	public OilOrder setVolume(String volume) {
		this.volume = volume;
		return this;
	}
	
	public String getBarcodeSrc() {
		return barcodeSrc;
	}





	public OilOrder setBarcodeSrc(String barcodeSrc) {
		this.barcodeSrc = barcodeSrc;
		return this;
	}
	
	

    public String getUsername() {
		return username;
	}







	public OilOrder setUsername(String username) {
		this.username = username;
		return this;
	}







	public LinkedList<DBEnableBean> getAllOrders(){
    //	OilOrder o=new OilOrder();
    	LinkedList<DBEnableBean> orders=this.query("search");
    	/*if(orders.size()>0) return orders;
    	else{
    		System.out.println("orderisnull");
    	}*/
    	return orders;
    }

   public void reviseStatusById(int id){
	   OilOrder o=new OilOrder();
	   o.setId(id);
	   //o.setFinished(true);
	  
	   LinkedList<DBEnableBean> orders= o.query("searchById");
		//System.out.println(orders.size());
        if( orders.size()>0 ){
        	o=(OilOrder)orders.get(0);
        	o.setFinished(true);
        	//orders.get(0).query("reviseStatus");
        	o.update();
        }
   }

	public static void main(String[] args){
		/*OilOrder o=new OilOrder();
		o.setFinished(false).setName("zzz").setCarDesc("奥迪A4L 苏C26958").setDate("2016-6-6")
		.setGasStation("中油燕宾北邮加油站").setType("97#").setVolume("10升").setMoney(0.01).setBarcodeSrc("http://qr.liantu.com/api.php?w=300&text=").setUsername("zzz");
		o.insert();*/
		//new DBTableInitor(OilOrder.class,o);
		
		
		
		
		/*OilOrder o=new OilOrder();
		o.setUsername("zzz");
		LinkedList<DBEnableBean> orders= o.query("search");
		//System.out.println(orders.size());
         if( orders.size()>0 ){
			
			for(int i=0;i<orders.size();i++){
				OilOrder od = (OilOrder)orders.get(i);
			//	System.out.println(od);
				//JSONObject obj=JSONObject.fromObject(od);
			//	String str=od.toJsonString();
				//if(i==orders.size()-1)res+=str;
				//else res+=str+",";
				System.out.println(od);
				System.out.println(od.toJsonString());
			}
			
			
		}else{
			System.out.println("nonono");
		}*/
		
		/*String res="{\"data\":[";
		//System.out.println("all");
		OilOrder o=new OilOrder();
		o.setUsername("zzx");
		LinkedList<DBEnableBean> orders= o.query("search");
		//System.out.println(orders.size());
         if( orders.size()>0 ){
			
			for(int i=0;i<orders.size();i++){
				OilOrder od = (OilOrder)orders.get(i);
			//	System.out.println(od);
				//JSONObject obj=JSONObject.fromObject(od);
				String str=od.toJsonString();
				if(i==orders.size()-1)res+=str;
				else res+=str+",";
				//System.out.println(od);
			}
			
			
		}else{
			System.out.println("nonono");
		}
          res+="]}";
          System.out.println(res);*/
         
         /*String jsonStr="%7B%22name%22%3A%22%22%2C%22carDesc%22%3A%22%E5%A5%A5%E8%BF%AA%20A4L%20%E8%8B%8FC26958%22%2C%22date%22%3A%222016-6-12%22%2C%22gasStation%22%3A%22%E4%B8%AD%E6%B2%B9%E7%87%95%E5%AE%BE%E5%8C%97%E9%82%AE%E5%8A%A0%E6%B2%B9%E7%AB%99%22%2C%22type%22%3A%2290%23%22%2C%22volume%22%3A%22%E5%8D%87%22%2C%22barcodeSrc%22%3A%22http%3A%2F%2Fqr.liantu.com%2Fapi.php%3Fw%3D300%26text%3D%7B%5C%22name%5C%22%3A%5C%22%5C%22%2C%5C%22carDesc%5C%22%3A%5C%22%E5%A5%A5%E8%BF%AA%20A4L%20%E8%8B%8FC26958%5C%22%2C%5C%22date%5C%22%3A%5C%222016-6-12%5C%22%2C%5C%22gasStation%5C%22%3A%5C%22%E4%B8%AD%E6%B2%B9%E7%87%95%E5%AE%BE%E5%8C%97%E9%82%AE%E5%8A%A0%E6%B2%B9%E7%AB%99%5C%22%2C%5C%22type%5C%22%3A%5C%2290%23%5C%22%2C%5C%22volume%5C%22%3A%5C%22%E5%8D%87%5C%22%7D%22%7D";
        		 
        		 String ans="";
			try {
				ans = URLDecoder.decode(jsonStr,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(ans);
			
			JSONObject obj=JSONObject.fromObject(ans);
			OilOrder o=new OilOrder(obj.getString("name"),obj.getString("carDesc"),obj.getString("date"),obj.getString("gasStation"),
					obj.getString("type"),obj.getString("volume"),obj.getString("barcodeSrc"),"zzj"
					);
			
			o.insert();	*/
		
		/*
		 * change status
		 */
		//OilOrder o=new OilOrder();
		//o.reviseStatusById(4);
		
		 Date date=new Date();
		 DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		 String time=format.format(date);
		 System.out.println(time);
	}

	
}

package edu.nuist.dbenablebean.test;

import java.text.MessageFormat;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import edu.nuist.abenablebean.util.FileUtil;
import edu.nuist.dbenablebean.DBEnableBean;
import edu.nuist.dbenablebeananntation.ColDef;
import edu.nuist.dbenablebeananntation.SP;


@SP( sps="",
	 table = "user")
public class user extends DBEnableBean{
	private String name;

	private String phone;
	private String area;
	@ColDef( "descTxt" )
	private String desc;
	@ColDef( off=true )
	private String url;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static String getJsontemp() {
		return jsonTemp;
	}
	
	@ColDef( off=true )
	public static final String jsonTemp = " '{' \"name\":\"{0}\", \"phone\":\"{1}\", \"area\":\"{2}\", \"desc\":\"{3}\", \"url\":\"{4}\" '}'";
	
	public String toJSON(){
		return MessageFormat.format( jsonTemp, this.name, this.phone, this.area, this.desc, this.url);
	}
	
	public user(String name, String phone, String area, String desc, String url){
		this.name = name;
		this.phone = phone;
		this.area = area;
		this.desc = desc;
		this.url = url;
	}
	
	public user(String str){
		String tmp = "";
		tmp = str;
		tmp= tmp.substring( tmp.indexOf("\":\"") + 3);
		try{
			this.name = tmp.substring( 0, tmp.indexOf("\", \""));
			tmp = tmp.substring( tmp.indexOf("\":\"") + 3 );
			
			this.phone = tmp.substring( 0, tmp.indexOf("\", \""));
			tmp = tmp.substring( tmp.indexOf("\":\"") + 3 );
			
			this.desc = tmp.substring( 0, tmp.indexOf("\", \""));
			tmp = tmp.substring( tmp.indexOf("\":\"") + 3 );
			
			this.area = tmp.substring( 0, tmp.indexOf("\", \""));
			tmp = tmp.substring( tmp.indexOf("\":\"") + 3 );
			
			this.url = tmp.substring( 0, tmp.indexOf("\" "));
			
		}catch(Exception e){
			System.out.println( tmp );
		}

	}
	
	public static void main(String[] args){
		user u = new user(FileUtil.getFileContent("C:/SINA/02b3384d-f5e2-4bfa-9051-08cb4e296438.json"));
		u.setArea("nanjing");
		//u.setDesc( Base64.encode( u.getDesc().getBytes()).replaceAll("\n", ""));
		//u.setName( Base64.encode( u.getName().getBytes()));
		
		u.insert();
		System.out.println( u.toJSON());
	}
}
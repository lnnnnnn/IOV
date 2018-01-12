package edu.nuist.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;



import edu.nuist.beans.car_net;
import edu.nuist.dbenablebean.DBEnableBean;


@SuppressWarnings("serial")
@WebServlet("/carDbServlet")
public class carDataSerlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		//String id = request.getParameter("sid");
		String[] res = new String[999];
		System.out.println("receive");
		try {
			 response.setCharacterEncoding("UTF-8");  
			 response.setContentType("application/json; charset=utf-8");
			car_net c = new car_net();
			String username=request.getParameter("username");
			c.setUsername(username);
			System.out.println("receive");
			LinkedList<DBEnableBean> car = c.query("search");
			if( car.size()>0 ){
				response.getWriter().print("{\"data\":[");
				for(int i=0;i<car.size();i++){
					c = (car_net)car.get(i);
					 res[i] = "{\r \"brand\": \"" + c.getBrand() + "\",\r\"symbol\":\"" + c.getSymbol() + "\",\r\"type\":\""
							+ c.getType() + "\",\r\"hphm\":\"" + c.getHphm() + "\",\r\"classno\":\""+c.getClassno() + "\",\r\"engineno\":\"" + c.getEngineno()
							+ "\"\r}";
					if(i==car.size()-1){
						response.getWriter().println(res[i]);
					}else{
						response.getWriter().println(res[i]+",");
					}
				}
				response.getWriter().println("]\r}");
				
			}else{
				response.getWriter().println("nonono");
			}
				
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		
		doGet(request,response);
//		response.setContentType("text/html");  
//        PrintWriter out = response.getWriter();  
        //��Ҫ�����ص��ͻ��˵Ķ���  
//        car_net user=new car_net();  
//        user.setId("123");  
//        user.setName("JSONServlet");  
//        user.setPassword("JSON");  
//        user.setSay("Hello , i am a servlet !");  
//        JSONObject json=new JSONObject();  
//        json.accumulate("success", true);  
//        json.accumulate("user", user);  
//        out.println(json.toString());  
//      ��ΪJSON�����ڴ��ݹ�����������ͨ�ַ�����ʽ���ݵģ���������Ҳ�����ֶ�ƴ�ӷ���JSON�﷨�淶���ַ���������ͻ���  
//      �����������������38-46�д����������һ���ģ�����ͻ��˷���һ��User���󣬺�һ��success�ֶ�  
//      String jsonString="{\"user\":{\"id\":\"123\",\"name\":\"JSONServlet\",\"say\":\"Hello , i am a servlet !\",\"password\":\"JSON\"},\"success\":true}";  
//      out.println(jsonString);  
//        out.flush();  
//        out.close();  
    
//		
	}
		
		

}

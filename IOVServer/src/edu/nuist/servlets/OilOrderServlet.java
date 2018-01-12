package edu.nuist.servlets;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nuist.beans.OilOrder;
import edu.nuist.beans.car_net;
import edu.nuist.dbenablebean.DBEnableBean;
import net.sf.json.JSONObject;

@SuppressWarnings("serial")
@WebServlet("/OilOrderServlet")
public class OilOrderServlet extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response){
		
	   //System.out.println("receive");
		/*Enumeration paraEnum = request.getParameterNames();
		if(!paraEnum.hasMoreElements()){
			System.out.println("receive parame null");
			String res= getAllOrders();
			System.out.println(res);
			try {
				
				response.getWriter().print(res);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{*/
			System.out.println("receive");
			String jsonStr=request.getParameter("data");
			String username=request.getParameter("username");
			System.out.println(jsonStr);
			String res="";
			try {
				res = URLDecoder.decode(jsonStr,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(res);
			JSONObject obj=JSONObject.fromObject(res);
			OilOrder o=new OilOrder(obj.getBoolean("finished"),obj.getString("name"),obj.getString("carDesc"),obj.getString("date"),obj.getString("gasStation"),
					obj.getString("type"),obj.getString("volume"),obj.getDouble("money"),obj.getString("barcodeSrc"),username
					);
			
			o.insert();	
		//}
		
	}
	
	public String getAllOrders(){
		String res="{\"data\":[";
		//System.out.println("all");
		OilOrder o=new OilOrder();
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
          //System.out.println(res);
          
          return res;
	}
}

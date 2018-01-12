package edu.nuist.servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nuist.beans.car_net;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class MaintainServlet
 */
@WebServlet("/MaintainServlet")
public class MaintainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MaintainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
		JSONObject obj=JSONObject.fromObject(res);
		/*car_net c=new OilOrder(obj.getString("name"),obj.getString("carDesc"),obj.getString("date"),obj.getString("gasStation"),
				obj.getString("type"),obj.getString("volume"),obj.getString("barcodeSrc")
				);*/
		
		car_net car=new car_net();
		car.setBrand(obj.getString("brand")).setSymbol(obj.getString("symbol")).
		setType(obj.getString("type")).setHphm(obj.getString("hphm"))
		.setClassno(obj.getString("classno")).setEngineno(obj.getString("engineno")).setLevel(obj.getString("level")).setMileage(obj.getDouble("mileage"))
		.setGas_percent(obj.getInt("gas_percent")).setEngineP(obj.getString("engineP")).setLightS(obj.getString("lightS")).setTransmissionP(obj.getString("transmissionP")).setUsername(username);
		
		
		
		car_net pre=(car_net)car.query("queryByHphm").get(0);
		
		car.updateCarByHphm(car);
		
		ArrayList<String> infoList=new ArrayList<String>();
		infoList=car.getMaintainInfo(pre, car);
		String ans="";
		for(int i=0;i<infoList.size()-1;i++){ans+=infoList.get(i)+"&";}
		ans+=infoList.get(infoList.size()-1);
		System.out.println(ans);
		
		String str=new String(ans.getBytes("ISO-8859-1"),"UTF-8");//乱码
		//System.out.println(str);
		 response.setCharacterEncoding("UTF-8");  
		 response.setContentType("application/json; charset=utf-8");
		 
		 Date date=new Date();
		 DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		 String time=format.format(date);
		 String result=ans+"&"+time;
		 //System.out.println(time);
		//response.getWriter().print(ans);
		 response.getWriter().print(result);
		/*String username=pre.getUsername();
		System.out.println(username);
		User u=new User();
		u.setName(username);
		String email=((User)(u.query("queryByName").get(0))).getEmail();
		System.out.println(email);
		Email em=new Email(email,"来自车联网的维护信息",ans);
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
		}
	*/
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

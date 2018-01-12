package edu.nuist.servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nuist.beans.OilOrder;
import edu.nuist.dbenablebean.DBEnableBean;

/**
 * Servlet implementation class GetAllOrdersServlet
 */
@WebServlet("/GetAllOrdersServlet")
public class GetAllOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllOrdersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getAllOrders(String username){
		String res="{\"data\":[";
		//System.out.println("all");
		OilOrder o=new OilOrder();
		o.setUsername(username);
		
		LinkedList<DBEnableBean> orders= o.query("search");
		System.out.println(orders.size());
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
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		 response.setCharacterEncoding("UTF-8");  
		 response.setContentType("application/json; charset=utf-8");
		System.out.println("receive parame null");
		String res= getAllOrders(username);
		System.out.println(res);
		try {
			
			response.getWriter().print(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

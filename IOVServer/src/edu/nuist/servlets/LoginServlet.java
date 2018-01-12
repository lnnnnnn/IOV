package edu.nuist.servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nuist.beans.User;
import edu.nuist.dbenablebean.DBEnableBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    public void service(HttpServletRequest request, HttpServletResponse response){
    	
    	System.out.println("receiveLogin");
    	String un = request.getParameter("username");
		String ps = request.getParameter("password");
		
		try {
			User u = new User();
			u.setName(un);
			u.setPassword(ps);
			LinkedList<DBEnableBean> users = u.query("loginSQL");
			if( users.size()>0 ){
				System.out.println("Login Success!!");
				response.getWriter().print("Login Success!!");
				
				}
			
			else{
				System.out.println("Login ERROR!!");
				response.getWriter().print("Login ERROR!!");
			}
				

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
/*public void doPost(HttpServletRequest request, HttpServletResponse response){
	doGet(request,response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		String un = request.getParameter("username");
		String ps = request.getParameter("password");
		System.out.println("receiveLogin");
		try {
			User u = new User();
			u.setName(un);
			u.setPassword(ps);
			LinkedList<DBEnableBean> users = u.query("loginSQL");
			if( users.size()>0 )
				response.getWriter().println("Login Success!!");
			else
				response.getWriter().println("Login ERROR!!");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}

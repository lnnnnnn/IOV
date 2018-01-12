package edu.nuist.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nuist.abenablebean.util.DBTableInitor;
import edu.nuist.beans.User;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String un = request.getParameter("username");
		String ps = request.getParameter("password");
		String em=request.getParameter("email");
		//System.out.println(em);
		//System.out.println(un);
		 response.setCharacterEncoding("UTF-8");  
		 response.setContentType("application/text; charset=utf-8");
		User ub=new User();
		ub.setName(un);
		System.out.println(un);
		if(ub.query("queryByName").size()>0){
			response.getWriter().print("用户名已存在！");
		}
		else{
			User u=new User(un,ps,em);
			u.insert();
			response.getWriter().print("注册成功！");
		}
		
		//new DBTableInitor(User.class, u);
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

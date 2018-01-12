package edu.nuist.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nuist.beans.car_net;

/**
 * Servlet implementation class RegisterCarServlet
 */
@WebServlet("/RegisterCarServlet")
public class RegisterCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	    String username=request.getParameter("username");
	    String brand=request.getParameter("brand");
	    String type=request.getParameter("type");
	    String hphm=request.getParameter("hphm");
	    String classno=request.getParameter("classno");
	    String en=request.getParameter("engineno");
	    String symbol=request.getParameter("symbol");
	    
	    car_net  c =new car_net();
	    c.setBrand(brand).setClassno(classno).setType(type).setHphm(hphm).setEngineno(en).setSymbol(symbol).setUsername(username);
	    
	    c.insert();
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

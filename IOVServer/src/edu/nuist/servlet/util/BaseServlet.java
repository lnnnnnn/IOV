package edu.nuist.servlet.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BaseServlet�?
 */

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private boolean typeCheck(String paramValue, String type){
		boolean rst = true;
		try{
			switch(type){
				case "int" :
					Integer.parseInt(paramValue);
					break;
				case "boolean":
					Boolean.parseBoolean(paramValue);
					break;
				case "double":
					Double.parseDouble(paramValue);
					break;
				case "long":
					Long.parseLong(paramValue);
					break;
			}
		}catch(Exception e){
			rst = false;
		}
		
		return rst;
	}
	//paramchk: string: name=type:must; name=type:must; name=type:must;
	//return :
	private String[] checkParam(HttpServletRequest request, String paramChk){
		String[] paramChks = paramChk.split(";");
		String[] rst = new String[]{"SUCCESS"};
		for(String chk : paramChks){
			String name = chk.substring( 0, chk.indexOf("=") ).trim();
			String type = chk.substring( chk.indexOf("=")+1, chk.indexOf(":")).trim().toLowerCase();
			
			String must = "false";
			if( chk.indexOf(":") != -1)
				must = chk.substring( chk.indexOf(":")+1).trim().toLowerCase();
			
			if(type.indexOf("[]") != -1){ //for parameters array 
				String[] objs = request.getParameterValues(name);
				if( objs == null){
					if( "true".equals(must) ){
						rst = new String[]{ ServletRst.ERROR_PARAM_NULL , name };
						break;
					}
				}else{
					String basicType = type.substring(0, type.indexOf("[]"));
					for(String obj : objs){
						if(!typeCheck(obj, basicType)){
							rst = new String[]{ ServletRst.ERROR_PARAM_TYPE , name };
							break;
						}
					}
					
				}
			}else{
				String obj = request.getParameter(name);
				if( obj == null){
					if( "true".equals(must) ){
						rst = new String[]{ ServletRst.ERROR_PARAM_NULL , name };
						System.out.println(name);
						break;
					}
				}else{
					if(!typeCheck(obj, type)){
						rst = new String[]{ ServletRst.ERROR_PARAM_TYPE , name };
						break;
					}
				}
			}
			
		}
		return rst;
	}
	
	
	@Override	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");// 处理响应编码

		/**
		 * 1. 获取method参数，它是用户想调用的方�? 2. 把方法名称变成Method类的实例对象 3. 通过invoke()来调用这个方�?
		 */
		String methodName = request.getParameter("method");
		System.out.println(request.getParameter("name"));
		Method method = null;
		/**
		 * 2. 通过方法名称获取Method对象
		 */
		try {
			method = this.getClass().getMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);
			if( method.isAnnotationPresent(ParamChk.class)){
				String paramChk = method.getAnnotation(ParamChk.class).value();
				String[] rstStrings = checkParam(request, paramChk); //return success or error_null : paramName, error_type : paramName
				if( rstStrings.length != 1 ){
					String rst = ServletRst.getParamChkRst(rstStrings[0], rstStrings[1]);
					PrintWriter out = response.getWriter();
					out.println(rst);
					return;
				}				
			}
			
		} catch (Exception e) {
			throw new RuntimeException("您要调用的方法：" + methodName + "它不存在�?", e);
		}

		/**
		 * 3. 通过method对象来调用它
		 */
		try {
			String result = (String) method.invoke(this, request, response);
			if (result != null && !result.trim().isEmpty()) {// 如果请求处理方法返回不为�?
				int index = result.indexOf(":");// 获取第一个冒号的位置
				if (index == -1) {// 如果没有冒号，使用转�?
					String rst = ServletRst.getRst(this, request, result);					
					PrintWriter out = response.getWriter();
					out.println(rst);
				} else {// 如果存在冒号
					String start = result.substring(0, index);// 分割出前�?
					String path = result.substring(index + 1);// 分割出路�?
					if (start.equals("f")) {// 前缀为f表示转发
						request.getRequestDispatcher(path).forward(request,
								response);
					} else if (start.equals("r")) {// 前缀为r表示重定�?
						response.sendRedirect(request.getContextPath() + path);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

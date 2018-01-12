package edu.nuist.servlet.util;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

	/**
	 * 获取String类型参数
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getStringParameterName(HttpServletRequest request,String name){
		if(request.getParameter(name) == null){
			return "";
		}
		
		return request.getParameter(name).trim();
	}
	
	/**
	 * 获取long类型参数
	 * @param request
	 * @param name
	 * @return
	 */
	public static long getLongParameterName(HttpServletRequest request,String name){
		if(getStringParameterName(request, name).equals("")){
			return 0;
		}
		return Long.parseLong(getStringParameterName(request, name));
	}
	
	/**
	 * 获取int类型参数
	 * @param request
	 * @param name
	 * @return
	 */
	public static int getIntParameterName(HttpServletRequest request,String name){
		if(getStringParameterName(request, name).equals("")){
			return 0;
		}
		return Integer.parseInt(getStringParameterName(request, name));
	}
	
	/**
	 * 获取double类型参数
	 * @param request
	 * @param name
	 * @return
	 */
	public static double getDoubleParameterName(HttpServletRequest request,String name){
		if(getStringParameterName(request, name).equals("")){
			return 0;
		}
		return Double.parseDouble(getStringParameterName(request, name));
	}
}

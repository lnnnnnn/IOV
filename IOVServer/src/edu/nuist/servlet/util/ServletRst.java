package edu.nuist.servlet.util;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;

public class ServletRst {
	public static final String SUCCESS = "SUCCESS";
	public static final String ERROR = "ERROR";
	
	public static final String ERROR_PARAM = "ERROR_PARAM";
	public static final String ERROR_PARAM_TYPE = "参数类型错误";
	public static final String ERROR_PARAM_NULL = "参数不能为空";
	
	public static final String jsonTemp = "'{' \"flag\":\"{0}\",  \"content\": {1} '}'";
	
	public static String getRst(BaseServlet servlet, HttpServletRequest request, String rstKey){
		String rst = null;
		if(servlet.getClass().isAnnotationPresent(Rst.class)){
			String[]  rsts = ((Rst) servlet.getClass().getAnnotation(Rst.class)).value().split(";");
			for(String temp : rsts){
				String tempKey = temp.substring( 0, temp.indexOf(":") );
				if( tempKey.trim().equals(rstKey) ){
					rst = temp.substring( temp.indexOf(":") + 1).trim();
					break;
				}
			}	
		}
		if( rst == null){
			rst = (String)request.getAttribute(rstKey);
		}
		//rstKey must be prefix by error or success
		if(rst != null){
			if(rstKey.toLowerCase().indexOf("error") != -1) {
				return MessageFormat.format(jsonTemp, ERROR, rst);
			}else if(rstKey.toLowerCase().indexOf("success") != -1){
				System.out.println(MessageFormat.format(jsonTemp, SUCCESS, rst));
				return MessageFormat.format(jsonTemp, SUCCESS, rst);
			}
		}
		return null;
	}
	
	//return from rstkey: param error reason, rstParamName
	public static String getParamChkRst(String rstKey, String rstParamName){
		return MessageFormat.format(
				jsonTemp, 
				ERROR, 
				"{\"error_type\" : \"ERROR_PARAM\", \"error\":\""+ rstParamName +":"+ rstKey +"\" }"
			);
	}
	
}

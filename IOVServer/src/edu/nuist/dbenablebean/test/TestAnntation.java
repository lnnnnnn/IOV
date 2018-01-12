package edu.nuist.dbenablebean.test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;

import edu.nuist.abenablebean.util.WrapType;
import edu.nuist.dbenablebean.DBEnableBean;
import edu.nuist.dbenablebeananntation.ColDef;
import edu.nuist.dbenablebeananntation.SP;

class TestDBEnableBean extends DBEnableBean{

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DBEnableBean getBeanFromResult(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

@SP(value="get=", table="get=")
public class TestAnntation {
	
	@ColDef("Hello")
	private LinkedList<String> name = new LinkedList<String>();
	private int id;
	private boolean flag;
	private String names;
	private Date date;
	private float s;
	private double ss;
	private String[] array;
	private DBEnableBean db = new TestDBEnableBean();
	private LinkedList<String> be = new LinkedList<String>();
	private TestDBEnableBean[] beans;
	private WrapType<Integer> w;
	
	public void getFieldGenericType(String name){
		try{
			Field[] field = this.getClass().getDeclaredFields();  
			for(Field f : field){
				String fieldName = f.getName();
				Class<?> fieldClass = f.getType();
				System.out.println(fieldClass.getName());
				/*
				if(name.toLowerCase().equals(fieldName)){
					if(!f.isSynthetic()){
						
					}else{
						if(fieldClass == DBEnableBean.class){
							
						}else if(fieldClass == LinkedList.class){
							
						}else if(fieldClass == Array.class){
							ParameterizedType pt =  (ParameterizedType) f.getGenericType();
							System.out.println(pt.getActualTypeArguments().length);
							System.out.println(pt.getActualTypeArguments()[0]);
						}
					}
					
				}*/
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		/*
		if (TestAnntation.class.isAnnotationPresent(SP.class)) {
			SP annotation= (SP) TestAnntation.class.getAnnotation(SP.class);
			//System.out.println(annotation.value());
		}
		
		
		Field[] field = t.getClass().getDeclaredFields();  
		for(Field f : field){
			 if(f.isAnnotationPresent(ColDef.class)){
				 ColDef colName = f.getAnnotation(ColDef.class);
				 //System.out.println(colName.value());
			 }
					
			//System.out.println(f.getName());
			//System.out.println(Modifier.toString(f.getModifiers()) + " " + f.getType() + " " + f.getName());
		}*/
		TestAnntation t = new TestAnntation();
		/*
		Field[] field = t.getClass().getDeclaredFields();  
		for(Field f : field){
			System.out.println(f.getType());
			//System.out.println(Modifier.toString(f.getModifiers()) + " " + f.getType() + " " + f.getName());
		}*/
		//TestAnntation t = new TestAnntation();
		//t.getFieldGenericType("db");
		Field[] field = t.getClass().getDeclaredFields();  
		
		System.out.println(field[11].getGenericType());
	}
}

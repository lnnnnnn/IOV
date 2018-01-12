package edu.nuist.dbenablebean.test;

import edu.nuist.dbenablebean.DBEnableBean;
import edu.nuist.dbenablebeananntation.SP;

@SP(  table="locationMessage", 
sps="queryLastLocMsg : select * from {tableName} where name={name};" +
	 	 "queryByCode : select * from {tableName} where memcode={code};",
	 create = "create table TestBean (id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, name varchar(255), password varchar(255), date Date, memcode int(11), married BOOL, height FLOAT, weight DOUBLE );"
	 )
public class secTestQuery extends DBEnableBean{
	String myName;
	


	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}
	
}


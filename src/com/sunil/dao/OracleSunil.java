package com.sunil.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OracleSunil {
	private static DataSource OracleSunil = null;
	private static Context context = null;

	public static DataSource oracleSunilConn() throws Exception{
		if(OracleSunil!=null){
			return OracleSunil;
		}
		try{
			if(context == null){
				context = new InitialContext();
			}
			OracleSunil = (DataSource)context.lookup("sunilOracle");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return OracleSunil;
	}
}

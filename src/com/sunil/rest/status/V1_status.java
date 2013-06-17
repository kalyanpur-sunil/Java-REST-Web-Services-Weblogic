package com.sunil.rest.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sunil.dao.OracleSunil;

@Path("/v1/status")
public class V1_status {

	private static final String api_version = "00.01.00";


	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		return "<p>Java Web Service</p>";
	}

	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getVersion(){
		return "<p>Version: "+api_version +"</p>";
	}

	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception{
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		String sql = "select to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS')DATETIME from sys.dual";
		try{
			conn = OracleSunil.oracleSunilConn().getConnection();
			query = conn.prepareStatement(sql);
			ResultSet rs = query.executeQuery();
			while(rs.next()){
				myString = rs.getString("DATETIME");
			}
			returnString = "<p>Database status. </p>" +
					"<p>Database Timestamp "+myString+"</p>";
		}catch(Exception ex){

		}finally{
			if(query!=null){
				query.close();
			}
			if(conn!=null){
				conn.close();
			}
		}
		return returnString;
	}
	
	
	
}

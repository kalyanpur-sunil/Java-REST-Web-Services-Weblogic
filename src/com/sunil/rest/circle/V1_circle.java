package com.sunil.rest.circle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.sunil.dao.OracleSunil;
import com.sunil.util.ToJSON;

@Path("/v1/circle")
public class V1_circle {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllCircles() throws Exception{
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		Response rb = null;
		String sql = "select * from circle";
		try{
			conn = OracleSunil.oracleSunilConn().getConnection();
			query = conn.prepareStatement(sql);
			ResultSet rs = query.executeQuery();
			ToJSON convertor = new ToJSON();
			JSONArray jsonArray = new JSONArray();
			jsonArray = convertor.toJSONArray(rs);

			returnString = jsonArray.toString();
			rb = Response.ok(returnString).build();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(query!=null){
				query.close();
			}
			if(conn!=null){
				conn.close();
			}
		}
		return rb;
	}
}

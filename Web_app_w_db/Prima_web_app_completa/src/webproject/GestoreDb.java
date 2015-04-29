package webproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestoreDb {

	//questa classe si occuperà di garantire le operazioni più utili per interfacciarsi con la nostra tabella utenti
	
	private String protocol="jdbc:derby:";
	private String dbname="primoDb";
	private String dbURL=protocol+"//localhost"+dbname;
	
	static final String username="user";
	static final String password="password";
	
	public ArrayList<String> findByName(String name){
		
		ArrayList<String> result=new ArrayList<String>();
		PreparedStatement query=null;
		Connection c=null;
		ResultSet rs=null;
		Statement s=null;
		
		try {
			 c=DriverManager.getConnection(dbURL, username, password);
			s=c.createStatement();
			//s.execute("select username,pwd from users where username="+name);
			query=c.prepareStatement("select username,pwd from users where username=?");
			query.setString(1, name);
			rs=s.getResultSet();
			while(rs.next()){
				
				result.add(rs.getString("username"));
				result.add(rs.getString("pwd"));
				
				
			}
			rs.close();
			s.close();
			c.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		
			try {
				rs.close();
				s.close();
				c.close();
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
		return result;
		
		
		
		
		
	}
	
	
	
	
	
	
	
}

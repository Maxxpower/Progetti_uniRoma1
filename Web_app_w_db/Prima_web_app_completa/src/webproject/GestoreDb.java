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
        
        //ricerca per username, restituisce in un'arraylist utente e password
        
        public ArrayList<String> findByName(String name){
        		
        		Connection c=null;
        		Statement s=null;
        		PreparedStatement psquery=null;
        		ResultSet rs=null;
        	
                ArrayList<String> result=new ArrayList<String>();
               
                try {
                        c=DriverManager.getConnection(dbURL, username, password);
                        s=c.createStatement();
                        psquery=c.prepareStatement("select username,pwd from users where username=?");
                        psquery.setString(1,name);
                        psquery.executeUpdate();
                        
                        
                        
                        
                        rs=psquery.getResultSet();
                        while(rs.next()){
                               
                                result.add(rs.getString("username"));
                                result.add(rs.getString("pwd"));
                               
                               
                        }
                       
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }finally{
                	
                	
                	try {
						rs.close();
	                	s.close();
	                	psquery.close();
	                	c.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

                	
                	
                	
                	
                	
                }
               
               
               
                return result;
               
               
               
               
               
        }
       
       
       
       
       
       
       
}
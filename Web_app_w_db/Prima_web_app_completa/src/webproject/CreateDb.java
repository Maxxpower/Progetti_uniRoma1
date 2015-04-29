package webproject;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
 
public class CreateDb {
       
       
        private String protocol="jdbc:derby:";
 
        public void create(String name){
               
                System.out.println("creo il db "+ name);
                Connection c=null;
                String user="user";
            String pwd="password";
                Properties p=new Properties();
                Statement s=null;
               
                p.put(user,"user");
                p.put(pwd, "password");
                PreparedStatement insert=null;
               
                try {
                       
                        //connessione al db e creazione tabella
                       
                        c=DriverManager.getConnection(protocol+name+";create=true",p);
                        System.out.println("Db "+name+"creato!");
                        s=c.createStatement();
                        s.execute("create table users(username varchar(40),pwd varchar(40))");
                       
                        //inseriamo qualche riga
                       
                        insert=c.prepareStatement("insert into users values(?,?)");
                        insert.setString(1, "francesco");
                        insert.setString(2, "zada#res");
                        insert.executeUpdate();
                        //chiudo tutto e libero risorse
                        insert.close();
                        s.close();
                        c.close();
                       
                       
                       
                       
                       
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }finally{
                       
                        try {
                                c.close();
                        } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                       
                       
                }
               
               
               
               
               
               
               
        }
       
       
       
       
       
        public static void main(String[] args) {
               
                CreateDb db= new CreateDb();
                db.create("primoDb");
 
        }
 
}
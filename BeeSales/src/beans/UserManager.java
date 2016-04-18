package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserManager {

	private DataSource ds;

	public UserManager() {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/beesales");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean validateLogin(String user, String pwd) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select uname,password from users where uname=? and password=?";

		try {
			c = ds.getConnection();
			ps = c.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, pwd);
			rs = ps.executeQuery();

			if (rs.next()) {

				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				rs.close();
				ps.close();
				c.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return false;

	}

}

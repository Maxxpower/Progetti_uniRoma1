import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class VenditeManager {

	private DataSource ds;

	public VenditeManager() {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/beesales");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insertVendita(Vendita v) {

		String insertString = "insert into vendita(quantity,data,tipoMiele,tipoBarattolo,incasso) values(?,?,?,?,?)";
		PreparedStatement ps = null;
		Connection c = null;

		try {
			c = ds.getConnection();
			ps = c.prepareStatement(insertString);
			ps.setFloat(1, (float) v.getQuantitaKg());
			ps.setDate(2, new java.sql.Date(v.getData().getTime()));
			ps.setString(3, v.getTipoMiele().toUpperCase());
			ps.setString(4, v.getTipoBarattolo().toUpperCase());
			ps.setFloat(5, (float) v.getIncasso());
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				ps.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public List<Vendita> selectAll() {

		PreparedStatement ps = null;
		Connection c = null;
		ResultSet rs = null;
		List<Vendita> storico = null;

		try {
			c = ds.getConnection();
			ps = c.prepareStatement("select * from vendita");
			ps.execute();
			rs = ps.getResultSet();
			// inserisco i dati nella lista di ritorno
			storico = new ArrayList<Vendita>();
			while (rs.next()) {

				Vendita v = new Vendita();
				v.setData(new Date((rs.getDate("data").getTime())));
				v.setIncasso(rs.getDouble("incasso"));
				v.setQuantitaKg(rs.getDouble("quantity"));
				v.setTipoBarattolo(rs.getString("tipoBarattolo"));
				v.setTipoMiele(rs.getString("tipoMiele"));

				storico.add(v);

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

		return storico;

	}

	public List<Vendita> selectByMonth(String month) {

		List<Vendita> result = new ArrayList<Vendita>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, String> dizionarioMesi = new HashMap<String, String>();
		dizionarioMesi.put("Gennaio", "1");
		dizionarioMesi.put("Febbraio", "2");
		dizionarioMesi.put("Marzo", "3");
		dizionarioMesi.put("Aprile", "4");
		dizionarioMesi.put("Maggio", "5");
		dizionarioMesi.put("Giugno", "6");
		dizionarioMesi.put("Luglio", "7");
		dizionarioMesi.put("Agosto", "8");
		dizionarioMesi.put("Settembre", "9");
		dizionarioMesi.put("Ottobre", "10");
		dizionarioMesi.put("Novembre", "11");
		dizionarioMesi.put("Dicembre", "12");
		String query = "select * from vendita where data like '*/"+dizionarioMesi.get(month)+  "/*'";

		try {
			c = ds.getConnection();
			ps = c.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {

				Vendita v = new Vendita();
				v.setData(new Date(rs.getDate("data").getTime()));
				v.setIncasso(rs.getDouble("incasso"));
				v.setQuantitaKg(rs.getDouble("quantity"));
				v.setTipoBarattolo(rs.getString("tipoBarattolo"));
				v.setTipoMiele(rs.getString("tipoMiele"));

				result.add(v);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

}

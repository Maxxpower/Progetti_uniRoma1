package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.primefaces.model.DefaultScheduleEvent;

public class EventManager {

	private DataSource ds;

	public EventManager() {

		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/beesales");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void insertEvent(DefaultScheduleEvent event) {

		// controllo prima se l'arnia relativa all'evento è già presente sul db

		String hiveName = "" + event.getDescription().charAt(event.getDescription().length()-1);

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// Ricavo la lista di eventi corrente, verifico se l'arnia dell'evento
		// passato come parametro è già inserita tra gli eventi su db
		List<DefaultScheduleEvent> listaEventiArnia = findEventByHiveName(hiveName);

		if (listaEventiArnia.isEmpty()) {

			String query = "insert into eventi(datafine,datainizio,nomearnia,descrizione) values(?,?,?,?)";

			try {
				c = ds.getConnection();
				ps = c.prepareStatement(query);
				ps.setDate(1, new java.sql.Date(event.getEndDate().getTime()));
				ps.setDate(2, new java.sql.Date(event.getStartDate().getTime()));
				ps.setString(3, hiveName);
				ps.setString(4, event.getDescription());
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

		} else {

			String query = "update eventi set datafine=?, datainizio=? ,descrizione=? where nomearnia=?";

			try {
				c = ds.getConnection();
				ps = c.prepareStatement(query);
				ps.setDate(1, new java.sql.Date(event.getEndDate().getTime()));
				ps.setDate(2, new java.sql.Date(event.getStartDate().getTime()));
				ps.setString(3, event.getDescription());
				ps.setString(4, hiveName);
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

	}

	public List<DefaultScheduleEvent> findEventByHiveName(String hiveName) {

		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		ArrayList<DefaultScheduleEvent> result = new ArrayList<DefaultScheduleEvent>();
		String query = "select * from eventi where nomearnia=?";

		try {
			c = ds.getConnection();
			ps = c.prepareStatement(query);
			ps.setString(1, hiveName);
			rs = ps.executeQuery();

			while (rs.next()) {

				DefaultScheduleEvent event = new DefaultScheduleEvent();
				event.setStartDate(new Date(rs.getDate("datainizio").getTime()));
				event.setEndDate(new Date(rs.getDate("datafine").getTime()));
				event.setDescription(rs.getString("descrizione"));

				result.add(event);

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

		return result;

	}

}

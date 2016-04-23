package beans;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import beans.jpa.Hive;
import beans.jpa.HiveManager;

@ManagedBean
public class ScheduleBean {

	private ScheduleModel calendario;
	private ArrayList<Hive> storicoArnie;

	@PostConstruct
	public void init() {

		calendario = new DefaultScheduleModel();
		storicoArnie = new ArrayList<Hive>();
		HiveManager hm = new HiveManager();
		storicoArnie.addAll(hm.getAll());
		addEventsToScheduleAndDb();

	}

	public ScheduleModel getCalendario() {
		return calendario;
	}

	public void setCalendario(ScheduleModel calendario) {
		this.calendario = calendario;
	}

	private void addEventsToScheduleAndDb() {

		ArrayList<DefaultScheduleEvent> events = generateEvents();
		for (DefaultScheduleEvent e : events) {

			calendario.addEvent(e);

		}

		EventManager em = new EventManager();

		for (DefaultScheduleEvent e : events) {

			em.insertEvent(e);

		}

		// System.out.println("N.eventi "+events.size());
	}

	private ArrayList<DefaultScheduleEvent> generateEvents() {

		// Creo gli eventi da aggiungere alla schedule in base allo stato dei
		// controlli delle arnie

		ArrayList<DefaultScheduleEvent> resultList = new ArrayList<DefaultScheduleEvent>();

		for (Hive h : storicoArnie) {
			DefaultScheduleEvent controllo = new DefaultScheduleEvent();
			controllo.setEditable(false);
			String controlState = checkDate(h.getChecked_at());
			Date dataInizio = null;
			DateTime dataControlloConsigliato = null;

			if (controlState.equals("OK")) {

				dataControlloConsigliato = new DateTime().plusDays(10);
				dataInizio = new Date(dataControlloConsigliato.getMillis());

			} else if (controlState.equals("OLD")) {

				dataControlloConsigliato = new DateTime().plusDays(3);
				dataInizio = new Date(dataControlloConsigliato.getMillis());

			} else if (controlState.equals("MEDIUM")) {

				dataControlloConsigliato = new DateTime().plusDays(7);
				dataInizio = new Date(dataControlloConsigliato.getMillis());

			}

			controllo.setStartDate(dataInizio);
			controllo.setEndDate(dataInizio);
			controllo.setAllDay(false);
			controllo.setDescription("Controllo Arnia n.: " + h.getName());
			controllo.setTitle("Controllo Arnia n.: " + h.getName());
			resultList.add(controllo);

		}

		return resultList;

	}

	private String checkDate(Date d) {

		// Controllo sulle date registrate su ogni oggetto di tipo Hive

		if (d != null) {

			DateTime today = new DateTime();
			DateTime givenDate = new DateTime(d.getTime());

			Days difference = Days.daysBetween(givenDate, today);

			if (difference.getDays() >= 15) {

				return "OLD";

			} else if (difference.getDays() < 15 && difference.getDays() > 7) {

				return "MEDIUM";

			} else {

				return "OK";

			}

		}

		else {

			return "NOT_REGISTERED";
		}

	}

}
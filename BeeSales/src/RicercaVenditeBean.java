import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class RicercaVenditeBean {

	private String modoRicerca;
	private List<Vendita> risultatoRicerca;

	private boolean mese = false;
	private boolean giorno = false;
	private boolean anno = false;

	private Date giornoRicerca;
	private String meseRicerca;
	private int annoRicerca;

	private final String[] mesiAnno = { "Gennaio", "Febbraio", "Marzo",
			"Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre",
			"Ottobre", "Novembre", "Dicembre" };
	private final String[] modiRicerca = { "Mese", "Giorno", "Anno" };

	public String[] getModiRicerca() {
		return modiRicerca;
	}

	public String[] getMesiAnno() {
		return mesiAnno;
	}

	public Date getGiornoRicerca() {
		return giornoRicerca;
	}

	public void setGiornoRicerca(Date giornoRicerca) {
		this.giornoRicerca = giornoRicerca;
	}

	public String getMeseRicerca() {
		return meseRicerca;
	}

	public void setMeseRicerca(String meseRicerca) {
		this.meseRicerca = meseRicerca;
	}

	public int getAnnoRicerca() {
		return annoRicerca;
	}

	public void setAnnoRicerca(int annoRicerca) {
		this.annoRicerca = annoRicerca;
	}

	public List<Vendita> getRisultatoRicerca() {
		return risultatoRicerca;
	}

	public void setRisultatoRicerca(List<Vendita> risultatoRicerca) {
		this.risultatoRicerca = risultatoRicerca;
	}

	public String getModoRicerca() {
		return modoRicerca;
	}

	public void setModoRicerca(String modoRicerca) {
		this.modoRicerca = modoRicerca;
	}

	public boolean isMese() {

		return mese;
	}

	public void setMese(boolean mese) {
		this.mese = mese;
	}

	public boolean isGiorno() {

		return giorno;
	}

	public void setGiorno(boolean giorno) {
		this.giorno = giorno;
	}

	public boolean isAnno() {

		return anno;
	}

	public void setAnno(boolean anno) {
		this.anno = anno;
	}

	public String ricerca() {

		VenditeManager vm = new VenditeManager();

		if (modoRicerca.equals("Mese")) {
			
			risultatoRicerca=vm.selectByMonth(meseRicerca);

		} else if (modoRicerca.equals("Giorno")) {

		} else if (modoRicerca.equals("Anno")) {

		}

		return null;

	}

	public String selezionaModoRicerca() {
		
		if (modoRicerca.equals("Mese")) {
			
			setMese(true);
			setGiorno(false);
			setAnno(false);

		} else if (modoRicerca.equals("Giorno")) {
			
			setGiorno(true);
			setMese(false);
			setAnno(false);

		} else if (modoRicerca.equals("Anno")) {
			
			setAnno(true);
			setGiorno(false);
			setMese(false);

		}
		
		

		return (null);
	}

}

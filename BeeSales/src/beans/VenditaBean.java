package beans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import utilities.DateUtils;

@ManagedBean
@SessionScoped
public class VenditaBean {

	private String tipoBarattolo;
	private double incasso;
	private double quantitaKg;
	private Date data;
	private String tipoMiele;
	//lista delle opzioni da visualizzare per le variet� di miele disponibili
	private List<String> tipiMiele=Arrays.asList("Millefiori","Castagno","Acacia","Primifiori");
	//lista delle vendite pregresse
	
	private List<Vendita> storico;
	private boolean ascendingOrder=true;
	
	public VenditaBean(){
		
		VenditeManager vm=new VenditeManager();
		storico=vm.selectByMonth(DateUtils.currentMonth());
		
	}
	

	public List<Vendita> getStorico() {
		return storico;
	}

	public void setStorico(List<Vendita> storico) {
		this.storico = storico;
	}

	public List<String> getTipiMiele() {
		return tipiMiele;
	}

	public void setTipiMiele(List<String> tipiMiele) {
		this.tipiMiele = tipiMiele;
	}

	public String getTipoMiele() {
		return tipoMiele;
	}

	public void setTipoMiele(String tipoMiele) {
		this.tipoMiele = tipoMiele;
	}

	public String getTipoBarattolo() {
		return tipoBarattolo;
	}

	public void setTipoBarattolo(String tipoBarattolo) {
		this.tipoBarattolo = tipoBarattolo;
	}

	public double getIncasso() {
		return incasso;
	}

	public void setIncasso(double incasso) {
		this.incasso = incasso;
	}

	public double getQuantitaKg() {
		return quantitaKg;
		
		
		
	}

	public void setQuantitaKg(double quantitaKg) {
		this.quantitaKg = quantitaKg;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	// ActionController

	public String inserisciVendita() {
		
		Vendita v=new Vendita();
		v.setData(data);
		v.setIncasso(incasso);
		v.setQuantitaKg(quantitaKg);
		v.setTipoBarattolo(tipoBarattolo);
		v.setTipoMiele(tipoMiele);
		//manager del DB con inserimento
		VenditeManager vm= new VenditeManager();
		vm.insertVendita(v);
		
		//aggiorno la tabella andrò a mostrare ad inserire solo le vendite dell'ultimo mese
		storico=vm.selectByMonth(DateUtils.currentMonth());
		
		//l'ultima vendita va in cima
		Collections.sort(storico, new Comparator<Vendita>() {

			@Override
				public int compare(Vendita o1, Vendita o2) {
					
					return o2.getData().compareTo(o1.getData());
				}
				
				
			
		});
		
		
		return(null);

	}
	
	
	//action listener sulla colonna Data dello storico
	public void sortSellTable(ActionEvent e){
		
	if(ascendingOrder){
			
			Collections.sort(storico, new Comparator<Vendita>() {

			@Override
				public int compare(Vendita o1, Vendita o2) {
					
					return o1.getData().compareTo(o2.getData());
				}
				
				
			
		});
			
			ascendingOrder=false;
			
		}else{
			
			Collections.sort(storico, new Comparator<Vendita>() {
				
			@Override
			public int compare(Vendita o1, Vendita o2) {
				
				return o2.getData().compareTo(o1.getData());
				}
				
				
			});
			
		ascendingOrder=true;
		}
		
	
		

	}
	
	
	
	
}

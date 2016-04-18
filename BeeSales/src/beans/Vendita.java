package beans;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;

import utilities.DoubleUtils;

public class Vendita {

	@Override
	public String toString() {
		return "Vendita [incasso=" + incasso + ", quantitaKg=" + quantitaKg + ", data=" + data + "]";
	}

	private String tipoBarattolo;
	private double incasso;
	private double quantitaKg;
	private Date data;
	private String tipoMiele;
	private boolean editabile;
	private Integer monthAsInteger;

	public Integer getMonthAsInteger() {
		return monthAsInteger;
	}

	public void setMonthAsInteger(Integer monthAsInteger) {
		this.monthAsInteger = monthAsInteger;
	}

	public boolean isEditabile() {
		return editabile;
	}

	public void setEditabile(boolean editabile) {
		this.editabile = editabile;
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

		return DoubleUtils.round(quantitaKg, 2);


	}

	public void setQuantitaKg(double quantitaKg) {
		this.quantitaKg = quantitaKg;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.editabile = false;
		this.data = data;
	}

	public String getTipoMiele() {
		return tipoMiele;
	}

	public void setTipoMiele(String tipoMiele) {
		this.tipoMiele = tipoMiele;
	}

}

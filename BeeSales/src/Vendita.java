import java.util.Date;


public class Vendita {
	
	
	
	
	private String tipoBarattolo;
	private double incasso;
	private double quantitaKg;
	private Date data;
	private String tipoMiele;
	private boolean editabile=false;
	
	
	
	
	
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
		return quantitaKg;
	}
	public void setQuantitaKg(double quantitaKg) {
		this.quantitaKg = quantitaKg;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.editabile=false;
		this.data = data;
	}
	public String getTipoMiele() {
		return tipoMiele;
	}
	public void setTipoMiele(String tipoMiele) {
		this.tipoMiele = tipoMiele;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

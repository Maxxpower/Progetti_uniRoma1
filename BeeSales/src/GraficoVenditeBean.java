import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
public class GraficoVenditeBean {

	private LineChartModel lm;
	private double incassoTotale = 0.0;
	private double quantitaTotale =0.0;
	public double getQuantitaTotale() {
		return quantitaTotale;
	}

	public void setQuantitaTotale(double quantitaTotale) {
		this.quantitaTotale = quantitaTotale;
	}

	private String bestMonth;
	private Map<Integer, String> months = new HashMap<Integer, String>();
	

	@PostConstruct
	public void init() {
		months.put(1, "Gennaio");
		months.put(2, "Febbraio");
		months.put(3, "Marzo");
		months.put(4, "Aprile");
		months.put(5, "Maggio");
		months.put(6, "Giugno");
		months.put(7, "Luglio");
		months.put(8, "Agosto");
		months.put(9, "Settembre");
		months.put(10, "Ottobre");
		months.put(11, "Novembre");
		months.put(12, "Dicembre");
		createLineModel();
		this.incassoTotale=calcolaIncassoTotale();
		this.bestMonth=calcolaMigliorMese();
		this.quantitaTotale=calcolaQuantitaVenduto();

	}

	public double getIncassoTotale() {
		return incassoTotale;
	}

	public void setIncassoTotale(double incassoTotale) {
		this.incassoTotale = incassoTotale;
	}

	public String getBestMonth() {
		return bestMonth;
	}

	public void setBestMonth(String bestMonth) {
		this.bestMonth = bestMonth;
	}

	private void createLineModel() {

		lm = inizializzaGrafico();
		lm.setTitle("Vendite Mensili");
		lm.setLegendPosition("e");
		Axis yAxis = lm.getAxis(AxisType.Y);
		yAxis.setMin("0");
		yAxis.setLabel("Incassi(€)");
		//TODO correggi il formato 
		yAxis.setTickFormat("%d");
		lm.getAxes().put(AxisType.X, new CategoryAxis("Mesi"));

	}

	private double calcolaIncassoTotale() {
		
		double incasso=0.0;
		
		VenditeManager vm = new VenditeManager();
		List<Vendita> incassiMensili = vm.selectByHoneyType();

		for (Vendita v : incassiMensili) {
			incasso += v.getIncasso();

		}

		return incasso;
	}

	@SuppressWarnings("deprecation")
	private String calcolaMigliorMese() {

		VenditeManager vm = new VenditeManager();
		List<Vendita> incassiMensili = vm.selectByHoneyType();
		String month = null;

		double maximum = 0.0;
		for (Vendita v : incassiMensili) {

			if (v.getIncasso() > maximum) {
				maximum = v.getIncasso();
				month=months.get(v.getMonthAsInteger());
			}

		}

		return month;

	}
	
	private double calcolaQuantitaVenduto(){
		
		VenditeManager vm = new VenditeManager();
		List<Vendita> vendite= vm.selectAll();
		double qt=0.0;
		
		for(Vendita v : vendite){
			
			qt+=v.getQuantitaKg();
			
			
			
			
		}
		
		
		
		return Math.round(qt);
		
		
	}

	private LineChartModel inizializzaGrafico() {
		// qui inizializzo il grafico lineare e setto le altre statistiche

		// Ricavo i dati dal db
		VenditeManager vm = new VenditeManager();
		List<Vendita> incassiMensili = vm.selectByHoneyType();

		LineChartModel lcm = new LineChartModel();

		ChartSeries millefiori = new ChartSeries("Millefiori");
		ChartSeries acacia = new ChartSeries("Acacia");
		ChartSeries primifiori = new ChartSeries("Primifiori");
		ChartSeries castagno = new ChartSeries("Castagno");

		for (Vendita v : incassiMensili) {

			// setup delle statistiche extra

			if (v.getTipoMiele().equalsIgnoreCase(castagno.getLabel())) {

				castagno.set(months.get(v.getMonthAsInteger()), v.getIncasso());

			}

			else if (v.getTipoMiele().equalsIgnoreCase(acacia.getLabel())) {

				acacia.set(months.get(v.getMonthAsInteger()), v.getIncasso());

			}

			else if (v.getTipoMiele().equalsIgnoreCase(millefiori.getLabel())) {

				millefiori.set(months.get(v.getMonthAsInteger()), v.getIncasso());

			}

			else if (v.getTipoMiele().equalsIgnoreCase(primifiori.getLabel())) {

				primifiori.set(months.get(v.getMonthAsInteger()), v.getIncasso());

			}

		}

		//TODO Verifica che le serie siano riempiete di dati
		lcm.addSeries(acacia);
		lcm.addSeries(castagno);
		lcm.addSeries(millefiori);
		lcm.addSeries(primifiori);

		return lcm;

	}

	public LineChartModel getLm() {
		return lm;
	}

	public void setLm(LineChartModel lm) {
		this.lm = lm;
	}

}

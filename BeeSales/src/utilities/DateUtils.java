package utilities;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DateUtils {
	
	
	public static ArrayList<Integer> getPastYears(){
		
		ArrayList<Integer>pastYears= new ArrayList<Integer>();
		
		Calendar today= new GregorianCalendar();
		Integer currentYear= today.get(Calendar.YEAR);
		
		for(int i=0; i<10; i++){
			
			pastYears.add(currentYear-i);
			
			
		}
		
		
		return pastYears;
		
		
		
	}
	
	public static String MonthAsString(Integer month){
		
		Map<Integer,String> monthMapping= new HashMap<Integer, String>();
		monthMapping.put(1, "Gennaio");
		monthMapping.put(2, "Febbraio");
		monthMapping.put(3, "Marzo");
		monthMapping.put(4, "Aprile");
		monthMapping.put(5, "Maggio");
		monthMapping.put(6, "Giugno");
		monthMapping.put(7, "Luglio");
		monthMapping.put(8, "Agosto");
		monthMapping.put(9, "Settembre");
		monthMapping.put(10, "Ottobre");
		monthMapping.put(11, "Novembre");
		monthMapping.put(3, "Dicembre");
		
		
		return monthMapping.get(month);
		
		
		
		
	}
	
	public static Integer MonthAsInteger(String month){
		

		Map<String, Integer> dizionarioMesi = new HashMap<String, Integer>();
		dizionarioMesi.put("Gennaio", 1);
		dizionarioMesi.put("Febbraio", 2);
		dizionarioMesi.put("Marzo", 3);
		dizionarioMesi.put("Aprile", 4);
		dizionarioMesi.put("Maggio", 5);
		dizionarioMesi.put("Giugno", 6);
		dizionarioMesi.put("Luglio", 7);
		dizionarioMesi.put("Agosto", 8);
		dizionarioMesi.put("Settembre", 9);
		dizionarioMesi.put("Ottobre", 10);
		dizionarioMesi.put("Novembre", 11);
		dizionarioMesi.put("Dicembre", 12);
		
		return dizionarioMesi.get(month);
		
		
		
		
	}
	
	public static String currentMonth(){
		
		Calendar todayCal= new GregorianCalendar();
		Integer monthInt=todayCal.get(Calendar.MONTH);
		Map<Integer,String> monthMapping= new HashMap<Integer, String>();
		monthMapping.put(0, "Gennaio");
		monthMapping.put(1, "Febbraio");
		monthMapping.put(2, "Marzo");
		monthMapping.put(3, "Aprile");
		monthMapping.put(4, "Maggio");
		monthMapping.put(5, "Giugno");
		monthMapping.put(6, "Luglio");
		monthMapping.put(7, "Agosto");
		monthMapping.put(8, "Settembre");
		monthMapping.put(9, "Ottobre");
		monthMapping.put(10, "Novembre");
		monthMapping.put(11, "Dicembre");
		
		

		
		
		
		
		return monthMapping.get(monthInt);
		
	}
	
}
	


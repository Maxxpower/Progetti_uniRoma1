package mvcshop;

import java.io.Serializable;
import java.util.ArrayList;

public class Carrello implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> carrello=new ArrayList<String>();
	private Double totale;
	
	public void aggiunggiProdotto(String s){
		
		
		carrello.add(s);
		
		
		
		
	}


	public ArrayList<String> getCarrello() {
		return carrello;
	}
	
	
	public String printCarrello(){
		
		String result="";
				
				for (String s: carrello){
					
					result+=s+"\n";
					
				}
				
		return result;
		
		
		
	}
	
	

}

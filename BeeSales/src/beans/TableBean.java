package beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import beans.jpa.Hive;
import beans.jpa.HiveManager;

@ManagedBean
@ViewScoped
public class TableBean {

	private List<Hive> records;

	@PostConstruct
	public void init() {
		HiveManager hm = new HiveManager();
		records = hm.getAll();

	}

	public List<Hive> getRecords() {
		return records;
	}

	public void setRecords(List<Hive> records) {
		this.records = records;
	}

	public String setEditable(Hive h){
		
		System.out.println("Chiamato editAction!");
		h.setEditable(true);
		
		return null;
		
		
	}
	
	public String testAction(Hive h){
		
		System.out.println("testAction chiamato!");
		h.setEditable(false);
		
		return null;
		
	}
	
}

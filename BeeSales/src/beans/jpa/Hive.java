package beans.jpa;

/*
 * Hive.java
 *
 * Copyright (c) 2016, MieleImperioli and/or its affiliates. All rights reserved.
 * MIELEIMPERIOLI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Instant;
import org.joda.time.Interval;

import utilities.DateUtils;

/**
 * The Hive class is an entity bean which references to the table Hives.
 *
 * @author fa
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
public class Hive implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "name")
	private String name;
	@Column(name = "checked_at")
	private Timestamp checked_at;
	@Column(name = "queen_bee_checked_at")
	private Timestamp queen_bee_checked_at;
	@Column(name = "queen_bee_color")
	private String queen_bee_color;
	@Column(name = "queen_bee_goodness")
	private Integer queen_bee_goodness;
	@Column(name = "brood_state")
	private String brood_state;
	@Column(name = "fleeting_state")
	private String fleeting_state;
	@Column(name = "honey_supplying")
	private String honey_supplying;
	@Column(name = "royal_cells_status")
	private String royal_cells_status;
	@Column(name = "block_blood_start_date")
	private Timestamp block_blood_start_date;
	@Column(name = "block_blood_end_date")
	private Timestamp block_blood_end_date;
	@Column(name = "varroa_checked_at")
	private Timestamp varroa_checked_at;
	@Column(name = "varroa_status")
	private String varroa_status;
	@Column(name = "tray_checked_at")
	private Timestamp tray_checked_at;
	@Column(name = "syrup1_supplied_at")
	private Timestamp syrup1_supplied_at;
	@Column(name = "syrup2_supplied_at")
	private Timestamp syrup2_supplied_at;
	@Column(name = "candy_supplied_at")
	private Timestamp candy_supplied_at;
	@Column(name = "candy_qty")
	private Double candy_qty;
	@Column(name = "honey_production_type")
	private String honey_production_type;
	@Column(name = "honey_production_qty")
	private Double honey_production_qty;
	@Column(name = "qty_frame_number")
	private Integer qty_frame_number;
	
	private boolean editable;
	
	
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getChecked_at() {
		return checked_at;
	}

	public void setChecked_at(Timestamp checked_at) {
		this.checked_at = checked_at;
	}

	public Timestamp getQueen_bee_checked_at() {
		return queen_bee_checked_at;
	}

	public void setQueen_bee_checked_at(Timestamp queen_bee_checked_at) {
		this.queen_bee_checked_at = queen_bee_checked_at;
	}

	public String getQueen_bee_color() {
		return queen_bee_color;
	}

	public void setQueen_bee_color(String queen_bee_color) {
		this.queen_bee_color = queen_bee_color;
	}

	public Integer getQueen_bee_goodness() {
		return queen_bee_goodness;
	}

	public void setQueen_bee_goodness(Integer queen_bee_goodness) {
		this.queen_bee_goodness = queen_bee_goodness;
	}

	public String getBrood_state() {
		return brood_state;
	}

	public void setBrood_state(String brood_state) {
		this.brood_state = brood_state;
	}

	public String getFleeting_state() {
		return fleeting_state;
	}

	public void setFleeting_state(String fleeting_state) {
		this.fleeting_state = fleeting_state;
	}

	public String getHoney_supplying() {
		return honey_supplying;
	}

	public void setHoney_supplying(String honey_supplying) {
		this.honey_supplying = honey_supplying;
	}

	public String getRoyal_cells_status() {
		return royal_cells_status;
	}

	public void setRoyal_cells_status(String royal_cells_status) {
		this.royal_cells_status = royal_cells_status;
	}

	public Timestamp getBlock_blood_start_date() {
		return block_blood_start_date;
	}

	public void setBlock_blood_start_date(Timestamp block_blood_start_date) {
		this.block_blood_start_date = block_blood_start_date;
	}

	public Timestamp getBlock_blood_end_date() {
		return block_blood_end_date;
	}

	public void setBlock_blood_end_date(Timestamp block_blood_end_date) {
		this.block_blood_end_date = block_blood_end_date;
	}

	public Timestamp getVarroa_checked_at() {
		return varroa_checked_at;
	}

	public void setVarroa_checked_at(Timestamp varroa_checked_at) {
		this.varroa_checked_at = varroa_checked_at;
	}

	public String getVarroa_status() {
		return varroa_status;
	}

	public void setVarroa_status(String varroa_status) {
		this.varroa_status = varroa_status;
	}

	public Timestamp getTray_checked_at() {
		return tray_checked_at;
	}

	public void setTray_checked_at(Timestamp tray_checked_at) {
		this.tray_checked_at = tray_checked_at;
	}

	public Timestamp getSyrup1_supplied_at() {
		return syrup1_supplied_at;
	}

	public void setSyrup1_supplied_at(Timestamp syrup1_supplied_at) {
		this.syrup1_supplied_at = syrup1_supplied_at;
	}

	public Timestamp getSyrup2_supplied_at() {
		return syrup2_supplied_at;
	}

	public void setSyrup2_supplied_at(Timestamp syrup2_supplied_at) {
		this.syrup2_supplied_at = syrup2_supplied_at;
	}

	public Timestamp getCandy_supplied_at() {
		return candy_supplied_at;
	}

	public void setCandy_supplied_at(Timestamp candy_supplied_at) {
		this.candy_supplied_at = candy_supplied_at;
	}

	public Double getCandy_qty() {
		return candy_qty;
	}

	public void setCandy_qty(Double candy_qty) {
		this.candy_qty = candy_qty;
	}

	public String getHoney_production_type() {
		return honey_production_type;
	}

	public void setHoney_production_type(String honey_production_type) {
		this.honey_production_type = honey_production_type;
	}

	public Double getHoney_production_qty() {
		return honey_production_qty;
	}

	public void setHoney_production_qty(Double honey_production_qty) {
		this.honey_production_qty = honey_production_qty;
	}

	public Integer getQty_frame_number() {
		return qty_frame_number;
	}

	public void setQty_frame_number(Integer qty_frame_number) {
		this.qty_frame_number = qty_frame_number;
	}

	@Override
	public String toString() {
		return "Hive [name=" + name + ", checked_at=" + checked_at + ", queen_bee_checked_at=" + queen_bee_checked_at
				+ ", queen_bee_color=" + queen_bee_color + ", queen_bee_goodness=" + queen_bee_goodness
				+ ", brood_state=" + brood_state + ", fleeting_state=" + fleeting_state + ", honey_supplying="
				+ honey_supplying + ", royal_cells_status=" + royal_cells_status + ", block_blood_start_date="
				+ block_blood_start_date + ", block_blood_end_date=" + block_blood_end_date + ", varroa_checked_at="
				+ varroa_checked_at + ", varroa_status=" + varroa_status + ", tray_checked_at=" + tray_checked_at
				+ ", syrup1_supplied_at=" + syrup1_supplied_at + ", syrup2_supplied_at=" + syrup2_supplied_at
				+ ", candy_supplied_at=" + candy_supplied_at + ", candy_qty=" + candy_qty + ", honey_production_type="
				+ honey_production_type + ", honey_production_qty=" + honey_production_qty + ", qty_frame_number="
				+ qty_frame_number + "]";
	}

	
	
	public String editHive(){
		
		setEditable(true);
		return null;
		
		
		
	}
	
	// these methods are for getting table cells colors based on inserted data.

	public String getDateColorClass(Timestamp t) {

		if (t != null) {
			DateTime today = new DateTime();
			DateTime givenDate = new DateTime(t.getTime());

			// difference in days with JodaTime library
			Days d = Days.daysBetween(givenDate, today);

			// //for debug purposes
			// System.out.println("date 1 millis"+ today.getMillis());
			// System.out.println("date 2 millis"+ givenDate.getMillis());
			// System.out.println("difference in days" + d.getDays());

			if (d.getDays() >= 15) {

				return "TOO_OLD_DATE";
			} else if (d.getDays() > 7 && d.getDays() < 15) {

				return "OLD_DATE";
			} else if (d.getDays() <= 7) {

				return "OK_DATE";

			} else {

				return "WHITE_DATE";
			}

		} else {

			return "WHITE_DATE";

		}

	}

	public String varroaStateColorClass() {

		if(varroa_status!=null){
		switch (varroa_status) {

		case "Assente": {

			return "OK_DATE";

		}
		case "Scarsa": {

			return "OK_DATE";

		}
		case "Media": {

			return "OLD_DATE";

		}
		case "Alta": {

			return "TOO_OLD";

		}
		default: {

			return "WHITE_DATE";
		}

		}

		}else{
			
			
			return "WHITE_DATE";
		}
	}

}

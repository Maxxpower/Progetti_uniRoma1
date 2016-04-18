package beans.jpa;

/*
 * HiveManager.java
 *
 * Copyright (c) 2016, MieleImperioli and/or its affiliates. All rights reserved.
 * MIELEIMPERIOLI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class HiveManager {

	private static final Logger LOGGER = Logger.getLogger(HiveManager.class.getName());
	private static final String PERSISTENCE_UNIT_NAME = "mieleImperioliPU";
	private EntityManagerFactory emf;
	private EntityManager em;

	public HiveManager() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = emf.createEntityManager();
	}

	public List<Hive> getAll() {
		TypedQuery<Hive> query = em.createQuery("SELECT h FROM Hive h", Hive.class);
		List<Hive> results = query.getResultList();
		LOGGER.info("found " + (results == null ? "0" : "" + results.size()) + " hive(s)");
		return results;
	}

	public List<Hive> findByName(String name) {
		TypedQuery<Hive> query = em.createQuery("SELECT h FROM Hive h where h.name=:name", Hive.class);
		query.setParameter("name", name);
		List<Hive> results = query.getResultList();
		LOGGER.info("found " + (results == null ? "0" : "" + results.size()) + " hive(s)");
		return results;
	}

	public void insert(Hive hive) {
		em.getTransaction().begin();
		em.persist(hive);
		em.getTransaction().commit();
	}

	public void updateHive(Hive hive) {

		List<Hive> matchingHives = findByName(hive.getName());
		Hive hiveToUpdate = matchingHives.get(0);

		em.getTransaction().begin();
		// Setters for update the record
		hiveToUpdate.setBlock_blood_end_date(hive.getBlock_blood_end_date());
		hiveToUpdate.setBlock_blood_start_date(hive.getBlock_blood_start_date());
		hiveToUpdate.setBrood_state(hive.getBrood_state());
		hiveToUpdate.setCandy_qty(hive.getCandy_qty());
		hiveToUpdate.setCandy_supplied_at(hive.getCandy_supplied_at());
		hiveToUpdate.setChecked_at(hive.getChecked_at());
		hiveToUpdate.setFleeting_state(hive.getFleeting_state());
		hiveToUpdate.setHoney_production_qty(hive.getHoney_production_qty());
		hiveToUpdate.setHoney_production_type(hive.getHoney_production_type());
		hiveToUpdate.setHoney_supplying(hive.getHoney_supplying());
		hiveToUpdate.setQty_frame_number(hive.getQty_frame_number());
		hiveToUpdate.setTray_checked_at(hive.getTray_checked_at());
		hiveToUpdate.setQueen_bee_checked_at(hive.getQueen_bee_checked_at());
		hiveToUpdate.setQueen_bee_color(hive.getQueen_bee_color());
		hiveToUpdate.setQueen_bee_goodness(hive.getQueen_bee_goodness());
		hiveToUpdate.setSyrup1_supplied_at(hive.getSyrup1_supplied_at());
		hiveToUpdate.setSyrup2_supplied_at(hive.getSyrup2_supplied_at());
		hiveToUpdate.setVarroa_checked_at(hive.getVarroa_checked_at());
		hiveToUpdate.setVarroa_status(hive.getVarroa_status());
		em.getTransaction().commit();

	}

}

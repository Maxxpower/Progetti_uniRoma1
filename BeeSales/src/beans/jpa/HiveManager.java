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
	
	public HiveManager(){
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = emf.createEntityManager();
	}
	
	
	public List<Hive> getAll(){
		TypedQuery<Hive> query = em.createQuery("SELECT h FROM Hive h", Hive.class);
		List<Hive> results = query.getResultList();
		LOGGER.info("found " + (results==null ? "0" : "" + results.size()) + " hive(s)");
		return results;
	}
	
	public List<Hive> findByName(String name){
		TypedQuery<Hive> query = em.createQuery("SELECT h FROM Hive h where h.name=:name", Hive.class);
		query.setParameter("name", name);
		List<Hive> results = query.getResultList();
		LOGGER.info("found " + (results==null ? "0" : "" + results.size()) + " hive(s)");
		return results;
	}
	
	public void insert(Hive hive){
		em.getTransaction().begin();
		em.persist(hive);
		em.getTransaction().commit();
	}

}

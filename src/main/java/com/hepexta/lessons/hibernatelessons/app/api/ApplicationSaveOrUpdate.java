package com.hepexta.lessons.hibernatelessons.app.api;


import com.hepexta.lessons.hibernatelessons.data.HibernateUtil;
import com.hepexta.lessons.hibernatelessons.data.entities.Bank;
import org.hibernate.Session;

import static com.hepexta.lessons.hibernatelessons.app.api.TestEntities.createBank;

public class ApplicationSaveOrUpdate {

	public static void main(String[] args) {
		try {
			// Open Persistence Context 1
			Session session = HibernateUtil.getSessionFactory().openSession();
			org.hibernate.Transaction transaction = session.beginTransaction();
			// Put the entity in the persistence context
			Bank detachedBank = (Bank) session.get(Bank.class, 1L);
			// Persist the entity to the database
			transaction.commit();
			// Close Persistence Context 1. This bank entity's state is detached.
			session.close();

            // Create a new bank entity using new operator and constructor.
            // This bank entity's state is transient.
			Bank transientBank = createBank();

            // Open Persistence Context 2
			Session session2 = HibernateUtil.getSessionFactory().openSession();
			org.hibernate.Transaction transaction2 = session2.beginTransaction();

            // saveOrUpdate is flexible method that handles both detached and transient entity states.
            // Hibernate looks at the identifier property for the entity and using the presence or lack
            // of identifier the API is smart enough to route the method invocation to the appropriate
            // method saveOrUpdate.
			session2.saveOrUpdate(transientBank);
			session2.saveOrUpdate(detachedBank);
			detachedBank.setName("Test Bank 2");
            // Persist the entity to the database
			transaction2.commit();
            // Close Persistence Context 2.
			session2.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibernateUtil.getSessionFactory().close();
		}
	}


}

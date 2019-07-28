package com.hepexta.lessons.hibernatelessons.app.api;

import com.hepexta.lessons.hibernatelessons.data.HibernateUtil;
import com.hepexta.lessons.hibernatelessons.data.entities.Bank;
import org.hibernate.Session;

public class ApplicationReattachingDetachedEntities {

	public static void main(String[] args) {
		try {
            // Open Persistence Context 1
			Session session = HibernateUtil.getSessionFactory().openSession();
			org.hibernate.Transaction transaction = session.beginTransaction();
			// Put the entity in the persistence context
			Bank bank = (Bank) session.get(Bank.class, 1L);
			// Persist the entity to the database
            transaction.commit();
            // Close Persistent Context 1. Our bank entity is detached. It is not attached to any persistence context.
			session.close();

            // Open Persistence Context 2
			Session session2 = HibernateUtil.getSessionFactory().openSession();
			org.hibernate.Transaction transaction2 = session2.beginTransaction();

            // Bank entity is not contained within the second persistence context.
			System.out.println(session2.contains(bank));
            // Update will cause bank entity to be reattached to the persistence context causing it to go from a
            // a detached state to a persistent state.  Once an entity is reattached Hibernate will think its dirty
            // (entity has a different state or some of the fields have been changed).An update statement to the
            // database which  will cause the database to be synched to the new state of the entity.
			session2.update(bank);
            // Set one of the fields on the bank entity after we have reattached it.
			bank.setName("Test Bank");
			System.out.println("Update Method Invoked");
            // Bank entity is now contained within the second persistence context.
			System.out.println(session2.contains(bank));

            // Persist the entity to the database
			transaction2.commit();
			session2.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibernateUtil.getSessionFactory().close();
		}
	}

}

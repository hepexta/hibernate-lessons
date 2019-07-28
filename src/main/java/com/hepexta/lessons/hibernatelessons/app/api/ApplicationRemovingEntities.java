package com.hepexta.lessons.hibernatelessons.app.api;

import com.hepexta.lessons.hibernatelessons.data.HibernateUtil;
import com.hepexta.lessons.hibernatelessons.data.entities.Bank;
import org.hibernate.Session;

public class ApplicationRemovingEntities {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
            // Open Persistence Context 1
			org.hibernate.Transaction transaction = session.beginTransaction();

            // Put the entity in the persistent context
			Bank bank = (Bank) session.get(Bank.class, 1L);
			
			System.out.println(session.contains(bank));

			// When deleting an entity Hibernate will change the state of the entity to remove which will remove the
            // entity from the persistence context. No longer use the entity within our code!
            // Let garbage collection remove the object from the application.
			session.delete(bank);
			System.out.println("Method Invoked");
			System.out.println(session.contains(bank));

            // The entity will subsequently be removed from the database when we commit our transaction or flush
            // our session.
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close(); // Close Persistence Context 1
			HibernateUtil.getSessionFactory().close();
		}
	}
}

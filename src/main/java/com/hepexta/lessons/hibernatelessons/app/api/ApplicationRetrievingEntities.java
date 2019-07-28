package com.hepexta.lessons.hibernatelessons.app.api;

import com.hepexta.lessons.hibernatelessons.data.HibernateUtil;
import com.hepexta.lessons.hibernatelessons.data.entities.Bank;
import org.hibernate.Session;

public class ApplicationRetrievingEntities {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			org.hibernate.Transaction transaction = session.beginTransaction();

			// Hibernate will read records, translate the records into objects, and then insert
			// the objects into the persistence context with a persistent state.


			 Bank bank = (Bank) session.get(Bank.class, 1L);

            // In the next line, instead of going back to the database Hibernate consults its
            // persistence context before issuing the database command. It finds that there is
            // a bank entity with the same identifier. It then simply returns that entity from
            // the persistence context. So it keeps us from issuing an unnecessary database call.
            //bank = (Bank) session.get(Bank.class,1L);

            // Will generate a NPE because there is no ID of 123
            Bank bank1 = (Bank) session.get(Bank.class, 123L);

            // ObjectNotFoundException from Hibernate better than NPE using load method.
            Bank bank2 = (Bank) session.load(Bank.class, 123L);

            // load is similar to the get method.  When we use the load method Hibernate always
            // tries to return a proxy which stands in place of the actual entity. Once we actually
            // need to reference something on the entity, say the name of the bank, then the SELECT
            // statement is performed to grab the object from the database and then we can reference
            // that name field upon the entity.  So it tries to delay the execution of the database
            // operation as much as possible.  If you have one entity with an association with
            // another entity and you just want to establish that association you can use the load
            // method to load that entity and set the field on that other entity and you would
            // never have to issue a select statement so some performance gains can be achieved.
			Bank bank3 = (Bank) session.load(Bank.class, 1L);
			System.out.println("Method Executed");
			
			System.out.println(bank.getName());
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}
}

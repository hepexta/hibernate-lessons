package com.hepexta.lessons.hibernatelessons.app.api;

import com.hepexta.lessons.hibernatelessons.data.HibernateUtil;
import com.hepexta.lessons.hibernatelessons.data.entities.Bank;
import org.hibernate.Session;

import java.util.Date;

public class ApplicationModifyingEntities {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			org.hibernate.Transaction transaction = session.beginTransaction();

			// Get a bank entity from the session with the ID of 1.
			Bank bank = (Bank) session.get(Bank.class, 2L);
			// Make changes to the bank entity. Hibernate will track those changes to the entity
			// and when we commit our transaction those changes will be updated on that entity.
			bank.setName("New Hope Bank");
			bank.setLastUpdatedBy("Kevin Bowersox");
			bank.setLastUpdatedDate(new Date());

			// Once commit is called Hibernate realizes changes have been made to the entity and
			// is no longer is synch with the database. Hibernate issues an update statement to
			// synch up the object model changes with the database.
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}

}

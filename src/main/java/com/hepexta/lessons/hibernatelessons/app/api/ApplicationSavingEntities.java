package com.hepexta.lessons.hibernatelessons.app.api;

import com.hepexta.lessons.hibernatelessons.data.HibernateUtil;
import com.hepexta.lessons.hibernatelessons.data.entities.Account;
import com.hepexta.lessons.hibernatelessons.data.entities.Transaction;
import org.hibernate.Session;

import static com.hepexta.lessons.hibernatelessons.app.api.TestEntities.createNewAccount;
import static com.hepexta.lessons.hibernatelessons.app.api.TestEntities.createNewBeltPurchase;
import static com.hepexta.lessons.hibernatelessons.app.api.TestEntities.createShoePurchase;

public class ApplicationSavingEntities {

	public static void main(String[] args) {

		// Persistence Context 1
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Account account = createNewAccount(); // transient
		Transaction trans1 = createNewBeltPurchase(account); // transient
		Transaction trans2 = createShoePurchase(account); // transient
		account.getTransactions().add(trans1);
		account.getTransactions().add(trans2);

        // See if the objects are associated with the persistent context.
		System.out.println(session.contains(account));
		System.out.println(session.contains(trans1));
		System.out.println(session.contains(trans1));
		
		try {
            // Need to fully qualify Transaction since we have two objects named transaction
			org.hibernate.Transaction transaction = session.beginTransaction();
			session.save(account); // persistent, will cascade the transactions associated with the account

            // See if the objects get transitioned over to the persistent state.
			System.out.println(session.contains(account));
			System.out.println(session.contains(trans1));
			System.out.println(session.contains(trans1));

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close(); // Close Persistence Context 1
			HibernateUtil.getSessionFactory().close();
		}
	}

}

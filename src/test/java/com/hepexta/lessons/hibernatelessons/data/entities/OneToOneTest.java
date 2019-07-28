package com.hepexta.lessons.hibernatelessons.data.entities;

import com.hepexta.lessons.hibernatelessons.EntityChecker;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Date;

public class OneToOneTest extends EntityChecker {

    @Test
    public void test_save() {
        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setFirstName("Kevin");
        user.setLastName("Bowersox");
        user.setAge(20);
        user.setBirthDate(new Date());
        user.setCreatedBy("Kevin Bowersox");
        user.setCreatedDate(new Date());
        user.setEmailAddress("kevin.bowersox@navy.mil");
        user.setLastUpdateDate(new Date());
        user.setLastUpdatedBy("Kevin Bowersox");

        Credential credential = new Credential();
        credential.setPassword("kevinspassword");
        credential.setUsername("kmb385");

        credential.setUser(user);
        /** Only persist the credential. Because we have @OneToOne(cascade=CascadeType.ALL) in Credential class
         *  both credentialUniDirectionalOneToOne and userHibernateAPI will both be persisted. */
        session.save(credential); // WILL SAVE THE USER
        transaction.commit();

        User dbUser = (User)
                session.get(User.class, user.getId());
        System.out.println(dbUser.getFirstName());
    }

}

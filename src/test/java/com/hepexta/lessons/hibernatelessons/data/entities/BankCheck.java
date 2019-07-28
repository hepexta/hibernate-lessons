package com.hepexta.lessons.hibernatelessons.data.entities;

import com.hepexta.lessons.hibernatelessons.data.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class BankCheck {

    private Session session;

    @Before
    public void setUp() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @After
    public void tearDown() {
        session.close();
    }

    @Test
    public void test_save() {
        Transaction transaction = session.beginTransaction();

        Bank bank = new Bank();
        bank.setName("Federal Trust");
        bank.setAddressLine1("33 Wall Street");
        bank.setAddressLine2("Suite 233");
        bank.setCity("New York");
        bank.setState("NY");
        bank.setZipCode("12345");
        bank.setInternational(false);
        bank.setCreatedBy("Kevin");
        bank.setCreatedDate(new Date());
        bank.setLastUpdatedBy("Kevin");
        bank.setLastUpdatedDate(new Date());
        //bank.getContacts().add("Joe");
        //bank.getContacts().add("Mary");
        bank.getContacts().put("MANAGER", "Joe");
        bank.getContacts().put("TELLER", "Mary");
        session.save(bank);

        transaction.commit();

    }
}

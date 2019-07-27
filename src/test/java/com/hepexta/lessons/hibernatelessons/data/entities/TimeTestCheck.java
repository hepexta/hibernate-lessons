package com.hepexta.lessons.hibernatelessons.data.entities;

import com.hepexta.lessons.hibernatelessons.data.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class TimeTestCheck {
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
        session.beginTransaction();

        TimeTest timeTest = new TimeTest(new Date());

        session.save(timeTest);
        session.getTransaction().commit();

        session.beginTransaction();

        TimeTest ts = (TimeTest) session.get(TimeTest.class, timeTest.getTimeTestId());
        System.out.println(ts);
    }
}

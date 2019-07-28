package com.hepexta.lessons.hibernatelessons;

import com.hepexta.lessons.hibernatelessons.data.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;

public class EntityChecker {

    protected Session session;

    @Before
    public void setUp() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @After
    public void tearDown() {
        session.close();
    }

}

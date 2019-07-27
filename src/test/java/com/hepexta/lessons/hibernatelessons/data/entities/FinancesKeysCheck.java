package com.hepexta.lessons.hibernatelessons.data.entities;

import com.hepexta.lessons.hibernatelessons.data.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FinancesKeysCheck {

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

        FinancesKeys keys = FinancesKeys.builder()
                .tableName("user")
                .value(10)
                .build();

        session.save(keys);
        session.getTransaction().commit();
    }
}

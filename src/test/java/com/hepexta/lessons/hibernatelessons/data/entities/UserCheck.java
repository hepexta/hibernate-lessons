package com.hepexta.lessons.hibernatelessons.data.entities;

import com.hepexta.lessons.hibernatelessons.data.HibernateUtil;
import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static com.hepexta.lessons.hibernatelessons.utils.DateUtils.parseDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCheck {

    Session session;

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

        User user = User.builder()
                .firstName("Sergei")
                .lastName("Kuznetsov")
                .birthDate(parseDate("19.07.1986"))
                .createdDate(new Date())
                .createdBy("Sergei")
                .lastUpdateDate(new Date())
                .lastUpdatedBy("Sergei")
                .emailAddress("mail@mail.com")
                .build();

        session.save(user);
        session.getTransaction().commit();

        session.beginTransaction();
        User dbUser = (User) session.get(User.class, user.getId());

        Assert.assertEquals(user.getId(), dbUser.getId());
        Assert.assertEquals(user.getFirstName(), dbUser.getFirstName());
        Assert.assertEquals(user.getLastName(), dbUser.getLastName());
        Assert.assertEquals(user.getBirthDate(), dbUser.getBirthDate());
        Assert.assertEquals(user.getCreatedBy(), dbUser.getCreatedBy());
        Assert.assertEquals(user.getCreatedDate(), dbUser.getCreatedDate());
        Assert.assertEquals(user.getEmailAddress(), dbUser.getEmailAddress());
        Assert.assertEquals(user.getLastUpdateDate(), dbUser.getLastUpdateDate());
        Assert.assertEquals(user.getLastUpdatedBy(), dbUser.getLastUpdatedBy());
    }

    @Test
    public void test_update() {

        session.beginTransaction();
        User dbUser = (User) session.get(User.class, 1L);

        Date lastUpdateDate = new Date();
        String lastUpdatedBy = lastUpdateDate.toString();
        dbUser.setLastUpdateDate(lastUpdateDate); // updatable == true
        dbUser.setLastUpdatedBy(lastUpdatedBy); // updatable == true

        session.getTransaction().commit();

        session.beginTransaction();
        dbUser = (User) session.get(User.class, 1L);

        Assert.assertEquals(lastUpdateDate, dbUser.getLastUpdateDate());
        Assert.assertEquals(lastUpdatedBy, dbUser.getLastUpdatedBy());
    }

    @Test
    public void test_update_nonupdatable() {

        session.beginTransaction();
        User dbUser = (User) session.get(User.class, 1L);

        Date lastUpdateDate = new Date();
        String lastUpdatedBy = lastUpdateDate.toString();
        dbUser.setCreatedBy(lastUpdatedBy); // updatable == false
        dbUser.setCreatedDate(lastUpdateDate); // updatable == false
        session.update(dbUser);
        session.getTransaction().commit();

        session.beginTransaction();
        dbUser = (User) session.get(User.class, 1L);

        Assert.assertNotEquals(lastUpdateDate, dbUser.getCreatedDate());
        Assert.assertNotEquals(lastUpdatedBy, dbUser.getCreatedBy());
    }

    @Test(expected = PropertyValueException.class)
    public void test_update_nullable() {

        session.beginTransaction();
        User dbUser = (User) session.get(User.class, 1L);
        dbUser.setBirthDate(null);
        session.update(dbUser);
        session.getTransaction().commit();
    }
}

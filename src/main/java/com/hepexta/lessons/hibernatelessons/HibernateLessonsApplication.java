package com.hepexta.lessons.hibernatelessons;

import com.hepexta.lessons.hibernatelessons.data.HibernateUtil;
import com.hepexta.lessons.hibernatelessons.data.entities.User;
import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.hepexta.lessons.hibernatelessons.utils.DateUtils.parseDate;

@SpringBootApplication
public class HibernateLessonsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateLessonsApplication.class, args);
        Session session = HibernateUtil.getSessionFactory().openSession();
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

        session.close();
    }



}

package com.hepexta.lessons.hibernatelessons;

import com.hepexta.lessons.hibernatelessons.data.HibernateUtil;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibernateLessonsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateLessonsApplication.class, args);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.close();
    }

}

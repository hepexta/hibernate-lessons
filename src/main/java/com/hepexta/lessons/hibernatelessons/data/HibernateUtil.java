package com.hepexta.lessons.hibernatelessons.data;

import com.hepexta.lessons.hibernatelessons.data.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.configure();
        return configuration.buildSessionFactory(
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
                .build());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


}

package ru.vasili_zlobin.hibernate.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.vasili_zlobin.hibernate.model.Customer;
import ru.vasili_zlobin.hibernate.model.Product;

public class ConnectFactory {
    private static final SessionFactory SESSION_FACTORY = new Configuration()
            .addAnnotatedClass(Product.class)
            .addAnnotatedClass(Customer.class)
            .buildSessionFactory();

    public static Session getSession() {
        return SESSION_FACTORY.getCurrentSession();
    }
}

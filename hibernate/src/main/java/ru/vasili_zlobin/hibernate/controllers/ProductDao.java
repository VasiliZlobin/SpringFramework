package ru.vasili_zlobin.hibernate.controllers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.vasili_zlobin.hibernate.model.Product;

import java.util.List;

public class ProductDao {
    private static final SessionFactory SESSION_FACTORY = new Configuration()
            .addAnnotatedClass(Product.class)
            .buildSessionFactory();

    private static Session getSession() {
        return SESSION_FACTORY.getCurrentSession();
    }

    public void saveOrUpdate(Product product) {
        Session session = getSession();
        session.beginTransaction();
        if (product.getId() == null) {
            session.persist(product);
        } else {
            session.merge(product);
        }
        session.getTransaction().commit();
    }

    public List<Product> findAll() {
        Session session = getSession();
        session.beginTransaction();
        List<Product> result = session.createQuery("FROM Product", Product.class).getResultList();
        session.getTransaction().commit();
        return result;
    }

    public Product findById(Long id) {
        Session session = getSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();
        return product;
    }

    public void deleteById(Long id) {
        Session session = getSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Product WHERE id = :product_id", null)
                        .setParameter("product_id", id).executeUpdate();
        session.getTransaction().commit();
    }
}

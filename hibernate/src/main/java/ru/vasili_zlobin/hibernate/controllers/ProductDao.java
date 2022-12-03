package ru.vasili_zlobin.hibernate.controllers;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.vasili_zlobin.hibernate.model.Customer;
import ru.vasili_zlobin.hibernate.model.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDao {

    private static Session getSession() {
        return ConnectFactory.getSession();
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

    public List<Customer> getCustomersForProductId(Long id) {
        Session session = getSession();
        session.beginTransaction();
        Product product = session.createQuery("SELECT p FROM Product p LEFT JOIN FETCH p.customers WHERE p.id = :id"
                        , Product.class).setParameter("id", id).getSingleResult();
        List<Customer> result = new ArrayList<>();
        if (product != null) {
            result = product.getCustomers();
        }
        session.getTransaction().commit();
        return result;
    }
}

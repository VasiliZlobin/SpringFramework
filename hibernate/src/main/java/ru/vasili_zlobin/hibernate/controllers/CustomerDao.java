package ru.vasili_zlobin.hibernate.controllers;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.vasili_zlobin.hibernate.model.Customer;
import ru.vasili_zlobin.hibernate.model.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDao {

    private static Session getSession() {
        return ConnectFactory.getSession();
    }

    public void saveOrUpdate(Customer customer) {
        Session session = getSession();
        session.beginTransaction();
        if (customer.getId() == null) {
            session.persist(customer);
        } else {
            session.merge(customer);
        }
        session.getTransaction().commit();
    }

    public List<Customer> findAll() {
        Session session = getSession();
        session.beginTransaction();
        List<Customer> result = session.createQuery("FROM Customer", Customer.class).getResultList();
        session.getTransaction().commit();
        return result;
    }

    public Customer findById(Long id) {
        Session session = getSession();
        session.beginTransaction();
        Customer result = session.get(Customer.class, id);
        session.getTransaction().commit();
        return result;
    }

    public void deleteById(Long id) {
        Session session = getSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Customer WHERE id = :customer_id", null)
                        .setParameter("customer_id", id).executeUpdate();
        session.getTransaction().commit();
    }

    public List<Product> getProductsForCustomerId(Long id) {
        Session session = getSession();
        session.beginTransaction();
        Customer customer = session.createQuery("SELECT c FROM Customer c JOIN FETCH c.products WHERE c.id = :id"
                        , Customer.class).setParameter("id", id).getSingleResult();
        List<Product> result = new ArrayList<>();
        if (customer != null) {
            result = customer.getProducts();
        }
        session.getTransaction().commit();
        return result;
    }
}

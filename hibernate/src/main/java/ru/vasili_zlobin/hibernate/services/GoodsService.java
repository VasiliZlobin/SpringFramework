package ru.vasili_zlobin.hibernate.services;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vasili_zlobin.hibernate.controllers.ConnectFactory;
import ru.vasili_zlobin.hibernate.controllers.CustomerDao;
import ru.vasili_zlobin.hibernate.controllers.ProductDao;
import ru.vasili_zlobin.hibernate.model.Customer;
import ru.vasili_zlobin.hibernate.model.Product;

import java.util.List;

public class GoodsService {
    private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.vasili_zlobin.hibernate.controllers");

    public static List<Product> getProductsForCustomerId(Long id) {
        return context.getBean(CustomerDao.class).getProductsForCustomerId(id);
    }

    public static List<Customer> getCustomersForProductId(Long id) {
        return context.getBean(ProductDao.class).getCustomersForProductId(id);
    }

    public static Double getPriceInPurchase(Long customerId, Long productId) {
        Session session = ConnectFactory.getSession();
        session.beginTransaction();
        String sql = "SELECT COALESCE(ps.sum / ps.amount, p.price) FROM purchases ps" +
                " JOIN products p ON p.id = ps.product_id WHERE ps.customer_id = :cid AND ps.product_id = :pid";
        NativeQuery<Double> query = session.createNativeQuery(sql, Double.class);
        query.setParameter("cid", customerId).setParameter("pid", productId);
        Double result;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            result = null;
        }
        session.getTransaction().commit();
        return result;
    }
}

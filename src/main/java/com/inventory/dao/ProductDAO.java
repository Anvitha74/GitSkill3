package com.inventory.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;
public class ProductDAO {
    public void saveProduct(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(product);
        tx.commit();
        session.close();
    }
    public Product getProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }
    public void updateProduct(int id, double price, int quantity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Product product = session.get(Product.class, id);
        product.setPrice(price);
        product.setQuantity(quantity);
        session.merge(product);
        tx.commit();
        session.close();
    }
    public void deleteProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product product = session.get(Product.class, id);

        if (product != null) {
            session.remove(product);   // Hibernate 6 method
            System.out.println("Product deleted successfully");
        } else {
            System.out.println("Product not found with ID: " + id);
        }

        tx.commit();
        session.close();
    }

}


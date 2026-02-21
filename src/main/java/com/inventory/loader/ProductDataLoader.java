package com.inventory.loader;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.inventory.entity.Product;

public class ProductDataLoader {

    public static void loadSampleProducts(Session session) {
        Transaction tx = session.beginTransaction();

        session.persist(new Product("Laptop", "Electronics", 899.99, 15));
        session.persist(new Product("Mouse", "Electronics", 25.50, 50));
        session.persist(new Product("Keyboard", "Electronics", 45.00, 30));
        session.persist(new Product("Monitor", "Electronics", 299.99, 20));
        session.persist(new Product("Desk Chair", "Furniture", 150.00, 0));
        session.persist(new Product("Desk Lamp", "Furniture", 35.75, 25));
        session.persist(new Product("Notebook", "Stationery", 5.99, 100));
        session.persist(new Product("Pen Set", "Stationery", 12.50, 75));

        tx.commit();
    }
}
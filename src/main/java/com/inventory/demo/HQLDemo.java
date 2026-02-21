package com.inventory.demo;
import org.hibernate.query.Query;
import com.inventory.entity.Product;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.inventory.entity.Product;
import com.inventory.loader.ProductDataLoader;
import com.inventory.util.HibernateUtil;

public class HQLDemo {
	
	public static void getFirstThreeProducts(Session session) {

	    String hql = "FROM Product";

	    Query<Product> query = session.createQuery(hql, Product.class);

	    // Pagination settings
	    query.setFirstResult(0);   // start from index 0
	    query.setMaxResults(3);    // get 3 records

	    List<Product> list = query.list();

	    System.out.println("=== First 3 Products ===");

	    for (Product p : list) {
	        System.out.println(p.getName() + " - " + p.getPrice());
	    }
	}
	
	public static void getNextThreeProducts(Session session) {

	    String hql = "FROM Product";

	    Query<Product> query = session.createQuery(hql, Product.class);

	    query.setFirstResult(3);   // skip first 3 records
	    query.setMaxResults(3);    // get next 3

	    List<Product> list = query.list();

	    System.out.println("=== Next 3 Products ===");

	    for (Product p : list) {
	        System.out.println(p.getName() + " - " + p.getPrice());
	    }
	}
	
	
	
	public static void sortPriceAsc(Session session) {

	    String hql = "FROM Product p ORDER BY p.price DESC";

	    var list = session.createQuery(hql, Product.class).list();

	    System.out.println("Products sorted by price (DESC):");

	    for (Product p : list) {
	        System.out.println(p.getName() + " - " + p.getPrice());
	    }
	}
	
	public static void countTotalProducts(Session session) {

	    String hql = "SELECT COUNT(p) FROM Product p";

	    Long count = session
	            .createQuery(hql, Long.class)
	            .uniqueResult();

	    System.out.println("Total products: " + count);
	}
	
	public static void groupByDescription(Session session) {

	    String hql =
	        "SELECT p.description, COUNT(p) FROM Product p GROUP BY p.description";

	    var list = session
	            .createQuery(hql, Object[].class)
	            .list();

	    System.out.println("Products grouped by description:");

	    for (Object[] row : list) {
	        System.out.println(row[0] + " -> " + row[1]);
	    }
	}
	
	
	public static void filterByPriceRange(Session session) {

	    String hql =
	        "FROM Product p WHERE p.price BETWEEN :min AND :max";

	    var query = session.createQuery(hql, Product.class);

	    query.setParameter("min", 20.0);
	    query.setParameter("max", 100.0);

	    var list = query.list();

	    System.out.println("Products between 20 and 100:");

	    for (Product p : list) {
	        System.out.println(p.getName() + " - " + p.getPrice());
	    }
	}
	
	public static void findStartingWithD(Session session) {

	    String hql =
	        "FROM Product p WHERE p.name LIKE :pattern";

	    var query = session.createQuery(hql, Product.class);

	    query.setParameter("pattern", "D%");

	    var list = query.list();

	    System.out.println("Names starting with D:");

	    for (Product p : list) {
	        System.out.println(p.getName());
	    }
	}
	
    public static void main(String[] args) {

    	SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        // ⚠️ keep this commented after first run
        // ProductDataLoader.loadSampleProducts(session);

        // 🔥 CALL PAGINATION METHODS
//        getFirstThreeProducts(session);
//        getNextThreeProducts(session);
        findStartingWithD(session);
        session.close();
        factory.close();
    }
    
}


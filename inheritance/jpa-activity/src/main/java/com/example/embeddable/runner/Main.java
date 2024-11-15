package com.example.embeddable.runner;

import com.example.embeddable.Address;
import com.example.embeddable.EmployeeEmbedded;
import com.example.util.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Main {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Create and persist an Employee with an embedded Address
            Address address = new Address("123 Main St", "Springfield", "IL", "62701");
            EmployeeEmbedded employee = new EmployeeEmbedded();
            employee.setName("John Doe");
            employee.setAddress(address);

            em.persist(employee);

            tx.commit();

            // Retrieve and print the Employee with the embedded Address
            EmployeeEmbedded retrievedEmployee = em.find(EmployeeEmbedded.class, employee.getId());
            System.out.println("Retrieved Employee: " + retrievedEmployee);

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            JPAUtil.close();
        }
    }
}

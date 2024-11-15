package com.example.mapped.runner;

import com.example.mapped.ConsultantEmployeeMapped;
import com.example.mapped.PermanentEmployeeMapped;
import com.example.util.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Main {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Persist PermanentEmployee
            PermanentEmployeeMapped permEmployee = new PermanentEmployeeMapped();
            permEmployee.setName("Alice");
            permEmployee.setDepartment("IT");
            permEmployee.setSalary(80000);
            permEmployee.setBenefits("Health, Dental");

            em.persist(permEmployee);

            // Persist ConsultantEmployee
            ConsultantEmployeeMapped consEmployee = new ConsultantEmployeeMapped();
            consEmployee.setName("Bob");
            consEmployee.setDepartment("Finance");
            consEmployee.setHourlyRate(75);
            consEmployee.setHoursWorked(40);

            em.persist(consEmployee);

            tx.commit();

            // Retrieving objects
            PermanentEmployeeMapped retrievedPermEmployee = em.find(PermanentEmployeeMapped.class, permEmployee.getId());
            ConsultantEmployeeMapped retrievedConsEmployee = em.find(ConsultantEmployeeMapped.class, consEmployee.getId());

            System.out.println("Retrieved PermanentEmployeeMapped: " + retrievedPermEmployee);
            System.out.println("Retrieved ConsultantEmployeeMapped: " + retrievedConsEmployee);

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

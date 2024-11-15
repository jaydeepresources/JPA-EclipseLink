package com.example.inheritance.runner;

import com.example.inheritance.ConsultantEmployeeInheritance;
import com.example.inheritance.PermanentEmployeeInheritance;
import com.example.util.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Main {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Persist PermanentEmployeeInheritance
            PermanentEmployeeInheritance permEmployee = new PermanentEmployeeInheritance();
            permEmployee.setName("Alice");
            permEmployee.setDepartment("IT");
            permEmployee.setSalary(80000);
            permEmployee.setBenefits("Health, Dental");

            em.persist(permEmployee);

            // Persist ConsultantEmployeeInheritance
            ConsultantEmployeeInheritance consEmployee = new ConsultantEmployeeInheritance();
            consEmployee.setName("Bob");
            consEmployee.setDepartment("Finance");
            consEmployee.setHourlyRate(75);
            consEmployee.setHoursWorked(40);

            em.persist(consEmployee);

            tx.commit();

            // Retrieving objects
            PermanentEmployeeInheritance retrievedPermEmployee = em.find(PermanentEmployeeInheritance.class, permEmployee.getId());
            ConsultantEmployeeInheritance retrievedConsEmployee = em.find(ConsultantEmployeeInheritance.class, consEmployee.getId());

            System.out.println("Retrieved PermanentEmployeeInheritance: " + retrievedPermEmployee);
            System.out.println("Retrieved ConsultantEmployeeInheritance: " + retrievedConsEmployee);

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

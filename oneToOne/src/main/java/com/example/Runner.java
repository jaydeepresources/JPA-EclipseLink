package com.example;

import javax.persistence.*;

public class Runner {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("activity-app");

    public static void main(String[] args) {

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

//        GST gst = new GST();
//        gst.setGstNumber("GSTIN9457976");
//
//        Company company = new Company();
//        company.setName("HLAG");
//
//        company.setGst(gst);
//        gst.setCompany(company);
//
//        entityManager.persist(gst);
//        System.out.println("Gst Saved");
//
//        entityManager.persist(company);
//        System.out.println("Company Saved");

        Company company = entityManager.find(Company.class, 1L);
//        System.out.println(company.getName() + ", GST NO=" + company.getGst().getGstNumber());

        CompanyDTO companyDTO =
                new CompanyDTO(company.getId(),company.getName(),
                        new GSTDTO(company.getGst().getId(),company.getGst().getGstNumber()));

        System.out.println(companyDTO);

//        entityTransaction.commit();
        entityManager.close();

    }
}

package com.epam.jdbc;

import com.epam.entities.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class JPAExample {
    private static final String PERSISTENT_UNIT_NAME = "jpaExample";
    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("Select account from Account account");

        List<Account> accounts = query.getResultList();
        accounts.forEach(System.out::println);


        entityManager.getTransaction().begin();
        Account account = new Account();
        account.setAccountName("sushmitha");
        account.setAccountPassword("sush");
        account.setAccountCreatedDate(new Date());
        entityManager.persist(account);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


}

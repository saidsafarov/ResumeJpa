package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AbstractDao {

    public Connection connect() throws Exception {
        String url = "jdbc:mysql://localhost:3306/resume?serverTimezone=UTC";
        String username = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
    private static EntityManagerFactory emf = null;

    public EntityManager em() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("resumeappPu");
        }
        EntityManager entityManager = emf.createEntityManager();
        return entityManager;

    }

    public void closeEmf() {
        emf.close();
    }

}














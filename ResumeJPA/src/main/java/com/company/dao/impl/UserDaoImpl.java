package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        EntityManager em = em();

        String jpql = "select u from User u where 1 = 1 ";

        if (name != null && !name.trim().isEmpty()) {
            jpql += "and u.name= :name ";
        }
        if (surname != null && !surname.trim().isEmpty()) {
            jpql += "and u.surname= :surname ";
        }
        if (nationalityId != null) {
            jpql += "and u.nationalityId.id= :nid";
        }
        jpql = jpql + " ORDER BY id ASC";

        Query query = em.createQuery(jpql, User.class);

        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", name);
        }
        if (surname != null && !surname.trim().isEmpty()) {
            query.setParameter("surname", surname);
        }
        if (nationalityId != null && nationalityId != 0) {
            query.setParameter("nid", nationalityId);
        }
        return query.getResultList();
    }

    @Override
    public List<User> getAll() {
        EntityManager em = em();
        List<User> list = em.createNamedQuery("User.findAll", User.class).getResultList();
        em.close();
        return list;
    }

    @Override
    public User getById(int userId) {
        EntityManager em = em();

        User user = em.find(User.class, userId);

        em.close();
        return user;
    }

    @Override
    public boolean add(User user) {

        EntityManager em = em();
        em.getTransaction().begin();

        Query query = em.createNativeQuery("INSERT INTO"
                + "user(name, surname, email, phone, password, profile_description, address,"
                + "birthdate, birthplace_id, nationality_id, authority_id)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        query.setParameter(1, user.getName());
        query.setParameter(2, user.getSurname());
        query.setParameter(3, user.getEmail());
        query.setParameter(4, user.getPhone());
        query.setParameter(6, user.getProfileDescription());
        query.setParameter(8, user.getBirthdate());
        query.setParameter(9, user.getBirthplaceId().getId());
        query.setParameter(10, user.getNationalityId().getId());
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
        return true;
    }

    @Override
    public boolean delete(User user) {
        EntityManager em = em();

        em.getTransaction().begin();
        em.remove(em.find(User.class, user.getId()));
        em.getTransaction().commit();

        em.close();
        return true;
    }

    @Override
    public boolean deleteById(int userId) {
        EntityManager em = em();

        em.getTransaction().begin();
        em.remove(em.find(User.class, userId));
        em.getTransaction().commit();

        em.close();
        return true;
    }

    @Override
    public boolean update(User u) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
        em.close();
        return true;
    }

}

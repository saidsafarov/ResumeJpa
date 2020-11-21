package com.company.dao.impl;

import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;
import com.company.entity.UserSkill;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class SkillDaoImpl extends AbstractDao implements SkillDaoInter {

    @Override
    public List<Skill> getAll() {
        EntityManager em = em();

        String jpql = "select s from Skill s";
        Query query = em.createQuery(jpql, Skill.class);
        List<Skill> list = query.getResultList();

        return list;
    }

    @Override
    public Skill getById(int id) {
        EntityManager em = em();

        Skill skill = em.find(Skill.class, id);

        em.close();
        return skill;
    }

    @Override
    public boolean add(Skill skill) {
        EntityManager em = em();
        em.getTransaction().begin();

        Query query = em.createNativeQuery("INSERT INTO skill (name) VALUES (?);");
        query.setParameter(1, skill.getName());
        query.executeUpdate();

        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean update(Skill skill) {
        EntityManager em = em();
        em.getTransaction().begin();

        em.merge(skill);

        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean delete(Skill skill) {
        EntityManager em = em();
        em.getTransaction().begin();

        em.remove(em.find(Skill.class, skill.getId()));

        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean deleteById(int id) {
        EntityManager em = em();
        em.getTransaction().begin();

        em.remove(em.find(Skill.class, id));

        em.getTransaction().commit();
        em.close();
        return true;
    }

    public List<UserSkill> getAllSkillByUserId(int UserId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

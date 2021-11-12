package com.epam.demo.spring.dbaccess.dao;

import com.epam.demo.spring.dbaccess.entities.Session;
import com.epam.demo.spring.dbaccess.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

public class SessionDao {

    @PersistenceContext
    public EntityManager entityManager;

    @Transactional
    public void create(Session session) {
        entityManager.persist(session);
    }

    public List<Session> findByUser(User user) {
        return entityManager.createQuery(
                        "select Session from Session where Session.user = :user",
                        Session.class)
                .setParameter("user", user)
                .getResultList();
    }
}

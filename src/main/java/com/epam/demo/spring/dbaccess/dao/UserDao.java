package com.epam.demo.spring.dbaccess.dao;

import com.epam.demo.spring.dbaccess.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void create(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void save(User user) {
        entityManager.merge(user);
    }

    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    public User findByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(criteriaBuilder.equal(root.get("userName"), name));

        return entityManager.createQuery(query).getSingleResult();
    }

    public User findByPass(String pass) {
        return entityManager.createQuery(
                        "select User from User where userPass = :userPass",//JPQL
                        User.class)
                .setParameter("userPass", pass)
                .getSingleResult();
    }

    public List<User> findAll() {
        CriteriaQuery<User> query = entityManager.getCriteriaBuilder().createQuery(User.class);
        query.select(query.from(User.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Transactional
    public void delete(User user) {
        entityManager.remove(user);
    }

}

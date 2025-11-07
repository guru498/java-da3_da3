package com.example.dao;

import com.example.model.Student;
import com.example.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentDAO {

    public Student create(Student student) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            return student;
        } finally {
            em.close();
        }
    }

    public Student update(Student student) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Student merged = em.merge(student);
            em.getTransaction().commit();
            return merged;
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Student s = em.find(Student.class, id);
            if (s != null) {
                em.remove(s);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Student findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }

    public List<Student> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Student> q = em.createQuery("SELECT s FROM Student s ORDER BY s.id", Student.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}

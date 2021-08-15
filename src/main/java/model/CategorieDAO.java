package model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import entity.Categorie;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class CategorieDAO implements DAO<Categorie> {

    private final SessionFactory sessionFactory;

    public CategorieDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void insert(Categorie obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(obj);
        transaction.commit();
        session.close();
    }

    public void update(Categorie obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(obj);
        transaction.commit();
        session.close();
    }

    public void delete(Categorie obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(Categorie.class, obj.getId()));
        transaction.commit();
        session.close();
    }

    public Categorie searchById(long id) {
        Session session = sessionFactory.openSession();
        return session.get(Categorie.class, id);
    }

    public List<Categorie> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Categorie> query = session.createQuery("from Categorie");
        return query.list();
    }

}

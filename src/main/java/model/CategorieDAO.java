package model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import entity.Categorie;
import util.HibernateUtil;

public class CategorieDAO {
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
        session.delete((Categorie) session.get(Categorie.class, obj.getId()));
        transaction.commit();
        session.close();

    }

    public Categorie chercherParId(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Categorie c1 = (Categorie) session.get(Categorie.class, (long) id);
        transaction.commit();
        session.close();
        return c1;
    }

    public List<Categorie> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Categorie");
        return query.list();
    }

}

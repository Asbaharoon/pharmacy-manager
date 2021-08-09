package model;

import entity.AvocatAdmin;
import entity.Tribunal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class AvocatAdminDAO implements DAO<AvocatAdmin>{
    SessionFactory sessionFactory;

    public AvocatAdminDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public AvocatAdmin getById(int id) {
        Session session = sessionFactory.openSession();
        return session.get(AvocatAdmin.class, id);
    }

    @Override
    public List<AvocatAdmin> getAll() {
        Session session = sessionFactory.openSession();
        Query<AvocatAdmin> query = session.createQuery("from AvocatAdmin");
        return query.list();
    }

    @Override
    public void save(AvocatAdmin avocatAdmin) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(avocatAdmin);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AvocatAdmin avocatAdmin = (session.load(AvocatAdmin.class, id));
        session.delete(avocatAdmin);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(AvocatAdmin avocatAdmin) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(avocatAdmin);
        transaction.commit();
        session.close();
    }
}

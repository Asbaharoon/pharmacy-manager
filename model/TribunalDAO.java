package model;

import entity.Agent;
import entity.Tribunal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class TribunalDAO implements DAO<Tribunal> {
    SessionFactory sessionFactory;

    public TribunalDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Tribunal getById(int id) {
        Session session = sessionFactory.openSession();
        return session.get(Tribunal.class, id);
    }

    @Override
    public List<Tribunal> getAll() {
        Session session = sessionFactory.openSession();
        Query<Tribunal> query = session.createQuery("from Tribunal");
        return query.list();
    }

    @Override
    public void save(Tribunal tribunal) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(tribunal);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Tribunal tribunal = (session.load(Tribunal.class, id));
        session.delete(tribunal);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Tribunal tribunal) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(tribunal);
        transaction.commit();
        session.close();
    }
}

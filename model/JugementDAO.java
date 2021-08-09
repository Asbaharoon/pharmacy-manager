package model;

import entity.Jugement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class JugementDAO implements DAO<Jugement>{
    private SessionFactory sessionFactory;
    public JugementDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }
    @Override
    public List<Jugement> getAll() {
        return null;
    }

    @Override
    public void save(Jugement jugement) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(jugement);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Jugement jugement) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(jugement);
        transaction.commit();
        session.close();
    }

    public boolean isExists(int id) {
        Session session = sessionFactory.openSession();
        Jugement jugement = session.get(Jugement.class, id);
        return jugement != null;
    }
}

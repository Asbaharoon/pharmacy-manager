package model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Vente;
import org.hibernate.query.Query;

public class VenteDAO {

    private final SessionFactory sessionFactory;

    public VenteDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insert(Vente obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(obj);
        transaction.commit();
        session.close();
    }


    public void update(Vente obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(obj);
        transaction.commit();
        session.close();
    }

    public void delete(Vente obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(Vente.class, obj.getId()));
        transaction.commit();
        session.close();
    }

    public Vente searchById(long id) {
        Session session = sessionFactory.openSession();
        return session.get(Vente.class, id);
    }

    public List<Vente> getAll() {
        Session session = sessionFactory.openSession();
        Query<Vente> query = session.createQuery("from Vente");
        return query.list();
    }
}

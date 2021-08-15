package model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Commande;
import org.hibernate.query.Query;

public class CommandeDAO implements DAO<Commande> {

    private final SessionFactory sessionFactory;

    public CommandeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insert(Commande obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(obj);
        transaction.commit();
        session.close();
    }

    public void update(Commande obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(obj);
        transaction.commit();
        session.close();
    }

    public void delete(Commande obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(Commande.class, obj.getId()));
        transaction.commit();
        session.close();
    }

    public Commande searchById(long id) {
        Session session = sessionFactory.openSession();
        return session.get(Commande.class, id);
    }

    public List<Commande> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Commande> query = session.createQuery("from Commande");
        return query.list();
    }
}

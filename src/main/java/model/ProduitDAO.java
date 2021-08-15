package model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Produit;
import org.hibernate.query.Query;

public class ProduitDAO {

    private final SessionFactory sessionFactory;

    public ProduitDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insert(Produit obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(obj);
        transaction.commit();
        session.close();
    }

    public void update(Produit obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(obj);
        transaction.commit();
        session.close();
    }

    public void delete(Produit obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(Produit.class, obj.getId()));
        transaction.commit();
        session.close();

    }

    public Produit searchById(long id) {
        Session session = sessionFactory.openSession();
        return session.get(Produit.class, id);
    }

    public List<Produit> getAll() {
        Session session = sessionFactory.openSession();
        Query<Produit> query = session.createQuery("from Produit");
        return query.list();
    }
}

package model;

import entity.Fournisseur;
import entity.Vente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FournisseurDAO implements DAO<Fournisseur>{
    private final SessionFactory sessionFactory;

    public FournisseurDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insert(Fournisseur obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(obj);
        transaction.commit();
        session.close();
    }


    public void update(Fournisseur obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(obj);
        transaction.commit();
        session.close();
    }

    public void delete(Fournisseur obj) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(Fournisseur.class, obj.getId()));
        transaction.commit();
        session.close();
    }

    public Fournisseur searchById(long id) {
        Session session = sessionFactory.openSession();
        return session.get(Fournisseur.class, id);
    }

    public List<Fournisseur> getAll() {
        Session session = sessionFactory.openSession();
        Query<Fournisseur> query = session.createQuery("from Fournisseur");
        return query.list();
    }
}

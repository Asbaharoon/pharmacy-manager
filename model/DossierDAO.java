package model;

import entity.Dossier;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;
import java.util.List;

public class DossierDAO implements DAO<Dossier>{
    SessionFactory sessionFactory;

    public DossierDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Dossier getById(int id) {
        Session session = sessionFactory.openSession();
        Query<Dossier> query = session.createQuery("from Dossier where ID = " + id);
        return query.getSingleResult();
    }
    @Override
    public List<Dossier> getAll() {
        Session session = sessionFactory.openSession();
        Query<Dossier> query = session.createQuery("from Dossier");
        return query.list();
    }

    public List<Dossier> getAllByAgent(long id) {
        Session session = sessionFactory.openSession();
        Query<Dossier> query = session.createQuery("from Dossier where ID_AGENT = " + id);
        return query.list();
    }

    public List<Dossier> getAllByAgentAndObjet(long id, String objet) {
        Session session = sessionFactory.openSession();
        Query<Dossier> query = session.createQuery("from Dossier where objet = '" + objet + "' and ID_AGENT = " + id + " order by ID_TRIBUNAL ASC" );
        return query.list();
    }

    @Override
    public void save(Dossier dossier) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(dossier);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.load(Dossier.class, id));
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Dossier dossier) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(dossier);
        transaction.commit();
        session.close();
    }
}

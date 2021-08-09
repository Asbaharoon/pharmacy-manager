package model;

import entity.Agent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class AgentDAO {
    private static final SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();
    public static List<Agent> getAll() {
        Session session = sessionFactory.openSession();
        Query<Agent> query = session.createQuery("from Agent");
        return query.list();
    }

    public static Agent getById(long id) {
        Session session = sessionFactory.openSession();
        return session.get(Agent.class, id);
    }


}

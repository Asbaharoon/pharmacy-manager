package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.transaction.Transactional;
import java.util.List;

public class AbstractDAO <T> implements IDAO<T>{
    private Class< T > clazz;
    private final SessionFactory sessionFactory;

    public AbstractDAO() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void setClazz(Class< T > clazzToSet) {
        clazz = clazzToSet;
    }

    @Override
    public List<T> getAll() {
        Transaction txn = getCurrentSession().beginTransaction();
        List<T> list = getCurrentSession()
                .createQuery( "from " + clazz.getName() ).list();
        txn.commit();
        return list;
    }

    @Override
    public T getById(long id) {
        Transaction txn = getCurrentSession().beginTransaction();
        T t = getCurrentSession().get( clazz, id );
        txn.commit();
        return t;
    }

    @Override
    public void save(T t) {
        Transaction txn = getCurrentSession().beginTransaction();
        getCurrentSession().save(t);
        txn.commit();
    }

    @Override
    public void update(T t) {
        Transaction txn = getCurrentSession().beginTransaction();
        getCurrentSession().merge(t);
        txn.commit();
    }

    @Override
    public void delete(T t) {
        Transaction txn = getCurrentSession().beginTransaction();
        getCurrentSession().delete(t);
        txn.commit();
    }

    // TO IMPLEMENT MORE IN SUB CLASSES AS NEEDED...

    protected final Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}

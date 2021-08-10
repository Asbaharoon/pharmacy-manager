package Model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import Entity.Vente;

public class VenteDAO {
	public void insert(Vente obj) {
		Configuration configuration =new Configuration().configure();
		StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
				SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
		  Session session=sessionfactory.openSession();
		  Transaction transaction=session.beginTransaction();
		  session.save(obj);
		  transaction.commit();
		  session.close();
	}
	
	
public void update(Vente obj) {
		
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  session.update(obj);
	  transaction.commit();
	  session.close();
	}
public void delete(Vente obj) {
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  session.delete((Vente) session.get(Vente.class,obj.getIdvente()));
	  transaction.commit();
	  session.close();
	
}

public Vente chercherParId(long id) {
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  Vente c1=(Vente) session.get(Vente.class, (long)id);
	  transaction.commit();
	  session.close();
	return c1;
}
public List<Vente> getAll(){
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  Query query = session.createQuery("from Client");
	  return query.list();
}
}

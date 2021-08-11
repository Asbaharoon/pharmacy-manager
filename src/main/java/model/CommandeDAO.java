package model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import entity.Commande;

public class CommandeDAO {
	public void insert(Commande obj) {
		Configuration configuration =new Configuration().configure();
		StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
				SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
		  Session session=sessionfactory.openSession();
		  Transaction transaction=session.beginTransaction();
		  session.save(obj);
		  transaction.commit();
		  session.close();
	}
	
	
public void update(Commande obj) {
		
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  session.update(obj);
	  transaction.commit();
	  session.close();
	}
public void delete(Commande obj) {
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  session.delete((Commande) session.get(Commande.class,obj.getId()));
	  transaction.commit();
	  session.close();
	
}

public Commande chercherParId(long id) {
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  Commande c1=(Commande) session.get(Commande.class, (long)id);
	  transaction.commit();
	  session.close();
	return c1;
}
public List<Commande> getAll(){
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  Query query = session.createQuery("from Commande");
	  return query.list();
}
}

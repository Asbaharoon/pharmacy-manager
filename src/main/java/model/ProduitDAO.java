package model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import entity.Produit;

public class ProduitDAO {
	public void insert(Produit obj) {
		Configuration configuration =new Configuration().configure();
		StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
				SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
		  Session session=sessionfactory.openSession();
		  Transaction transaction=session.beginTransaction();
		  session.save(obj);
		  transaction.commit();
		  session.close();
	}
	
	
public void update(Produit obj) {
		
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  session.update(obj);
	  transaction.commit();
	  session.close();
	}
public void delete(Produit obj) {
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  session.delete((Produit) session.get(Produit.class,obj.getId()));
	  transaction.commit();
	  session.close();
	
}

public Produit chercherParId(long id) {
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  Produit c1=(Produit) session.get(Produit.class, (long)id);
	  transaction.commit();
	  session.close();
	return c1;
}
public List<Produit> getAll(){
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  Query query = session.createQuery("from Produit");
	  return query.list();
}
}

package Model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import Entity.Categorie;

public class CategorieDAO {
	public void insert(Categorie obj) {
		Configuration configuration =new Configuration().configure();
		StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
				SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
		  Session session=sessionfactory.openSession();
		  Transaction transaction=session.beginTransaction();
		  session.save(obj);
		  transaction.commit();
		  session.close();
	}
	
	
public void update(Categorie obj) {
		
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  session.update(obj);
	  transaction.commit();
	  session.close();
	}
public void delete(Categorie obj) {
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  session.delete((Categorie) session.get(Categorie.class,obj.getIdcategorie()));
	  transaction.commit();
	  session.close();
	
}

public Categorie chercherParId(long id) {
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  Categorie c1=(Categorie) session.get(Categorie.class, (long)id);
	  transaction.commit();
	  session.close();
	return c1;
}
public List<Categorie> getAll(){
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  Query query = session.createQuery("from Categorie");
	  return query.list();
}

}

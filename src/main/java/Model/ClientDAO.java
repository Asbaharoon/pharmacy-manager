package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import Entity.Client;

public class ClientDAO {
	
	
	public void insert(Client obj) {
		Configuration configuration =new Configuration().configure();
		StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
				SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
		  Session session=sessionfactory.openSession();
		  Transaction transaction=session.beginTransaction();
		  session.save(obj);
		  transaction.commit();
		  session.close();
	}
	
	
public void update(Client obj) {
		
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  session.update(obj);
	  transaction.commit();
	  session.close();
	}
public void delete(Client obj) {
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  session.delete((Client) session.get(Client.class,obj.getIdcli()));
	  transaction.commit();
	  session.close();
	
}

public Client chercherParId(long id) {
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  Client c1=(Client) session.get(Client.class, (long)id);
	  transaction.commit();
	  session.close();
	return c1;
}
public List<Client> getAll(){
	Configuration configuration =new Configuration().configure();
	StandardServiceRegistryBuilder builder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			SessionFactory sessionfactory= configuration.buildSessionFactory(builder.build());
	  Session session=sessionfactory.openSession();
	  Transaction transaction=session.beginTransaction();
	  Query query = session.createQuery("from Client");
	  return query.list();
}

}

package util;

import java.util.logging.Level;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

   public static SessionFactory getSessionFactory() {
      java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF); // to prevent UGLY logging
      Configuration config = new Configuration().configure(HibernateUtil.class.getClassLoader().getResource("hibernate.cfg.xml"));
      config.addAnnotatedClass(entity.Agent.class);
      config.addAnnotatedClass(entity.AvocatAdmin.class);
      config.addAnnotatedClass(entity.Dossier.class);
      config.addAnnotatedClass(entity.Jugement.class);
      config.addAnnotatedClass(entity.Tribunal.class);
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
      return config.buildSessionFactory(builder.build());
   }
}

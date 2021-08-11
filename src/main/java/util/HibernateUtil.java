package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;

public class HibernateUtil {
    public static SessionFactory getSessionFactory() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF); // to prevent UGLY logging
        Configuration config = new Configuration().configure(HibernateUtil.class.getClassLoader().getResource("hibernate.cfg.xml"));
        config.addAnnotatedClass(entity.Categorie.class);
        config.addAnnotatedClass(entity.Client.class);
        config.addAnnotatedClass(entity.Commande.class);
        config.addAnnotatedClass(entity.Fournisseur.class);
        config.addAnnotatedClass(entity.Produit.class);
        config.addAnnotatedClass(entity.Vente.class);
        config.addAnnotatedClass(entity.Utilisateur.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
        return config.buildSessionFactory(builder.build());
    }
}

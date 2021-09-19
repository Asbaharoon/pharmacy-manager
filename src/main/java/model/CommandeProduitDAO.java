package model;

import entity.Commande;
import entity.CommandeProduit;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CommandeProduitDAO extends AbstractDAO<CommandeProduit> implements IDAO<CommandeProduit> {
    public CommandeProduitDAO() {
        setClazz(CommandeProduit.class);
    }

    public List<CommandeProduit> getAllByCmd(Commande cmd) {
        Transaction txn = getCurrentSession().beginTransaction();
        List<CommandeProduit> list = getCurrentSession().createQuery("from CommandeProduit cp where cp.commande = :cmd").setParameter("cmd", cmd).list();
        txn.commit();
        return list;
    }
}

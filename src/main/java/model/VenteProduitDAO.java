package model;

import entity.Produit;
import entity.Vente;
import entity.VenteProduit;
import org.hibernate.Transaction;

import java.util.List;

public class VenteProduitDAO extends AbstractDAO<VenteProduit> implements IDAO<VenteProduit> {
    public VenteProduitDAO() {
        setClazz(VenteProduit.class);
    }

    public List<VenteProduit> getAllByVente(Vente vente) {
        Transaction txn = getCurrentSession().beginTransaction();
        List<VenteProduit> list = getCurrentSession().createQuery("from VenteProduit vp where vp.vente = :vente").setParameter("vente", vente).list();
        txn.commit();
        return list;
    }

    public VenteProduit getByVenteAndProduit(Vente vente, Produit produit) {
        Transaction txn = getCurrentSession().beginTransaction();
        VenteProduit venteProduit = (VenteProduit)(getCurrentSession().createQuery("from VenteProduit vp where vp.vente = :vente and vp.produit = :produit").setParameter("vente", vente).setParameter("produit", produit)).getSingleResult();
        txn.commit();
        return venteProduit;
    }
}

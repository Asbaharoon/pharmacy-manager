package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CommandeProduit implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    @Column(name = "qte_commandée")
    private int qteCommandee;

    public CommandeProduit() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public int getQteCommandee() {
        return qteCommandee;
    }

    public void setQteCommandee(int qteCommandee) {
        this.qteCommandee = qteCommandee;
    }
}

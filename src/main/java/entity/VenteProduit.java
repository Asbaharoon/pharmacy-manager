package entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vente_produit")
public class VenteProduit implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "vente_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Vente vente;

    @OneToOne(mappedBy = "venteProduit")
    private Avoir avoir;

    @Column(name = "qte_vendue")
    private int qteVendue;

    public VenteProduit() {
    }

    public VenteProduit(Produit produit, Vente vente, int quantite) {
        this.produit = produit;
        this.vente = vente;
        this.qteVendue = quantite;
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

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public int getQteVendue() {
        return qteVendue;
    }

    public void setQteVendue(int qteVendue) {
        this.qteVendue = qteVendue;
    }
}

package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import util.CategorieValueBridge;

@Entity
@Indexed
public class Produit implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @FullTextField
    @Column(name = "designation", unique = true, nullable = false)
    public String designation;

    @Column(name = "prix_achat")
    public BigDecimal prixAchat;

    @Column(name = "prix_vente")
    public BigDecimal prixVente;

    @Column(name = "qte_stock")
    public int qteStock;

    @Column(name = "labo")
    private String labo;

    @FullTextField
    @Column(name = "principe_actif")
    public String principeActif;

    @GenericField(valueBridge = @ValueBridgeRef(type = CategorieValueBridge.class))
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Categorie categorie;

    @OneToMany(mappedBy = "produit")
    private Set<CommandeProduit> produitsCommandees;

    @OneToMany(mappedBy = "produit")
    private Set<VenteProduit> produitsVendues;


    public Produit() {

    }

    public Produit(String designation, String labo, String principeActif, Categorie categorie, BigDecimal prixAchat, BigDecimal prixVente, int qteStock) {
        this.designation = designation;
        this.labo = labo;
        this.principeActif = principeActif;
        this.categorie = categorie;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.qteStock = qteStock;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String desig) {
        this.designation = desig;
    }

    public BigDecimal getPrixAchat() {
        return this.prixAchat;
    }

    public void setPrixAchat(BigDecimal prixAchat) {
        this.prixAchat = prixAchat;
    }

    public BigDecimal getPrixVente() {
        return this.prixVente;
    }

    public void setPrixVente(BigDecimal prvente) {
        this.prixVente = prvente;
    }

    public int getQteStock() {
        return this.qteStock;
    }

    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }

    public String getLabo() {
        return this.labo;
    }

    public void setLabo(String labo) {
        this.labo = labo;
    }

    public String getPrincipeActif() {
        return this.principeActif;
    }

    public void setPrincipeActif(String principeActif) {
        this.principeActif = principeActif;
    }

    public Categorie getCategorie() {
        return this.categorie;

    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

}


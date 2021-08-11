package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Produit implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "designation", unique = true)
    private String designation;

    @Column(name = "prixAchat")
    public float prixAchat;

    @Column(name = "prvente")
    public float prixVente;

    @Column(name = "qteStock")
    public int qteStock;

    @Column(name = "labo")
    private String labo;

    @Column(name = "principeActif")
    public String principeActif;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCategorie")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Categorie categorie;

    public Produit() {

    }

    public Produit(long id, String designation, float prixAchat, float prixVente, int qteStock, String labo, String principeActif, Categorie categorie) {
        this.id = id;
        this.designation = designation;
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.qteStock = qteStock;
        this.labo = labo;
        this.principeActif = principeActif;
        this.categorie = categorie;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesig() {
        return this.designation;
    }

    public void setDesig(String desig) {
        this.designation = desig;
    }

    public float getPrixAchat() {
        return this.prixAchat;
    }

    public void setPrixAchat(float prixAchat) {
        this.prixAchat = prixAchat;
    }

    public float getPrvente() {
        return this.prixVente;
    }

    public void setPrvente(float prvente) {
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


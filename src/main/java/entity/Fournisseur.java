package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fournisseur implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "teleprt", columnDefinition = "char(10)")
    private String teleprt;

    public Fournisseur() {
    }

    public Fournisseur(long id, String nom, String prenom, String teleprt) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.teleprt = teleprt;
    }

    public long getidFournisseur() {
        return this.id;
    }

    public void setidFournisseur(long idfournisseur) {
        this.id = idfournisseur;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTeleprt() {
        return this.teleprt;
    }

    public void setTeleprt(String teleprt) {
        this.teleprt = teleprt;
    }


}

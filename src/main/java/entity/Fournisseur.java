package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

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

    @Column(name = "tele_prt", columnDefinition = "char(10)")
    private String teleprt;

    @OneToMany(mappedBy = "fournisseur")
    private Set<Commande> commandes;

    public Fournisseur() {
    }

    public Fournisseur(long id, String nom, String prenom, String teleprt) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.teleprt = teleprt;
    }

    public long getId() {
        return this.id;
    }

    public void getId(long idfournisseur) {
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

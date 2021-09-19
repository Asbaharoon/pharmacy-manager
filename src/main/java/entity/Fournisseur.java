package entity;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Indexed
public class Fournisseur implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @FullTextField
    @Column(name = "nom")
    private String nom;

    @FullTextField
    @Column(name = "prenom")
    private String prenom;

    @Column(name = "tele_prt", columnDefinition = "char(10)")
    private String telePrt;
    @OneToMany(mappedBy = "fournisseur")
    private Set<Commande> commandes;

    public Fournisseur() {
    }

    public Fournisseur(long id, String nom, String prenom, String teleprt) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telePrt = teleprt;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long idfournisseur) {
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

    public String getTelePrt() {
        return this.telePrt;
    }

    public void setTelePrt(String telePrt) {
        this.telePrt = telePrt;
    }

    public String toString() {
        return "(" + id + ") " + prenom + " " + nom;
    }

}

package entity;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
@Indexed
@Entity
public class Client implements Serializable {
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

    @FullTextField
    @Column(name = "tele_prt", columnDefinition = "char(12)")
    private String telePrt;

    @OneToMany(mappedBy = "client")
    private Set<Vente> ventes;

    public Client() {

    }

    public Client(long id, String nom, String prenom, String teleprt) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.telePrt = teleprt;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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

}

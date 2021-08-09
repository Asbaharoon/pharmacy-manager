package entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class AvocatAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String nom, prenom;
    @OneToMany(mappedBy = "avocatAdmin")
    private List<Dossier> dossier;

    public AvocatAdmin() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Dossier> getDossier() {
        return dossier;
    }

    public void setDossier(List<Dossier> dossier) {
        this.dossier = dossier;
    }

    public String toString() {
        return nom + " " + prenom;
    }
}

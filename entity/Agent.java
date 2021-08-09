package entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long matricule;
    private String CIN;
    private String nom, prenom;
    @OneToMany(mappedBy = "agent")
    private List<Dossier> dossiers;

    public Agent() {

    }

    public long getMatricule() {
        return matricule;
    }

    public void setMatricule(long matricule) {
        this.matricule = matricule;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
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

    public List<Dossier> getDossiers() {
        return dossiers;
    }

    public void setDossiers(List<Dossier> dossiers) {
        this.dossiers = dossiers;
    }

    public String toString() {
        return "AGENT = matricle: " + this.matricule + " | CIN: " + this.CIN + " | nom et prenom: " + this.nom + " " + this.prenom;
    }
}

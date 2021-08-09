package entity;

import javax.persistence.*;

@Entity
public class Dossier {
    @Id
    public int ID;
    public String objet;
    @ManyToOne
    @JoinColumn(name = "ID_AGENT", referencedColumnName = "matricule")
    public Agent agent;
    @ManyToOne
    @JoinColumn(name = "ID_TRIBUNAL", referencedColumnName = "ID")
    public Tribunal tribunal;
    @ManyToOne
    @JoinColumn(name = "ID_AVOCAT_ADMIN", referencedColumnName = "ID")
    public AvocatAdmin avocatAdmin;
    @OneToOne(mappedBy = "dossier", cascade = CascadeType.ALL)
    public Jugement jugement;

    public Dossier() { }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public AvocatAdmin getAvocatAdmin() {
        return avocatAdmin;
    }

    public void setAvocatAdmin(AvocatAdmin avocatAdmin) {
        this.avocatAdmin = avocatAdmin;
    }

    public Jugement getJugement() {
        return jugement;
    }

    public void setJugement(Jugement jugement) {
        this.jugement = jugement;
    }

    public Tribunal getTribunal() {
        return tribunal;
    }

    public void setTribunal(Tribunal tribunal) {
        this.tribunal = tribunal;
    }
}

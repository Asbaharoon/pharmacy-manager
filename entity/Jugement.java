package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Jugement {
    @Id
    private int ID;
    @OneToOne
    @JoinColumn(name = "ID_DOSSIER", referencedColumnName = "ID")
    private Dossier dossier;
    private LocalDate date;
    private String jugement;
    private String remarque;
    private String situation;

    public Jugement() {

    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getJugement() {
        return jugement;
    }

    public void setJugement(String jugement) {
        this.jugement = jugement;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }
}

package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Avoir implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "vente_produit_id")
    private VenteProduit venteProduit;

    @Column(name = "motif")
    private String motif;

    @Column(name = "date_heuere")
    private LocalDateTime dateHeuere;

    @Column(name = "qte_rendue")
    private int qteRendue;

    public Avoir() {
    }

    public Avoir(VenteProduit venteProduit, String motif, LocalDateTime dateTime, int qteRendue) {
        this.venteProduit = venteProduit;
        this.motif = motif;
        this.dateHeuere = dateTime;
        this.qteRendue = qteRendue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VenteProduit getVenteProduit() {
        return venteProduit;
    }

    public void setVenteProduit(VenteProduit venteProduit) {
        this.venteProduit = venteProduit;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public LocalDateTime getDateHeuere() {
        return dateHeuere;
    }

    public void setDateHeuere(LocalDateTime dateHeuere) {
        this.dateHeuere = dateHeuere;
    }

    public int getQteRendue() {
        return qteRendue;
    }

    public void setQteRendue(int qteRendue) {
        this.qteRendue = qteRendue;
    }
}

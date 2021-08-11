package entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class Vente implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "dateHeuere")
    private LocalDateTime dateHeuere;

    @Column(name = "prixTotal")
    private float prixTotal;

    @Column(name = "etatpaiment")
    private byte etatPaiement;

    @Column(name = "restePaiement")
    private float restePaiement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idClient")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Client client;

    public Vente() {
    }

    public Vente(long id, LocalDateTime dateHeuere, float prixTotal, byte etatPaiement, float restePaiement, Client client) {
        this.id = id;
        this.dateHeuere = dateHeuere;
        this.prixTotal = prixTotal;
        this.etatPaiement = etatPaiement;
        this.restePaiement = restePaiement;
        this.client = client;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateHeuere() {
        return this.dateHeuere;
    }

    public void setDateHeuere(LocalDateTime dateHeuere) {
        this.dateHeuere = dateHeuere;
    }

    public float getPrixTotal() {
        return this.prixTotal;
    }

    public void setPrixtotam(float prixtotal) {
        this.prixTotal = prixtotal;
    }

    public byte getEtatPaiement() {
        return this.etatPaiement;
    }

    public void setEtatPaiement(byte etatPaiement) {
        this.etatPaiement = etatPaiement;
    }

    public float getRestePaiement() {
        return this.restePaiement;
    }

    public void setRestePaiement(float restePaiement) {
        this.restePaiement = restePaiement;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}

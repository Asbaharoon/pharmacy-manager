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
public class Commande implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "dateHeuere")
    private LocalDateTime dateHeuere;

    @Column(name = "prixTotal")
    private float prixTotal;

    @Column(name = "etatPaiment")
    private byte etatPaiment;

    @Column(name = "restePaiement")
    private float restePaiement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idFournisseur")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Fournisseur fournisseur;

    public Commande() {
    }

    public Commande(long id, LocalDateTime dateHeuere, int prixTotal, byte etatPaiment, float restePaiement, Fournisseur fournisseur) {
        this.id = id;
        this.dateHeuere = dateHeuere;
        this.prixTotal = prixTotal;
        this.etatPaiment = etatPaiment;
        this.restePaiement = restePaiement;
        this.fournisseur = fournisseur;
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

    public byte getEtatPaiment() {
        return this.etatPaiment;
    }

    public void setEtatPaiment(byte etatPaiment) {
        this.etatPaiment = etatPaiment;
    }

    public float getRestePaiement() {
        return this.restePaiement;
    }

    public void setRestePaiement(float restePaiement) {
        this.restePaiement = restePaiement;
    }

    public Fournisseur getFournisseur() {
        return this.fournisseur;
    }

    public void setFournisseur(Fournisseur idfournisseur) {
        this.fournisseur = idfournisseur;
    }


}

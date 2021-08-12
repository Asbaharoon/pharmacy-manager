package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Commande implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date_heuere")
    private LocalDateTime dateHeuere;

    @Column(name = "prix_total")
    private BigDecimal prixTotal;

    @Column(name = "etat_paiment")
    private byte etatPaiment;

    @Column(name = "reste_paiement")
    private BigDecimal restePaiement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fournisseur_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "commande")
    private Set<CommandeProduit> produitsCommandees;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

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

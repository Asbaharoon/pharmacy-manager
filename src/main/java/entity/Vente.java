package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Vente implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date_heuere")
    private LocalDateTime dateHeuere;

    @Column(name = "prix_total")
    private BigDecimal prixTotal;

    @Column(name = "etat_paiment")
    private byte etatPaiement;

    @Column(name = "reste_paiement")
    private BigDecimal restePaiement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Client client;

    @OneToMany(mappedBy = "vente")
    private Set<VenteProduit> produitsVendues;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

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

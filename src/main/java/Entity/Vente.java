package Entity;

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
public class Vente implements Serializable{
	
	@Id
	@Column(name="idvente")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
private long idvente;
	
	@Column(name="datevente")
	private LocalDateTime datevente;
	
	@Column(name="prixtotal")
	private float prixtotal;
	
	
	@Column(name="etatpaiment")
	private byte etatpaiement;
	
	@Column(name="restepaiement")
	private float restepaiement;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idcli")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Client client;
	
	
	public Vente() {
		
	}
	public Vente(long idvente,LocalDateTime datevente,float prixtotal,byte etatpaiement,float restepaiement,Client client) {
		this.idvente=idvente;
		this.datevente=datevente;
		this.prixtotal=prixtotal;
		this.etatpaiement=etatpaiement;
		this.restepaiement=restepaiement;
		this.client=client;
	}
	
	
	public Vente(long idvente) {
		this.idvente=idvente;
	}
	
	public long getIdvente() {
		return this.idvente;
	}
	public void setIdvente(long idvente) {
		this.idvente=idvente;
	}
	
	public LocalDateTime getDatevente() {
		return this.datevente;
	}
	
	public void setDatevente(LocalDateTime datevente ) {
		this.datevente=datevente;
	}
	
	public float getPrixtotal() {
		return this.prixtotal;
	}
	public void setPrixtotam(float prixtotal) {
		this.prixtotal=prixtotal;
	}
	
	
	public byte getEtatpaiement() {
		return this.etatpaiement;
	}
	public void setEtatpaiement(byte etatpaiement) {
		this.etatpaiement=etatpaiement;
	}
	
	public float getRestepaiement() {
		return this.restepaiement;
	}
	public void setRestepaiement(float restepaiement) {
		this.restepaiement=restepaiement;
	}
	
	public Client getClient() {
		return this.client;
	}
	public void setClient(Client client) {
		this.client=client;
	}

}

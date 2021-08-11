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
public class Commande implements Serializable{

	@Id
	@Column(name="idcmd")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
private long idcmd;
	
	@Column(name="dateheurecmd")
	private LocalDateTime dateheurecmd;
	
	@Column(name="prixtotal")
	private float prixtotal;
	
	@Column(name="etatpaiment")
	private byte etatpaiement;
	
	@Column(name="restepaiement")
	private float restepaiement;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idfournisseur")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Fournisseur idfournisseur;
	
	
	public Commande() {
		
	}
	public Commande(long idcmd,LocalDateTime dateheurecmd,int prixtotal,byte etatpaiement,float restepaiement,Fournisseur idfournisseur) {
		this.idcmd=idcmd;
		this.dateheurecmd=dateheurecmd;
		this.prixtotal=prixtotal;
		this.etatpaiement=etatpaiement;
		this.restepaiement=restepaiement;
		this.idfournisseur=idfournisseur;
	}
	
	
	public Commande(long idcmd) {
		this.idcmd=idcmd;
	}
	
	public long getIdcmd() {
		return this.idcmd;
	}
	public void setIdcmd(long idcmd) {
		this.idcmd=idcmd;
	}
	
	public LocalDateTime getDateheurecmd() {
		return this.dateheurecmd;
	}
	
	public void setDateheurecmd(LocalDateTime dateheurecmd ) {
		this.dateheurecmd=dateheurecmd;
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
	
	public Fournisseur getFournisseur() {
		return this.idfournisseur;
	}
	public void setFournisseur(Fournisseur idfournisseur) {
		this.idfournisseur=idfournisseur;
	}
	
	
	
}

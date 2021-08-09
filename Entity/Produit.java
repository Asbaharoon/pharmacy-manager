package Entity;

import java.io.Serializable;

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
public class Produit implements Serializable{
	@Id
	@Column(name="idprod")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
private long idprod;
	@Column(name="desig" ,unique=true)
	private String dsig;
	
	@Column(name="prachat")
	public float prachat;
	
	@Column(name="prvente")
	public float prvente;
	
	@Column(name="qtestock")
	public int qtestock;
	
	@Column(name="labo")
	private String labo;
	@Column(name="principeactif")
	public String principeactif;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idcategorie")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Categorie categorie;
	
	public Produit() {
		
	}
	public Produit(long idprod,String desig,float prachat,float prvente,int qtestock,String labo,String principeactif, Categorie categorie) {
		this.idprod=idprod;
		this.dsig=desig;
		this.prachat=prachat;
		this.prvente=prvente;
		this.qtestock=qtestock;
		this.labo=labo;
		this.principeactif=principeactif;
		this.categorie=categorie;
	}
	
	public Produit(long idprod) {
		this.idprod=idprod;
	}
	
	public long getIdprod() {
		return this.idprod;
	}
	public void setIdprod(long idprod) {
		this.idprod=idprod;
	}
	
	
	public String getDesig() {
		return this.dsig;
	}
	public void setDesig(String desig) {
		this.dsig=desig;
	}
	
	public float getPrachat() {
		return this.prachat;
	}
	public void setPrachat(float prachat) {
		this.prachat=prachat;
	}
	
	public float getPrvente() {
		return this.prvente;
	}
	public void setPrvente(float prvente) {
		this.prvente=prvente;
	}
	
	public int getQtestock() {
		return this.qtestock;
	}
	
	public void setQtestock(int qtestock) {
		this.qtestock=qtestock;
	}
	
	public String getLabo() {
		return this.labo;
	}
	public void setLabo(String labo) {
		this.labo=labo;
	}
	
	public String getPrincipeactif() {
		return this.principeactif;
	}
	public void setPrincipeactif(String principeactif) {
		this.principeactif=principeactif;
	}
	
	public Categorie getCategorie() {
		return this.categorie;
				
	}
	public void setCategorie(Categorie categorie) {
		this.categorie=categorie;
	}
	
}


package Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categorie implements Serializable{

	@Id
	@Column(name="idcategorie")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
private long idcategorie;
	
	@Column(name="nomcategorie")
	private String nomcategorie;
	
	public Categorie() {
		
	}
	
	public Categorie(long idcategorie,String nomcategorie) {
		this.idcategorie=idcategorie;
		this.nomcategorie=nomcategorie;
		
	}
	public Categorie(long idcategorie) {
		this.idcategorie=idcategorie;
	}
	
	public long getIdcategorie() {
		return this.idcategorie;
	}
	public void setIdcategorie(long idcategorie) {
		this.idcategorie=idcategorie;
	}
	
	
	public String getNomcategorie() {
		return this.nomcategorie;
	}
	public void setNomcategorie(String nomcategorie) {
		this.nomcategorie=nomcategorie;
	}
}

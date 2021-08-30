package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Categorie implements Serializable{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	@Column(name="nom")
	private String nom;

	@OneToMany(mappedBy = "categorie")
	private Set<Produit> produits;

	
	public Categorie() {
		
	}
	
	public Categorie(long id, String nom) {
		this.id = id;
		this.nom = nom;
	}
	
	public long getId() {
		return this.id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String toString() {
		return nom;
	}
}

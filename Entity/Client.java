package Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Client implements Serializable {
	@Id
	@Column(name="idcli")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idcli;
	
	@Column(name="nom")
	private String nom;
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="teleprt",columnDefinition="char(10)")
	private String teleprt;
	
	
       public Client() {
    	   
       }
       
       
       public Client(long idcli,String nom,String prenom,String teleprt) {
    	   this.idcli=idcli;
    	   this.nom=nom;
    	   this.prenom=prenom;
    	   this.teleprt=teleprt;
       }
       
       
       public Client(long idcli) {
    	   this.idcli=idcli;
       }
       
       
       public long getIdcli() {
    	   return this.idcli;
       }
       public void setIdcli(long idcli) {
    	   this.idcli=idcli;
       }
       
       
       public String getNom() {
    	   return this.nom;
       }
       public void setNom(String nom) {
    	   this.nom=nom;
       }
       
       
       public String getPrenom() {
    	   return this.prenom;
       }
       public void setPrenom(String prenom) {
    	   this.prenom=prenom;
       }
       
       
       public String getTeleprt() {
    	   return this.teleprt;
       }
       public void setTeleprt(String teleprt) {
    	   this.teleprt=teleprt;
       }
       
}

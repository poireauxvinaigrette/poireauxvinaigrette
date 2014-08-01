package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Resto extends Model {
	private static final long serialVersionUID = 4842563465068606079L;

	@Id
	@GeneratedValue
	public Long id;
	@Required
	public String raisonSociale;
	@Required
	public String mobile;
	public String mail;
	@Required
	public Double latitude;
	@Required
	public Double longitude;
	
	public String type;
	public String categorie;
	public String specialite;
	
	public String adresse;
	public String codePostale;
	public String commune;
	public String telephone;
	public String internet;
	
	public String classement;
	public String marque;
	public String tourisme;
	
	
	@Transient
	public Integer distance;
	
	
	public static Finder<Long, Resto> find = new Finder<Long, Resto>(Long.class, Resto.class);

}
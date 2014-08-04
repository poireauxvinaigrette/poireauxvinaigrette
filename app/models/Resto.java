package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	@Column(unique=true)
	public String mobile;
	public String mail;
	@Required
	public Double latitude;
	@Required
	public Double longitude;
	
	public String type;
	public String categorie;
	
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
	@Transient
	public String menudujour;

	public static Finder<Long, Resto> find = new Finder<Long, Resto>(Long.class, Resto.class);

}
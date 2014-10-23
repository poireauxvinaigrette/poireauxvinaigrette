package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.StringUtils;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Resto extends Model implements Comparable<Resto> {
	private static final long serialVersionUID = 4842563465068606079L;

	@Id
	public String id;
	
	@Required
	public String mobile;
	
	@Required
	public String raisonSociale;

	
	
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
	
	@OneToMany(mappedBy="resto")
	//@JoinColumn(name="resto", referencedColumnName = "mobile")
    public List<Menu> menus;


	public static Finder<Long, Resto> find = new Finder<Long, Resto>(Long.class, Resto.class);

	@Override
	public int compareTo(Resto o) {
		if (StringUtils.isEmpty(this.menudujour) && StringUtils.isEmpty(o.menudujour))
			return this.distance.compareTo(o.distance);
		else if (StringUtils.isEmpty(this.menudujour))
			return 1;
		else return -1;
	}

}
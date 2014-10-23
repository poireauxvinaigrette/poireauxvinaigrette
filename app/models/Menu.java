package models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import play.data.format.Formats;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Menu extends Model {

	private static final long serialVersionUID = -4628913172466111808L;
	@Id
	public String messageId;
	public Date creationDate;
	@Required
	public String destinataire;
	public String text;
	public String type;
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	public Date receptionDate;
	
	@Transient
	public String mobile;

	// This means every Sms has exactly one resto associated
	@ManyToOne
	@JoinColumn(name="resto", referencedColumnName="id", insertable=false, updatable=false, unique=true)
	@JsonIgnore
	public Resto resto;

	public static Finder<String, Menu> find = new Finder<String, Menu>(String.class, Menu.class);

}

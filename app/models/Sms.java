package models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.data.format.Formats;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Sms extends Model {

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

	// This means every Sms has exactly one resto associated
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "resto", referencedColumnName = "mobile")
	public Resto resto;

	public static Finder<String, Sms> find = new Finder<String, Sms>(String.class, Sms.class);

}

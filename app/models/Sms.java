package models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.data.format.*;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Sms extends Model {

	@Id
	public String messageId;
	public Date creationDate;
	@Required
	public String destinataire;
	public String text;
	public String type;
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	public Date timestamp;

	// This means every Sms has exactly one resto associated
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "resto", referencedColumnName = "mobile")
	public Resto resto;

	public static Finder<String, Sms> find = new Finder<String, Sms>(String.class, Sms.class);

}

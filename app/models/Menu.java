package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import play.data.format.Formats;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Menu extends Model {

	private static final long serialVersionUID = -4628913172466111808L;
	@Id
	@GeneratedValue
	public Integer id;
	public String messageId;
	public Date creationDate;
	@Required
	public String destinataire;
	public String text;
	@Column(name="type")
	public String typeMsg;
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	public Date receptionDate;
	
	@Transient
	public String mobile;

	// This means every Sms has exactly one resto associated
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="resto", nullable=false)
	@JsonIgnore
	public Resto resto;

	public static Finder<String, Menu> find = new Finder<String, Menu>(String.class, Menu.class);

}

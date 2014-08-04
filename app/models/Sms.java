package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Sms extends Model {

	@Id
	public String messageId;
	@Required
	public String msisdn;
	@Required
	public String to;
	public String text;
	public String type;
	public String timestamp;

}

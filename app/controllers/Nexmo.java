package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.PersistenceException;

import org.postgresql.util.PSQLException;

import play.mvc.Controller;
import play.mvc.Result;
import models.Resto;
import models.Sms;

import com.avaje.ebean.Ebean;

public class Nexmo extends Controller {

	public static Result index() {
		return ok("Got request " + request() + "!");
	}

	public static Result logSms(String msisdn, String to, String messageId, String text, String type) {
		Sms sms = new Sms();
		try {
			sms.resto = Resto.find.where().eq("mobile", msisdn).findUnique();

			if (sms.resto == null) {
				return internalServerError(msisdn + " mobile inconnu");
			}
			sms.destinataire = to;
			sms.messageId = messageId;
			sms.text = text;
			sms.type = type;
			sms.creationDate = new Date();

			String messagetimestamp = request().getQueryString("message-timestamp");
			sms.timestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(messagetimestamp);

			Ebean.save(sms);

		} catch (Exception e) {
			String message;
			if (e instanceof PersistenceException)
				message = e.getCause().getMessage();
			else
				message = e.getMessage();
			return internalServerError("Oops :  " + message);
		}
		return ok("Sms received :" + sms.messageId);
	}

}

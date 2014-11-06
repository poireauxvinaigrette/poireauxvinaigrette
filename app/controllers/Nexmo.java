package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.PersistenceException;

import models.Resto;
import models.Menu;
import play.mvc.Controller;
import play.mvc.Result;

import com.avaje.ebean.Ebean;

public class Nexmo extends Controller {

	public static Result index() {
		return ok("Got request " + request() + "!");
	}

	public static Result logMenu(String msisdn, String to, String messageId, String text, String type) {
		Menu menu = new Menu();
		try {
			menu.resto = Resto.find.where().eq("mobile", msisdn).findUnique();

			if (menu.resto == null) {
				return internalServerError(msisdn + " mobile inconnu");
			}
			menu.destinataire = to;
			menu.messageId = messageId;
			menu.text = text;
			menu.type = type;
			menu.creationDate = new Date();

			String messagetimestamp = request().getQueryString("message-timestamp");
			menu.receptionDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(messagetimestamp);

			Ebean.save(menu);

		} catch (Exception e) {
			String message;
			if (e instanceof PersistenceException)
				message = e.getCause().getMessage();
			else
				message = e.getMessage();
			return internalServerError("Oops :  " + message);
		}
		return ok("Sms received :" + menu.messageId);
	}

}

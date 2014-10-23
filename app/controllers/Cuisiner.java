package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.PersistenceException;

import models.Resto;
import models.Menu;
import play.i18n.Messages;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Cuisiner extends Controller {
	// private final static String feedUrl =
	// "http://beta.poireauxvinaigrette.com/restos/528/43.6036763/1.4426685?format=json";
	private final static String feedUrl2 = "http://localhost:9000/restos/528/43.6036763/1.4426685?format=json";

	public static Result index() {
		return ok("Got request " + request() + "!");
	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result cuisine() {
		Menu sms = null;
		try {
			JsonNode json = request().body().asJson();
			JsonParser parser = json.traverse();
			ObjectMapper mapper = new ObjectMapper();
			sms = mapper.readValue(parser, Menu.class);
			Resto restoAttached = Resto.find.where().eq("mobile",sms.mobile).findUnique();
			if (restoAttached == null) {
				throw new Exception(Messages.get("resto.inconnu", sms.resto.mobile));
			} else {
				sms.resto = restoAttached;
			}
			//Ebean.refresh(sms.resto);
			Ebean.save(sms);
			
		} catch (Exception e) {
			return badRequest(e.getMessage());
		}

		return ok(Json.toJson(sms));
	}

	public static Result setMenu() {
		// msisdn=06999213270&to=12108054321&messageId=000000FFFB0356D1&text=menu&type=text&message-timestamp=2012-08-19+20%3A38%3A23
		final String urlMenu = "http://localhost:9000/newMenu";
		ObjectNode result = Json.newObject();
		result.put("msisdn", "06999213270");
		result.put("message-timestamp", "2012-10-23");
		result.put("type", "text");
		result.put("text", "menu du jour");
		result.put("to", "12108054321");
		result.put("messageId", "000000FFFB0356D1");

//		Promise<WSResponse> promise = WS.url(urlMenu).post(result);
//		Promise<JsonNode> resultat = promise.map(response -> {
//			return response.asJson();
//		});
		/*
		 * .map( response -> { response.asJson();} );
		 */
		//return ok(resultat.get(1000));
		// promise.map(response -> ok(" tst : " + response.asJson()));
		
		return ok(Json.toJson(result));
	}

	public static Promise<Result> getRestos() {
		Promise<WSResponse> promise = WS.url(feedUrl2).get();
		return promise.map(response -> ok(" tst : " + response.asJson()));
	}

	public static Result cuisine(String msisdn, String to, String messageId, String text, String type) {
		Menu sms = new Menu();
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
			sms.receptionDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(messagetimestamp);

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

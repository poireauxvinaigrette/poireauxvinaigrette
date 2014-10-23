package controllers;

import org.w3c.dom.Document;

import play.Play;
import play.libs.ws.*;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;

public class Twilio extends Controller {


	/**
	 * Display the login page or dashboard if connected
	 *
	 * @return login page or dashboard
	 */
	public static Result index() throws TwiMLException {

		// Create a TwiML response and add our friendly message.
		TwiMLResponse twiml = new TwiMLResponse();

		Say say = new Say("Hello Monkey!");

		twiml.append(say);

		return ok(twiml.toXML());
	}



}
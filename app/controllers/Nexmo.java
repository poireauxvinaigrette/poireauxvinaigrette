package controllers;

import play.mvc.Controller;
import play.mvc.Result;
//import models.Sms; 
//import com.avaje.ebean.Ebean;

class Nexmo extends Controller {

public static Result index() {
return ok("Got request " + request() + "!");
}

/*
public static Result logSms(String msisdn, String to , String messageId, String text, String type, String messagetimestamp) {
	Sms sms = new Sms();
	sms.msisdn = msisdn;
	sms.to = to;
	sms.messageId = messageId;
	sms.text = text;
	sms.type = type;
	sms.timestamp = messagetimestamp;
	Ebean.save(sms);
  return ok();
}
*/

}

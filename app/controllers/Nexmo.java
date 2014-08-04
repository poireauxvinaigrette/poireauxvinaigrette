package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Nexmo extends Controller {

    /**
     * Display the login page or dashboard if connected
     *
     * @return login page or dashboard
     */
    public static Result index() throws TwiMLException{
	return ok("Got request " + request() + "!");
    }

}

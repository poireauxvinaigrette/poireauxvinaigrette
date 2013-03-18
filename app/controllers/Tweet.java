package controllers;
import play.Logger;
import play.*;
import play.mvc.*;
import play.data.*;

import models.Tweety;

import views.html.*;

public class Tweet extends Controller {

    final static Form<Tweety> tweetForm = form(Tweety.class);



    /**
     * Display a blank form.
     */
    public static Result blank() {
        return ok(index.render(tweetForm));
    }

    public static Result submit() {
        Form<Tweety> filledForm = tweetForm.bindFromRequest();
	
	
	if(filledForm.hasErrors()) {
	    return badRequest(index.render(filledForm));
	}
  String username = filledForm.get().username;
 
Logger.debug("Log this "+username);     
 	return ok(index.render(filledForm));
    }
}


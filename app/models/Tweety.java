package models;

import javax.validation.*;

import play.data.validation.Constraints.*;

public class Tweety {

    @Required
    @Pattern(value = "@([A-Za-z0-9_]+)", message = "error.twitterAccount")
    public String username;

    @Required
    public Boolean jaimePV;

    public Tweety() {}

    public Tweety(String username, Boolean jaimePV) {
        this.username = username;
	this.jaimePV = jaimePV;
    }
}

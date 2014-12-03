import play.*;
import play.libs.*;

import java.util.*;

import com.avaje.ebean.*;

import models.*;

import play.GlobalSettings;
import play.Logger;
import securesocial.core.RuntimeEnvironment;
import service.MyEnvironment;

import java.util.HashMap;

public class Global extends GlobalSettings {
	private RuntimeEnvironment env = new MyEnvironment();
	private HashMap<String, Object> instances = new HashMap<>();

	@Override
	public <A> A getControllerInstance(Class<A> controllerClass) throws Exception {
		A result = (A) instances.get(controllerClass.getName());
		Logger.debug("result = " + result);
		if (result == null) {
			Logger.debug("creating controller: " + controllerClass.getName());
			try {
				result = controllerClass.getDeclaredConstructor(RuntimeEnvironment.class).newInstance(env);
			} catch (NoSuchMethodException e) {
				// the controller does not receive a RuntimeEnvironment,
				// delegate creation to base class.
				result = super.getControllerInstance(controllerClass);
			}
			instances.put(controllerClass.getName(), result);
		}
		return result;
	}

	public void onStart(Application app) {
		// InitialData.insert(app);
	}

	static class InitialData {

		public static void insert(Application app) {
			if (Ebean.find(Resto.class).findRowCount() == 0) {

				Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml.load("initial-data.yml");

				// Insert users first
				Ebean.save(all.get("restaurants"));

			}
		}

	}

}
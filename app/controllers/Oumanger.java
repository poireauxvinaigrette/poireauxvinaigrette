package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import models.GeoRestos;
import models.Resto;
import models.Sms;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.restos.geoloc;
import views.html.restos.map;
import views.html.restos.restos;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;

public class Oumanger extends Controller {

	public static Result index() {
		
		return ok(geoloc.render());
	}

	public static Result map() {
		return ok(map.render());
	}

	public static Result list(Integer dist, Double lat, Double lon, String format) {
		String distance = "(POINT(" + lat + "," + lon + ")<->POINT(latitude, longitude))";
		Date today = new Date();
		List<Resto> listResto = new ArrayList<Resto>();
		String sql = "select "
				+ distance
				+ " as distance, r.id , r.raison_sociale, r.raison_sociale, r.categorie, r.telephone, r.mobile, r.adresse"
				+ ", r.code_postale, r.commune, r.latitude, r.longitude, r.internet" + " from resto r where "
				+ distance + "*6367445*pi()/180<" + dist + " order by " + distance + " limit 200";
		// + " from resto r left join sms s on r.mobile = s.resto "

		List<SqlRow> items = Ebean.createSqlQuery(sql).findList();
		for (SqlRow sqlRow : items) {
			Resto resto = new Resto();
			resto.id = sqlRow.getLong("id");
			resto.raisonSociale = sqlRow.getString("raison_sociale");
			resto.categorie = sqlRow.getString("categorie");
			resto.telephone = sqlRow.getString("telephone");
			resto.mobile = sqlRow.getString("mobile");
			resto.adresse = sqlRow.getString("adresse");
			resto.codePostale = sqlRow.getString("code_postale");
			resto.commune = sqlRow.getString("commune");
			resto.latitude = sqlRow.getDouble("latitude");
			resto.longitude = sqlRow.getDouble("longitude");
			resto.internet = sqlRow.getString("internet");

			Double tmp = sqlRow.getDouble("distance") * 6367445 * Math.PI / 180;
			resto.distance = tmp.intValue();
			if (StringUtils.isNotEmpty(resto.mobile)) {
				
				List<Sms> findList = Sms.find.where().eq("resto.mobile", resto.mobile)
						.orderBy("creationDate desc").findList();
				List<Sms> filteredSms = new ArrayList<Sms>();
				for (Sms sms : findList) {
					Logger.debug(today + "check sms :"+ sms.messageId + " " + sms.receptionDate);
					if ( DateUtils.isSameDay(sms.receptionDate, today)) {
						Logger.debug("add sms :"+ sms.messageId + " " + sms.text);
						filteredSms.add(sms);
					}
				}
// java8	List<Sms> filteredSms = findList.stream().filter(u -> DateUtils.isSameDay(u.receptionDate, today)).collect(Collectors.toList());
				
				resto.menudujour  = (filteredSms.size() > 0 ? filteredSms.get(0).text : null);
			}
			listResto.add(resto);
		}

		Collections.sort(listResto);
		//Collections.reverse(restos);
		

		if (format == null) {
			return ok(restos.render(listResto));
		} else if (format.equals("geojson")) {
			GeoRestos georestos = GeoRestos.parse(listResto);
			return ok(Json.toJson(georestos));
		} else if (format.equals("json")) {
			return ok(Json.toJson(listResto));
		} else {
			return notFound();
		}

	}
}

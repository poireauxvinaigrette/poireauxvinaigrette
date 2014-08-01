package controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;

import models.GeoRestos;
import models.Resto;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.map;

public class Oumanger extends Controller {

	public static Result map() {
		return ok(map.render("Your new application is ready."));
	}

	public static Result list(Integer dist, Double lat, Double lon, String format) {
		String distance = "(POINT(" + lat + "," + lon + ")<->POINT(latitude, longitude))";
		// List<Resto> restos = Resto.find.select("distance,id,raisonSociale")
		// .where(distance + "*6367445*pi()/180<" +
		// dist).orderBy(distance).findList();

		List<Resto> restos = new ArrayList<Resto>();

		String sql = "select "
				+ distance
				+ " as distance, r.id , r.raison_sociale, r.raison_sociale, r.categorie, r.telephone, r.mobile, r.adresse"
				+ ", r.code_postale, r.commune, r.latitude, r.longitude, r.internet"
				+ " from resto r where " + distance + "*6367445*pi()/180<" + dist + " order by " + distance;

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

			restos.add(resto);
		}

		GeoRestos georestos = GeoRestos.parse(restos);

		if (format == null) {
			return ok(index.render(restos));
		} else if (format.equals("geojson")) {
			return ok(Json.toJson(georestos));
		} else {
			return notFound();
		}

	}
}

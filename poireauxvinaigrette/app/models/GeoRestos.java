package models;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.GeoRestos.GeoResto.GeoPoint;

public class GeoRestos {

	public String type = "FeatureCollection";
	public List<GeoResto> features = new ArrayList<GeoResto>();

	public class GeoResto {
		public GeoResto() {
		}

		public String type = "Feature";
		public GeoPoint geometry;
		public Map<String, String> properties;

		public class GeoPoint {
			public GeoPoint(Double latitude, Double longitude) {
				this.coordinates = new ArrayList<Double>();
				this.coordinates.add(longitude);
				this.coordinates.add(latitude);
				// = "[" + longitude + "," + latitude + "]";
			}

			public String type = "Point";
			public List<Double> coordinates;

		}
	}

	static public GeoRestos parse(List<Resto> restos) {
		GeoRestos georestos = new GeoRestos();
		try {

			for (Resto resto : restos) {
				GeoResto geoResto = georestos.new GeoResto();
				geoResto.properties = new HashMap<String, String>();

				Field[] champs = Resto.class.getFields();
				for (Field field : champs) {
					String name = field.getName();
					if (field.get(resto) != null)
						geoResto.properties.put(name, field.get(resto).toString());
				}
				/*
				 * "title": "Virginia earthquake 2011 - mag. 5.8",
				 * "description":
				 * "No deaths, minor injuries. Damage to buildings reported.",
				 * "marker-symbol": "star", "marker-size": "medium",
				 * "marker-color": "#f44"
				 */
				String title;
				if (resto.internet != null) {
					title = "<a target='_blank' class='popup' href='http://" + resto.internet + "'>" + resto.raisonSociale + "</a>";
				} else {
					title = resto.raisonSociale;
				}
				geoResto.properties.put("title", title);

				String adresse = resto.adresse + "<br/>" + resto.codePostale + " " + resto.commune + "<br/>"
						+ resto.telephone;

				geoResto.properties.put("description", resto.categorie + "<br/>" + adresse);
				geoResto.properties.put("marker-symbol", "star");
				geoResto.properties.put("marker-color", "#f44");

				geoResto.geometry = geoResto.new GeoPoint(resto.latitude, resto.longitude);
				georestos.features.add(geoResto);
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return georestos;
	}

}

/*
 * { "type": "Feature", "geometry": {"type": "Point", "coordinates": [102.0,
 * 0.5]}, "properties": {"prop0": "value0"} },
 */
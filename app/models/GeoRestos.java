package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

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

				// Field[] champs = Resto.class.getFields();
				// for (Field field : champs) {
				// String name = field.getName();
				// if (field.get(resto) != null)
				// geoResto.properties.put(name, field.get(resto).toString());
				// }
				//
				String rs;
				if (resto.internet != null) {
					rs = "<a target='_blank' class='popup' href='" + resto.internet + "'>" + resto.raisonSociale
							+ "</a>";
				} else {
					rs = resto.raisonSociale;
				}
				String datedujour = "";
				if (resto.datedujour!=null)  {
					datedujour=resto.datedujour + "<br/>";
				}

				geoResto.properties.put("title", datedujour 
					+  StringUtils.defaultString(resto.menudujour));

				String adresse = "";
				if (!StringUtils.isEmpty(resto.adresse))
					adresse = resto.adresse;
				if (!StringUtils.isEmpty(resto.codePostale))
					adresse += "<br/>" + resto.codePostale;
				if (!StringUtils.isEmpty(resto.commune))
					adresse += " " + resto.commune;
				if (!StringUtils.isEmpty(resto.telephone))
					adresse += "<br/>" + resto.telephone;

				geoResto.properties.put("description", "à " + resto.distance + "m : " + rs + "<br/>" + adresse);
				
				if (resto.menudujour != null) {
					if ("foodtruck".equals(resto.type)) {
						geoResto.properties.put("marker-symbol", "bus");
					} else {
					geoResto.properties.put("marker-symbol", "restaurant");
					}
					geoResto.properties.put("marker-color", "#f44");
					geoResto.properties.put("marker-allow-overlap", "false");
					geoResto.properties.put("marker-size", "large");

				} else {
					if ("foodtruck".equals(resto.type)) {
                                                geoResto.properties.put("marker-symbol", "bus");
                                        } else {
					geoResto.properties.put("marker-symbol", "bar");
                                        }
 
					geoResto.properties.put("marker-color", "#fff");
					geoResto.properties.put("marker-size", "small");
					geoResto.properties.put("marker-allow-overlap", "true");
				}
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

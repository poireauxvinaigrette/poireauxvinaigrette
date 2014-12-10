package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.LocalDate;

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
			Calendar c = Calendar.getInstance();
			Date today = c.getTime();
			Date yesterday = DateUtils.addDays(today, -1);

			for (Resto resto : restos) {
				GeoResto geoResto = georestos.new GeoResto();
				geoResto.properties = new HashMap<String, String>();
				geoResto.properties.put("uri", "/resto/" + resto.id);
				geoResto.properties.put("raisonSociale", resto.raisonSociale);
				geoResto.properties.put("distance", resto.distance + "m");

				if (resto.menudujour != null || resto.mobile != null) {

					if (resto.menudujour != null) {
						geoResto.properties.put("menudujour", StringUtils.defaultString(resto.menudujour));
					}

					if (resto.beforeMidi != null) {				
						geoResto.properties.put("marker-size", "large");
						geoResto.properties.put("marker-color", "#009933");
						if (resto.beforeMidi.equals(Boolean.TRUE)) {
							geoResto.properties.put("dujour", " Menu du midi");
						} else {
							geoResto.properties.put("dujour", " Menu du soir");
						}
					} else if (resto.datedujour != null) {
						geoResto.properties.put("marker-color", "#99FF99");
						if (DateUtils.isSameDay(resto.datedujour, yesterday)) {
							geoResto.properties.put("dujour", " Menu d'hier");
						} else {
							geoResto.properties.put("dujour", "Menu du " + DateFormatUtils.format(resto.datedujour, "yyyy-MM-dd"));
						}
					} else if (resto.mobile != null) {
						geoResto.properties.put("dujour", " Aucun menu.");
						geoResto.properties.put("marker-color", "#99FF99");
					}

					if ("foodtruck".equals(resto.typeDeResto)) {
						geoResto.properties.put("marker-symbol", "bus");
					} else {
						geoResto.properties.put("marker-symbol", "restaurant");
					}
					if (resto.menudujour != null) {
						geoResto.properties.put("marker-fill", "#03300");
						geoResto.properties.put("marker-allow-overlap", "true");

					} else {
						
						geoResto.properties.put("marker-allow-overlap", "false");
						geoResto.properties.put("marker-size", "medium");
					}
				} else {
					if ("foodtruck".equals(resto.typeDeResto)) {
						geoResto.properties.put("marker-symbol", "bus");
					} else {
						geoResto.properties.put("marker-symbol", "bar");
					}
					geoResto.properties.put("marker-color", "#fff");
					geoResto.properties.put("marker-size", "small");
					geoResto.properties.put("marker-allow-overlap", "false");
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

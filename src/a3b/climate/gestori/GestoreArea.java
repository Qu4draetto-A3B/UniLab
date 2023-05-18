/*
 * Interdisciplinary Workshop A
 * Climate Monitoring
 * A.A. 2022-2023
 *
 * Authors:
 * - Iuri Antico, 753144
 * - Beatrice Balzarini, 752257
 * - Michael Bernasconi, 752259
 * - Gabriele Borgia, 753262
 *
 * Some rights reserved.
 * See LICENSE file for additional information.
 */
package a3b.climate.gestori;

import org.apache.commons.csv.CSVRecord;

import a3b.climate.magazzeno.AreaGeografica;
import a3b.climate.magazzeno.ListaAree;
import a3b.climate.utils.CercaAree;
import a3b.climate.utils.DataTable;
import a3b.climate.utils.result.*;

/**
 * Gestisce le operazioni di lettura e scrittura riguardanti oggetti di tipo AreaGeografica
 */
public class GestoreArea extends Gestore implements CercaAree {
	/**
	 * Costruttore di un'istanza di GestoreArea
	 */
	public GestoreArea() {
		super(
				"./data/CoordinateMonitoraggio.CSV",
				new String[] { "GeonameID", "Name", "ASCIIName", "CountryCode", "CountryName", "Lat", "Lon" });
	}
	/**
	 * Metodo che ricerca una determinata area geografica in base al suo ID
	 * @param geoId ID relativo all'area geografica d'interesse
	 * @return Area geografica corrispondente all'ID fornito come parametro
	 */
	public Result<AreaGeografica> getArea(long geoId) {
		for (CSVRecord record : records) {
			long dbGeoId = Long.parseLong(record.get("GeonameID"));
			if (dbGeoId == geoId) {

				return new Result<AreaGeografica>((AreaGeografica)buildObject(record));
			}
		}

		return new Result<>(1, "Area non trovata");
	}

	@Override
	public ListaAree cercaAreaGeografica(String denom, String stato) {
		ListaAree lag = new ListaAree();
		AreaGeografica ag;

		if (denom == null) {
			denom = "";
		}

		if (stato == null) {
			stato = "";
		}

		for (CSVRecord record : records) {
			ag = new AreaGeografica(
					Long.parseLong(record.get("GeonameID")),
					Double.parseDouble(record.get("Lat")),
					Double.parseDouble(record.get("Lon")),
					record.get("CountryName"),
					record.get("Name"));

			if (ag.getDenominazione().toLowerCase().contains(denom.toLowerCase())
					&& ag.getStato().toLowerCase().contains(stato.toLowerCase())) {
				lag.addFirst(ag);
			}
		}

		return lag;
	}

	@Override
	public Result<AreaGeografica> cercaAreeGeografiche(double latitudine, double longitudine) {
		if ((latitudine < -90) || (latitudine > 90)) {
			return new Result<>(1, "hai inserito valori errati riprova");
		}
		if ((longitudine < -180) || (longitudine > 180)) {
			return new Result<>(1, "hai inserito valori errati riprova");
		}

		// Cerca coordinate esatte
		for (CSVRecord record : records) {
			if ((latitudine == Double.parseDouble(record.get("Lat"))) && (longitudine == Double.parseDouble(record.get("Lon"))))
				return new Result<AreaGeografica>((AreaGeografica)buildObject(record));
		}

		// Cerca coordinate piu' vicine
		CSVRecord r = records.iterator().next();
		CSVRecord target = r;
		AreaGeografica ag = (AreaGeografica)buildObject(r);

		double differenzalat = latitudine - ag.getLatitudine();
		differenzalat *= differenzalat;

		double differenzalong = longitudine - ag.getLongitudine();
		differenzalong *= differenzalong;

		double min = Math.sqrt(differenzalat + differenzalong);

		for (CSVRecord record : records) {

			differenzalat = latitudine - Double.parseDouble(record.get("Lat"));
			differenzalat *= differenzalat;

			differenzalong = longitudine - Double.parseDouble(record.get("Lon"));
			differenzalong *= differenzalong;

			double dist = Math.sqrt(differenzalat + differenzalong);

			if (min > dist) {
				min = dist;
				target = record;
			}

		}

		return new Result<AreaGeografica>((AreaGeografica)buildObject(target));
	}

	@Override
	protected DataTable buildObject(CSVRecord record){
		AreaGeografica ag = new AreaGeografica(
					Long.parseLong(record.get("GeonameID")),
					Double.parseDouble(record.get("Lat")),
					Double.parseDouble(record.get("Lon")),
					record.get("CountryName"),
					record.get("ASCIIName"));

				return ag;
	}
}

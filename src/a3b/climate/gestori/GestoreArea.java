package a3b.climate.gestori;

import org.apache.commons.csv.CSVRecord;

import a3b.climate.magazzeno.AreaGeografica;
import a3b.climate.magazzeno.ListaAree;
import a3b.climate.utils.CercaAree;
import a3b.climate.utils.DataTable;
import a3b.climate.utils.result.*;

public class GestoreArea extends Gestore implements CercaAree {
	public GestoreArea() {
		super(
				"./data/CoordinateMonitoraggio.CSV",
				new String[] { "GeonameID", "Name", "ASCIIName", "CountryCode", "CountryName", "Lat", "Lon" });
	}

	public Result<AreaGeografica> getArea(long geoId) {
		for (CSVRecord record : records) {
			long dbGeoId = Long.parseLong(record.get("GeonameID"));
			if (dbGeoId == geoId) {
				AreaGeografica ag = new AreaGeografica(
					dbGeoId,
					Double.parseDouble(record.get("Lat")),
					Double.parseDouble(record.get("Lon")),
					record.get("CountryName"),
					record.get("ASCIIName"));

				return new Result<AreaGeografica>(ag);
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
			AreaGeografica areaGeografica = new AreaGeografica(
					Long.parseLong(record.get("GeonameID")),
					Double.parseDouble(record.get("Lat")),
					Double.parseDouble(record.get("Lon")),
					record.get("CountryName"),
					record.get("Name"));

			if ((latitudine == areaGeografica.getLatitudine()) && (longitudine == areaGeografica.getLongitudine()))
				return new Result<>(areaGeografica);
		}

		// Cerca coordinate piu' vicine
		CSVRecord r = records.iterator().next();
		AreaGeografica ag = new AreaGeografica(
				Long.parseLong(r.get("GeonameID")),
				Double.parseDouble(r.get("Lat")),
				Double.parseDouble(r.get("Lon")),
				r.get("CountryName"),
				r.get("Name"));

		double differenzalat = latitudine - ag.getLatitudine();
		differenzalat *= differenzalat;

		double differenzalong = longitudine - ag.getLongitudine();
		differenzalong *= differenzalong;

		double min = Math.sqrt(differenzalat + differenzalong);
		for (CSVRecord record : records) {
			AreaGeografica areaGeografica = new AreaGeografica(
					Long.parseLong(r.get("GeonameID")),
					Double.parseDouble(record.get("Lat")),
					Double.parseDouble(record.get("Lon")),
					record.get("CountryName"),
					record.get("Name"));

			differenzalat = latitudine - areaGeografica.getLatitudine();
			differenzalat *= differenzalat;

			differenzalong = longitudine - areaGeografica.getLongitudine();
			differenzalong *= differenzalong;

			double dist = Math.sqrt(differenzalat + differenzalong);

			if (min > dist) {
				min = dist;
				ag = areaGeografica;
			}

		}

		return new Result<>(ag);
	}

	@Override
	protected DataTable buildObject(CSVRecord record) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'buildObject'");
	}

}

package a3b.climate.gestori;

import java.io.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import a3b.climate.magazzeno.AreaGeografica;
import a3b.climate.magazzeno.ListaAree;
import a3b.climate.utils.result.*;

public class GestoreArea {
	private static final String file = "./data/CoordinateMonitoraggio.CSV";
	private static final String[] HEADERS = { "GeonameID", "Name", "ASCIIName", "CountryCode", "CountryName", "Lat",
			"Lon" };

	private static CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
			.setHeader(HEADERS)
			.setSkipHeaderRecord(true)
			.build();

	private static Reader in;
	private static Iterable<CSVRecord> records;

	public static ListaAree cercaAreaGeografica(String denom, String stato) {
		try {
			in = new FileReader(file);
			records = csvFormat.parse(in);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Panic("File aree non trovato o non leggibile, non posso continuare");
		}

		ListaAree lag = new ListaAree();
		AreaGeografica ag;

		if (denom == null) {
			denom = "";
		}

		if (stato == null) {
			stato = "";
		}

		for (CSVRecord record : records) {
			ag = new AreaGeografica(Double.parseDouble(record.get("Lat")), Double.parseDouble(record.get("Lon")),
					record.get("CountryName"), record.get("Name"));

			if (ag.getDenominazione().toLowerCase().contains(denom.toLowerCase())
					&& ag.getStato().toLowerCase().contains(stato.toLowerCase())) {
				lag.addFirst(ag);
			}
		}

		return lag;
	}

	public static Result<AreaGeografica> cercaAreeGeografiche(double latitudine, double longitudine) {
		try {
			in = new FileReader("./data/CoordinateMonitoraggio.csv");
			records = csvFormat.parse(in);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Panic("File aree non trovato o non leggibile, non posso continuare");
		}

		if ((latitudine < -90) || (latitudine > 90)) {
			return new Result<>(1, "hai inserito valori errati riprova");
		}
		if ((longitudine < -180) || (longitudine > 180)) {
			return new Result<>(1, "hai inserito valori errati riprova");
		}

		// Cerca coordinate esatte
		for (CSVRecord record : records) {
			AreaGeografica areaGeografica = new AreaGeografica(Double.parseDouble(record.get("Lat")),
					Double.parseDouble(record.get("Lon")),
					record.get("CountryName"), record.get("Name"));

			if ((latitudine == areaGeografica.getLatitudine()) && (longitudine == areaGeografica.getLongitudine()))
				return new Result<>(areaGeografica);
		}

		// Cerca coordinate piu' vicine
		CSVRecord r = records.iterator().next();
		AreaGeografica ag = new AreaGeografica(Double.parseDouble(r.get("Lat")),
				Double.parseDouble(r.get("Lon")),
				r.get("CountryName"), r.get("Name"));

		double differenzalat = latitudine - ag.getLatitudine();
		differenzalat *= differenzalat;

		double differenzalong = longitudine - ag.getLongitudine();
		differenzalong *= differenzalong;

		double min = Math.sqrt(differenzalat + differenzalong);
		for (CSVRecord record : records) {
			AreaGeografica areaGeografica = new AreaGeografica(Double.parseDouble(record.get("Lat")),
					Double.parseDouble(record.get("Lon")),
					record.get("CountryName"), record.get("Name"));

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

}

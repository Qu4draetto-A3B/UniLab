package a3b.climate.gestori;

import java.io.FileReader;
import java.io.Reader;
import java.io.Writer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Indirizzo;
import a3b.climate.magazzeno.ListaAree;
import a3b.climate.utils.result.Panic;
import a3b.climate.utils.result.Result;

public class GestoreCentro {
	private static String file = "./data/CentriMonitoraggio.CSV";
	private final static String[] HEADERS = {"Name", "Address", "Areas"};
	private static CSVFormat format = CSVFormat.DEFAULT.builder()
			.setHeader(HEADERS)
			.setSkipHeaderRecord(true)
			.build();

	private static Reader in;
	private static Writer out;
	private static Iterable<CSVRecord> records;

	public static boolean registraCentro(CentroMonitoraggio cm) {
		return true;
	}

	public static Result<CentroMonitoraggio> getCentro(String nome) {
		try {
			in = new FileReader(file);
			records = format.parse(in);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Panic("File centri non trovato o non leggibile, non posso continuare");
		}

		CSVRecord target = null;

		for (CSVRecord record : records) {
			if (record.get("Name").toLowerCase().equals(nome.toLowerCase())) {
				target = record;
				break;
			}
		}

		if (target == null) {
			return new Result<>(1, "Centro non trovato");
		}

		String nomo = target.get("Name");
		String[] indArray = target.get("Address").split("|");
		Indirizzo ind = new Indirizzo(indArray[0], Integer.parseInt(indArray[1]), Integer.parseInt(indArray[2]), indArray[3], indArray[4]);
		ListaAree lag = new ListaAree();

		String[] coordArr = target.get("Areas").split(";");

		for (String coord : coordArr) {
			String[] ll = coord.split("|");
			double lat = Double.parseDouble(ll[0]);
			double lon = Double.parseDouble(ll[1]);

			lag.addFirst(GestoreArea.cercaAreeGeografiche(lat, lon).get());
		}

		CentroMonitoraggio cm = new CentroMonitoraggio(nomo, ind, lag);

		return new Result<>(cm);
	}
}

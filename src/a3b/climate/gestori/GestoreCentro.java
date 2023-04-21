package a3b.climate.gestori;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Indirizzo;
import a3b.climate.magazzeno.ListaAree;
import a3b.climate.utils.StatoGestore;
import a3b.climate.utils.result.Panic;
import a3b.climate.utils.result.Result;

import a3b.climate.utils.result.*;

public class GestoreCentro {
	private static StatoGestore stato = StatoGestore.FERMATO;
	private final static String file = "./data/CentriMonitoraggio.CSV";
	private final static String[] HEADERS = { "Name", "Address", "Areas" };
	private static CSVFormat format = CSVFormat.DEFAULT.builder()
			.setHeader(HEADERS)
			.setSkipHeaderRecord(true)
			.build();

	private static Reader in;
	private static Writer out;
	private static List<CSVRecord> records;

	private static CSVPrinter p;

	public static boolean registraCentro(CentroMonitoraggio cm) {
		return true;
	}

	public static Result<CentroMonitoraggio> getCentro(String nome) {
		try {
			in = new FileReader(file);
			records = format.parse(in).getRecords();
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
		Indirizzo ind = new Indirizzo(indArray[0], Integer.parseInt(indArray[1]), Integer.parseInt(indArray[2]),
				indArray[3], indArray[4]);
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

	public static boolean start() {
		try {
			in = new FileReader(file);
			out = new FileWriter(file,true);
			records = format.parse(in).getRecords();
			p = new CSVPrinter(out, format);
		} catch (Exception e) {
			e.printStackTrace();
			stato = StatoGestore.ERRORE;
			return false;
		}

		stato = StatoGestore.AVVIATO;
		return true;
	}

	public static boolean stop() {
		try {
			p.close();
			out.close();
			in.close();
			records.clear();
		} catch (Exception e) {
			e.printStackTrace();
			stato = StatoGestore.ERRORE;
			return false;
		}

		stato = StatoGestore.FERMATO;
		return true;
	}


	public static boolean addCentro(CentroMonitoraggio cm) {
		start();
		try {
			p.printRecord(cm.getNome(), cm.getIndirizzo().toCsv(), cm.getListaAree().toCsv());
			p.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		stop();
		return true;
	}
}

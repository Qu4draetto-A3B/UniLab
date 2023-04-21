package a3b.climate.gestori;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import a3b.climate.magazzeno.Misurazione;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.StatoGestore;
import a3b.climate.utils.result.*;

public class GestoreOperatore {
	private static StatoGestore stato = StatoGestore.FERMATO;
	private static final String file = "./data/OperatoriRegistrati.CSV";

	private final static String[] HEADERS = { "CodFis", "UserID", "Nome", "Cognome", "Email", "Centro", "Password" };
	private static CSVFormat format = CSVFormat.DEFAULT.builder()
			.setHeader(HEADERS)
			.setSkipHeaderRecord(true)
			.build();

	private static Reader in;
	private static Writer out;

	private static List<CSVRecord> records;
	private static CSVPrinter p;

	public static StatoGestore getStato() {
		return stato;
	}

	public static boolean start() {
		try {
			in = new FileReader(file);
			out = new FileWriter(file);
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

	public static boolean registrazione(Operatore op, String pwd) {
		try {
			p.printRecord(op.toCsv(), pwd);
			p.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public Result<Operatore> login(String uid, String pwdhash) {
		for (CSVRecord record : records) {
			System.out.println(record.toString());
			String dbUid = record.get("UserID");
			String dbPwd = record.get("Password");

			if (uid == dbUid && pwdhash == dbPwd) {
				Operatore op = new Operatore(record.get("CodFis"),
						dbUid,
						record.get("Nome"),
						record.get("Cognome"),
						record.get("Email"),
						null);

				return new Result<>(op);
			}
		}

		return new Result<>(1, "Operatore non trovato");
	}

	public static boolean addMisurazione(Misurazione mis) {
		// TODO
		return false;
	}

	public static List<Misurazione> getMisurazioni() {
		// TODO
		return null;
	}
}

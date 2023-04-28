package a3b.climate.gestori;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import a3b.climate.magazzeno.CentroMonitoraggio;
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
			out = new FileWriter(file, true);
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
			p.close(true);
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

	public static Result<Object> registrazione(Operatore op, String pwd) {
		String cf = op.getCf().toUpperCase();

		if (getOperatore(cf).isValid()) {
			return new Result<>(1, "Operatore gia registrato");
		}

		Pattern cfPattern = Pattern.compile(
				"(?:[A-Z][AEIOU][AEIOUX]|[AEIOU]X{2}|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$");

		if (!cfPattern.matcher(cf).matches()) {
			return new Result<>(2, "Codice fiscale irregolare");
		}

		try {
			p.printRecord(cf, op.getUid(), op.getNome(), op.getCognome(), op.getEmail(),
					op.getCentro().getNome(), pwd);
			p.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(3, "Errore nella scrittura del record");
		}

		return new Result<>(new Object());
	}

	public static Result<Operatore> login(String uid, String pwdhash) {
		for (CSVRecord record : records) {
			System.out.println(record.toString());
			String dbUid = record.get("UserID");
			String dbPwd = record.get("Password");
			String dbCf = record.get("CodFis");

			if (uid == dbUid && pwdhash == dbPwd) {
				return new Result<>(getOperatore(dbCf).get());
			}
		}

		return new Result<>(1, "Operatore non trovato");
	}

	private static Result<Operatore> getOperatore(String cf) {
		for (CSVRecord record : records) {
			String dbCf = record.get("CodFis");

			if (cf == dbCf) {
				CentroMonitoraggio cm = GestoreCentro.getCentro(record.get("Centro")).get();

				Operatore op = new Operatore(record.get("CodFis"),
						record.get("UserID"),
						record.get("Nome"),
						record.get("Cognome"),
						record.get("Email"),
						cm);

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

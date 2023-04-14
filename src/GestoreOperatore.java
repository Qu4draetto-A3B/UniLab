import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import utils.result.Panic;
import utils.result.Result;

public class GestoreOperatore {
	private static CSVFormat format = CSVFormat.DEFAULT.builder()
			.setHeader(new String[] { "CodFis", "UserID", "Nome", "Cognome", "Email", "Centro", "Password" })
			.setSkipHeaderRecord(true)
			.build();

	private static Reader in;
	private static Writer out;

	public static boolean registrazione(Operatore op, String pwd) {
		CSVPrinter p;
		try {
			out = new FileWriter("../data/OperatoriRegistrati.CSV");
			p = new CSVPrinter(out, format);
			p.printRecord(op.toCsv(), pwd);
			p.flush();
			p.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public Result<Operatore> login(String uid, String pwdhash) {
		Iterable<CSVRecord> it;

		try {
			in = new FileReader("../data/OperatoriRegistrati.CSV");
			it = format.parse(in);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Panic("Missing registered users database file, cannot start");
		}

		for (CSVRecord record : it) {
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

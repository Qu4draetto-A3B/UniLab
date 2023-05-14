package a3b.climate.gestori;

import java.security.MessageDigest;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVRecord;

import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.DataTable;
import a3b.climate.utils.result.*;

/**
 *
 */
public class GestoreOperatore extends Gestore {
	public GestoreOperatore() {
		super(
				"./data/OperatoriRegistrati.CSV",
				new String[] { "CodFis", "UserID", "Nome", "Cognome", "Email", "Centro", "Password" });
	}

	public Result<Operatore> registrazione(Operatore op, String pwd) {
		String cf = op.getCf().toUpperCase();
		pwd = hashPwd(pwd);

		if (getOperatoreByCf(cf).isValid()) {
			return new Result<>(1, "Operatore gia registrato");
		}

		if (!cfIsValid(cf) && false) {
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

		return new Result<>(getOperatoreByCf(cf).get());
	}

	public Result<Operatore> login(String uid, String pwd) {
		String pwdHash = hashPwd(pwd);

		for (CSVRecord record : records) {
			String dbUid = record.get("UserID");
			String dbPwd = record.get("Password");

			if (uid.equals(dbUid) && pwdHash.equals(dbPwd)) {
				return new Result<>((Operatore) buildObject(record));
			}
		}

		return new Result<>(1, "Operatore non trovato");
	}

	Result<Operatore> getOperatoreByCf(String cf) {
		for (CSVRecord record : records) {
			String dbCf = record.get("CodFis");
			DataTable op = buildObject(record);

			if (cf.equals(dbCf)) {
				return new Result<Operatore>((Operatore) op);
			}
		}

		return new Result<>(1, "Operatore valido non trovato");
	}

	Result<Operatore> getOperatoreByUid(String uid) {
		for (CSVRecord record : records) {
			String dbUid = record.get("UserID");
			DataTable op = buildObject(record);

			if (uid.equals(dbUid)) {
				return new Result<Operatore>((Operatore) op);
			}
		}

		return new Result<>(1, "Operatore valido non trovato");
	}

	private boolean cfIsValid(String cf) {
		Pattern cfPattern = Pattern.compile(
			"(?:[A-Z][AEIOU][AEIOUX]|[AEIOU]X{2}|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$");

		return cfPattern.matcher(cf).matches();
	}

	private String hashPwd(String pwd) {

		String pwdHash = pwd;
		/*
		try {
			pwdHash = MessageDigest.getInstance("SHA-256").digest(pwd.getBytes()).toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Panic(e);
		}*/

		return pwdHash;
	}

	@Override
	protected DataTable buildObject(CSVRecord record) {
		CentroMonitoraggio cm = DataBase.centro.getCentro(record.get("Centro")).get();
		Operatore op = new Operatore(record.get("CodFis"),
				record.get("UserID"),
				record.get("Nome"),
				record.get("Cognome"),
				record.get("Email"),
				cm);

		return op;
	}
}

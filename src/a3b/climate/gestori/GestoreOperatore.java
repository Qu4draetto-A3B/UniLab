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

import java.security.MessageDigest;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVRecord;

import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.DataTable;
import a3b.climate.utils.result.*;

/**
 * Gestisce le operazioni di lettura e scrittura riguardanti oggetti di tipo Operatore
 */
public class GestoreOperatore extends Gestore {
	/**
	 * Costruttore di un'istanza di tipo GestoreOperatore
	 */
	public GestoreOperatore() {
		super(
				"./data/OperatoriRegistrati.CSV",
				new String[] { "CodFis", "UserID", "Nome", "Cognome", "Email", "Centro", "Password" });
	}

	/**
	 * Metodo che permette registrare un operatore all'applicazione
	 * @param op Operatore da registrare
	 * @param pwd Password da impostare
	 * @return Restituisce un record relativo all'operatore registrato
	 */
	public Result<Operatore> registrazione(Operatore op, String pwd) {
		String cf = op.getCf().toUpperCase();
		pwd = hashPwd(pwd);

		if (getOperatoreByUid(op.getUid()).isValid() || getOperatoreByCf(cf).isValid()) {
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
	/**
	 * Metodo che permette a un utente di effettuare il login
	 * @param uid UserID relativo all'utente da registrare
	 * @param pwd Password relativa all'utente registrato
	 * @return Restituisce un record relativo all'operatore che ha effettuato il login
	 */
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

	/**
	 * Metodo che ricerca un operatore in base al suo codice fiscale
	 * @param cf Codice fiscale relativo all'operatore d'interesse
	 * @return Restituisce il record relativo all'operatore con il codice fiscale fornito come parametro
	 */
	Result<Operatore> getOperatoreByCf(String cf) {
		for (CSVRecord record : records) {
			String dbCf = record.get("CodFis");
			Operatore op = (Operatore) buildObject(record);

			if (cf.equals(dbCf)) {
				return new Result<>(op);
			}
		}

		return new Result<>(1, "Operatore valido non trovato");
	}
	/**
	 * Metodo che ricerca un operatore in base al suo UserID
	 * @param uid UserID relativo all'operatore d'interesse
	 * @return Restituisce il record relativo all'operatore con lo UserID fornito come parametro
	 */
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

	/**
	 * Metodo che controlla che un codice fiscale sia valido
	 * @param cf Codice fiscale da controllare
	 * @return Restituisce un booleano che rappresenta la validità del codice fiscale fornito come parametro
	 */
	public boolean cfIsValid(String cf) {
		Pattern cfPattern = Pattern.compile(
			"(?:[A-Z][AEIOU][AEIOUX]|[AEIOU]X{2}|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$");

		return cfPattern.matcher(cf).matches();
	}

	/**
	 * Metodo che restituisce una stringa per l'hash, tramite la stringa presa come parametro
	 * @param pwd
	 * @return
	 */
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
		Result<CentroMonitoraggio> rcm = DataBase.centro.getCentro(record.get("Centro"));

		return new Operatore(record.get("CodFis"),
				record.get("UserID"),
				record.get("Nome"),
				record.get("Cognome"),
				record.get("Email"),
				rcm.get());
	}
}

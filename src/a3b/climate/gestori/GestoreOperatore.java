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

import java.util.regex.Pattern;

import org.apache.commons.csv.CSVRecord;

import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.DataTable;
import a3b.climate.utils.result.*;

/**
 * La classe {@code GestoreOperatore} estende la classe {@link Gestore}.
 * <p>
 * Gestisce le operazioni di lettura e scrittura su file CSV di dati riguardanti
 * istanze di {@link Operatore}.
 */
public class GestoreOperatore extends Gestore {
	/**
	 * Costruttore di un'istanza di {@code Gestoreoperatore} che gestisce i dati
	 * relativi agli operatori.
	 *
	 * @see Gestore#Gestore(String, String[])
	 */
	public GestoreOperatore() {
		super(
				"./data/db/OperatoriRegistrati.CSV",
				new String[] { "CodFis", "UserID", "Nome", "Cognome", "Email", "Centro", "Password" });
	}

	/**
	 * Registra un nuovo operatore e la password associata nel relativo file
	 * CSV.
	 * <p>
	 * Controlla se &egrave gi&agrave presente un {@link Operatore} con lo stesso
	 * UID (user ID) o CF (codice fiscale) usando rispettivamente i metodi
	 * {@link #getOperatoreByUid(String)} e {@link #getOperatoreByCf(String)}.
	 * <p>
	 * Controlla che il codice fiscale fornito sia valido con il metodo
	 * {@link #cfIsValid(String)}.
	 * <p>
	 * Nel caso in cui l'operazione non venga eseguita correttamente (l'operatore
	 * sia già registrato, il codice fiscale risulti irregolare e/o vi sia un errore
	 * nella scrittura del record), restituisce un {@link Result} con un codice di
	 * errore.
	 *
	 * @param op  {@link Operatore} da registrare
	 * @param pwd password associata all'operatore
	 * @return {@link Result} contentente l'istanza di {@link Operatore} registrata
	 * @see #hashPwd(String)
	 */
	public Result<Operatore> registrazione(Operatore op, String pwd) {
		String cf = op.getCf().toUpperCase();
		pwd = hashPwd(pwd);

		if (getOperatoreByUid(op.getUid()).isValid() || getOperatoreByCf(cf).isValid()) {
			return new Result<>(1, "Operatore gia registrato");
		}

		/*
		 * if (!cfIsValid(cf)) {
		 * return new Result<>(2, "Codice fiscale irregolare");
		 * }
		 */

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
	 * Esegue l'accesso di un operatore verificando le credenziali fornite.
	 * <p>
	 * Scorre i record CSV per trovare il record corrispondente
	 * all'{@link Operatore} di cui eseguire l'accesso, per poi verificare la
	 * congurenza della password fornita.
	 * <p>
	 * Nel caso in cui l'operazione non venga eseguita correttemente (la password
	 * risulti errata e/o il record non venga trovato), restituisce un
	 * {@link Result} con un codice di errore.
	 *
	 * @param uid user ID associato all'operatore
	 * @param pwd password associata all'operatore
	 * @return {@link Record} contenente l'istanza di {@link Operatore} di cui viene
	 *         eseguito l'accesso
	 * @see #hashPwd(String)
	 */
	public Result<Operatore> login(String uid, String pwd) {
		// String pwdHash = hashPwd(pwd);

		for (CSVRecord record : records) {
			String dbUid = record.get("UserID");
			String dbPwd = record.get("Password");

			if (uid.equals(dbUid)) {
				if (pwd.equals(dbPwd)) {
					return new Result<>((Operatore) buildObject(record));
				} else {
					return new Result<>(2, "Password errata");
				}
			}
		}

		return new Result<>(1, "Operatore non trovato");
	}

	/**
	 * Recupera un'istanza di {@link Operatore} basandosi sul CF (codice fiscale)
	 * fornito.
	 * <p>
	 * Ricerca un record CSV con il codice fiscale specifico nella lista di record e
	 * costruisce il rispettivo {@link Operatore} usando il metodo
	 * {@link #buildObject(CSVRecord)}.
	 * <p>
	 * Nel caso in cui non venga trovato nessun record corrispondente al CF fornito,
	 * restituisce un {@link Result} con un codice di errore.
	 *
	 * @param cf codice fiscale relativo al centro di monitoraggio d'interesse
	 * @return restituisce l'istanza di {@link Operatore} corrispondente al
	 *         codice fiscale fornito come parametro
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
	 * Recupera un'istanza di {@link Operatore} basandosi sul UID (user ID) fornito.
	 * <p>
	 * Ricerca un record CSV con lo user ID specifico nella lista di record e
	 * costruisce il rispettivo {@link Operatore} usando il metodo
	 * {@link #buildObject(CSVRecord)}.
	 * <p>
	 * Nel caso in cui non venga trovato nessun record corrispondente al UID
	 * fornito, restituisce un {@link Result} con un codice di errore.
	 *
	 * @param uid user ID relativo al centro di monitoraggio d'interesse
	 * @return restituisce l'istanza di {@link Operatore} corrispondente al
	 *         user ID fornito come parametro
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
	 * Controlla la validità di un CF (codice fiscale).
	 * <p>
	 * Crea un {@link Pattern} per eseguire una verifica tramite <i>regex</i>.
	 *
	 * @param cf codice fiscale da controllare
	 * @return {@code boolean} che rappresenta la validità del codice fiscale
	 *         fornito
	 */
	public boolean cfIsValid(String cf) {
		Pattern cfPattern = Pattern.compile(
				"(?:[A-Z][AEIOU][AEIOUX]|[AEIOU]X{2}|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$");

		return cfPattern.matcher(cf).matches();
	}

	/**
	 * <b><i>Metodo non implementato</i></b>
	 * <p>
	 * Cifra la password fornita utilizzando l'algoritmo di hash SHA-256.
	 *
	 * @param pwd password da cifrare
	 * @return password cifrata (hash)
	 */
	private String hashPwd(String pwd) {

		String pwdHash = pwd;
		/*
		 * try {
		 * pwdHash =
		 * MessageDigest.getInstance("SHA-256").digest(pwd.getBytes()).toString();
		 * } catch (Exception e) {
		 * e.printStackTrace();
		 * throw new Panic(e);
		 * }
		 */

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

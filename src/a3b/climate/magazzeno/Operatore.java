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
package a3b.climate.magazzeno;

import java.time.LocalDateTime;

import a3b.climate.gestori.DataBase;
import a3b.climate.utils.Convertable;
import a3b.climate.utils.DataTable;
import a3b.climate.utils.result.Result;

/**
 * Rappresenta un operatore
 */

public class Operatore implements Convertable, DataTable {
	private String nome, cognome, email, cf, uid;
	private CentroMonitoraggio centro;

	/**
	 * Costruttore di un'istanza di Operatore
	 *
	 * @param cf      Codice fiscale dell'operatore
	 * @param uid     User ID relativo all'operatore
	 * @param nome    Nome dell'operatore
	 * @param cognome Cognome dell'operatore
	 * @param email   E-mail dell'operatore
	 * @param centro  Centro di monitoraggio a cui l'operatore e' associato
	 */

	public Operatore(String cf, String uid, String nome, String cognome, String email, CentroMonitoraggio centro) {
		this.cf = cf;
		this.uid = uid;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.centro = centro;
	}

	/**
	 * Costruttore vuoto di un'istanza di Operatore
	 */
	public Operatore() {
		cf = "CIVILE";
		uid = "civile";
		nome = "Civile";
		cognome = "Civile";
		email = "civile@example.com";
		centro = new CentroMonitoraggio();
	}

	/**
	 * Metodo che consente di inserire i dati climatici di una determinata area nel
	 * database
	 *
	 * @param area  Area geografica relativa ai dati
	 * @param dato  Aggregato di informazioni (valori dei parametri climatici)
	 *              relative al dato geografico
	 * @param tempo Data in cui avviene l'inserimento dei dati nel database
	 */
	public Result<Misurazione> inserisciParametri(AreaGeografica area, DatoGeografico dato, LocalDateTime tempo) {
		Misurazione mis = new Misurazione(0, tempo, this, centro, area, dato);
		return DataBase.misurazioni.addMisurazione(mis);
	}

	/**
	 * @return Restituisce il codice fiscale dell'operatore che esegue il metodo
	 */

	public String getCf() {
		return cf;
	}

	/**
	 * @return Restituisce il centro di monitoraggio al quale l'operatore che esegue
	 *         il metodo e' associato
	 */

	public CentroMonitoraggio getCentro() {
		return centro;
	}

	/**
	 * @return Restituisce il cognome dell'operatore che esegue il metodo
	 */

	public String getCognome() {
		return cognome;
	}

	/**
	 * @return Restituisce la e-mail dell'operatore che esegue il metodo
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * @return Restituisce il nome dell'operatore che esegue il metodo
	 */

	public String getNome() {
		return nome;
	}

	/**
	 * @return Restituisce lo user ID relativo all'operatore che esegue il metodo
	 */

	public String getUid() {
		return uid;
	}

	@Override
	public String toString() {
		String str = String.format(
				"%s: (\n\tCF: %s\n\tUserID: %s\n\tNome: %s\n\tCognome: %s\n\tEmail: %s\n\tCentro: %s\n)\n",
				super.toString(), cf, uid, nome, cognome, email, centro.getNome());

		str += centro.toString();

		return str;
	}

	public String toStringPretty() {
		return String.format(
				"C.F.\t: %s\nUser ID\t: %s\nNome\t: %s %s\nEmail\t: %s\nCentro\t: %s",
				cf, uid, nome, cognome, email, centro.getNome());
	}

	@Override
	public String toCsv() {
		String csv = String.format("%s,%s,%s,%s,%s,%s", cf, uid, nome, cognome, email, centro.getNome());
		return csv;
	}

	@Override
	public String toJson() {
		//
		return "";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Operatore)) {
			return super.equals(obj);
		}

		Operatore op = (Operatore) obj;

		if (cf.equals(op.getCf()))
			return true;

		return false;
	}
}

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
 * La classe {@code Operatore} rappresenta un operatore identificato
 * da: nome, cognome, e-mail, codice fiscale, user ID e centro di monitoraggio
 * associato.
 * <p>
 * Questa classe implementa le interfacce {@link Convertable} e
 * {@link DataTable} per consentire la
 * gestione dei dati.
 */

public class Operatore implements Convertable, DataTable {
	private String nome, cognome, email, cf, uid;
	private CentroMonitoraggio centro;

	/**
	 * Costruttore di un'istanza di {@code Operatore}
	 *
	 * @param cf      codice fiscale dell'operatore
	 * @param uid     user ID relativo all'operatore
	 * @param nome    nome dell'operatore
	 * @param cognome cognome dell'operatore
	 * @param email   e-mail dell'operatore
	 * @param centro  centro di monitoraggio a cui l'operatore &egrave associato
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
	 * Costruttore di un'istanza di {@code Operatore} con valori predefiniti.
	 * <p>
	 * Il {@link #cf} viene impostato su "<i>CIVILE</i>", lo {@link #uid} su
	 * "<i>civile</i>",
	 * il {@link #nome} su "<i>Civile</i>", il {@link #cognome} su"<i>Civile</i>" e
	 * la {@link #email} su "<i>civile@example.com</i>".
	 * Il {@link #centro} viene inizializzato con un nuovo
	 * {@link CentroMonitoraggio} predefinito.
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
	 * Imposta i dati climatici di una determinata area nel database.
	 *
	 * @param area  {@link AreaGeografica} relativa ai dati
	 * @param dato  {@link DatoGeografico} contenente i valori dei parametri
	 *              climatici
	 * @param tempo data e ora in cui avviene l'inserimento dei dati nel database
	 */
	public Result<Misurazione> inserisciParametri(AreaGeografica area, DatoGeografico dato, LocalDateTime tempo) {
		Misurazione mis = new Misurazione(0, tempo, this, centro, area, dato);
		return DataBase.misurazioni.addMisurazione(mis);
	}

	/**
	 * Restituisce il codice fiscale dell'operatore.
	 *
	 * @return {@link #cf} relativo all'{@code Operatore}
	 */
	public String getCf() {
		return cf;
	}

	/**
	 * Restituisce il centro di monitoraggio a cui &egrave associato l'operatore.
	 *
	 * @return {@link #centro} relativo all'{@code Operatore}
	 */
	public CentroMonitoraggio getCentro() {
		return centro;
	}

	/**
	 * Restituisce il cognome dell'operatore.
	 *
	 * @return {@link #cognome} relativo all'{@code Operatore}
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Restituisce la e-mail dell'operatore.
	 *
	 * @return {@link #email} relativo all'{@code Operatore}
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Restituisce il nome dell'operatore.
	 *
	 * @return {@link #nome} relativo all'{@code Operatore}
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Restituisce lo user ID dell'operatore.
	 *
	 * @return {@link #uid} relativo all'{@code Operatore}
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

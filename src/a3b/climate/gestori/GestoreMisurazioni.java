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

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import a3b.climate.magazzeno.DatoGeografico;
import a3b.climate.magazzeno.Filtratore;
import a3b.climate.magazzeno.Misurazione;
import a3b.climate.utils.DataTable;
import a3b.climate.utils.result.*;

/**
 * La classe {@code GestoreMisurazioni} estende la classe {@link Gestore}.
 * <p>
 * Gestisce le operazioni di lettura e scrittura su file CSV di dati riguardanti
 * istanze di {@link Misurazione}.
 */
public class GestoreMisurazioni extends Gestore {
	/**
	 * Costruttore di un'istanza di {@code GestoreMisurazioni} che gestisce i dati
	 * relativi alle misurazioni.
	 *
	 * @see Gestore#Gestore(String, String[])
	 */
	public GestoreMisurazioni() {
		super(
				"./data/Misurazioni.CSV",
				new String[] { "RID", "DateTime", "Operatore", "Centro", "Area", "Dato" });
	}

	/**
	 * Aggiunge una nuova misurazione al file CSV associato.
	 * <p>
	 * Recupera la propret&agrave <i>LastRID</i> (ultimo record ID) dal file di
	 * metadati usando il metodo {@link #getProperty(String)} e la incrementa di 1
	 * per creare un nuovo record.
	 * La propriet&agrave aggiornata viene reimpostata nel file di metadati usando il
	 * metodo {@link #setProperty(String, String)}.
	 * <p>
	 * Il record con i dati relativi alla {@link Misurazione} viene aggiunto nel file CSV.
	 * <p>
	 * Nel caso in cui vi sia un errore nella scrittura del record, restituisce un
	 * {@link Result} con un codice di errore.
	 *
	 * @param mis {@link Misurazione} da aggiungere al file CSV
	 * @return {@link Result} contenente la nuova istanza di {@link Misurazione}
	 *         aggiunta con il record ID aggiornato
	 */
	public Result<Misurazione> addMisurazione(Misurazione mis) {
		long newRID = Long.parseLong(getProperty("LastRID").get());
		newRID++;
		setProperty("LastRID", String.valueOf(newRID)).get();

		try {
			p.printRecord(
					newRID,
					mis.getTimeString(),
					mis.getOperatore().getCf(),
					mis.getCentro().getNome(),
					mis.getArea().getGeoID(),
					mis.getDato().getRid());

			p.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return new Result<>(1, "Errore nella scrittura del record");
		}

		return new Result<>(new Misurazione(newRID, mis.getTime(), mis.getOperatore(), mis.getCentro(), mis.getArea(),
				mis.getDato()));
	}

	/**
	 * Inizializza un nuovo filtratore contenente una lista di misurazioni.
	 * <p>
	 * Scorre una lista di record CSV e crea una lista di istanze di
	 * {@link Misurazione} usando il metodo {@link #buildObject(CSVRecord)}, la
	 * quale viene utilizzata per inizializzare un nuovo {@link Filtratore}.
	 *
	 * @return {@link Result} contenente il filtratore creato con la lista
	 *         di istanze di {@link Misurazione}.
	 */

	public Result<Filtratore> getMisurazioni() {
		List<Misurazione> lm = new LinkedList<>();
		for (CSVRecord record : records) {
			lm.add((Misurazione) buildObject(record));
		}
		return new Result<>(new Filtratore(lm));
	}

	@Override
	protected DataTable buildObject(CSVRecord record) {
		Misurazione mis = new Misurazione(
				Long.parseLong(record.get("RID")),
				LocalDateTime.parse(record.get("DateTime"), Misurazione.DATE_TIME_FORMAT),
				DataBase.operatore.getOperatoreByCf(record.get("Operatore")).get(),
				DataBase.centro.getCentro(record.get("Centro")).get(),
				DataBase.area.getArea(Long.parseLong(record.get("Area"))).get(),
				DataBase.dato.getDato(0).get());

		return mis;
	}
}

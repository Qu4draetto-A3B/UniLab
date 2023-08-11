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
import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;

import a3b.climate.cli.App;
import a3b.climate.magazzeno.DatoGeografico;
import a3b.climate.utils.DataTable;
import a3b.climate.utils.TipoDatoGeografico;
import a3b.climate.utils.result.Result;

/**
 * La classe {@code GestoreDato} estende la classe {@link Gestore}.
 * <p>
 * Gestisce le operazioni di lettura e scrittura su file CSV di dati riguardanti
 * istanze di {@link a3b.climate.magazzeno.DatoGeografico}.
 */
public class GestoreDato extends Gestore {

	/**
	 * Costruttore di un'istanza di {@code GestoreDato} che gestisce i dati
	 * relativi ai dati geografici.
	 *
	 * @see Gestore#Gestore(String, String[])
	 */
	public GestoreDato() {
		super(
				"./data/db/ParametriClimatici.CSV",
				new String[] {
						"RID", "AltitudineGhiacciai", "MassaGhiacciai", "Precipitazioni", "Pressione",
						"Temperatura", "Umidita", "Vento",
						"NotaAltitudineGhiacciai", "NotaMassaGhiacciai", "NotaPrecipitazioni",
						"NotaPressione", "NotaTemperatura", "NotaUmidita", "NotaVento" });
	}

	/**
	 * Recupera un'istanza di {@link a3b.climate.magazzeno.DatoGeografico} basandosi sull'ID
	 * specificato.
	 * <p>
	 * Ricerca un record CSV con l'ID specifico nella lista di record e
	 * costruisce il rispettivo {@link a3b.climate.magazzeno.DatoGeografico} usando il metodo
	 * {@link #buildObject(CSVRecord)}.
	 * <p>
	 * Nel caso in cui non venga trovato nessun record corrispondente all'ID
	 * fornito,
	 * restituisce un {@link Result} con un codice di errore.
	 *
	 * @param rid ID relativo al dato geografico d'interesse
	 * @return restituisce l'istanza di {@link a3b.climate.magazzeno.DatoGeografico} corrispondente all'ID
	 *         fornito come parametro
	 */
	public Result<DatoGeografico> getDato(long rid) {
		CSVRecord rec = null;
		long dbRid = 0;

		for (CSVRecord record : records) {
			dbRid = Long.parseLong(record.get("RID"));
			if (dbRid == rid) {
				rec = record;
				break;
			}
		}

		if (rec == null) {
			return new Result<>(1, "Dato non trovato");
		}

		return new Result<>((DatoGeografico) buildObject(rec));
	}

	/**
	 * Aggiunge un nuovo dato geografico al file CSV associato.
	 * <p>
	 * Recupera la propret&agrave; <i>LastRID</i> (ultimo record ID) dal file di
	 * metadati usando il metodo {@link #getProperty(String)} e la incrementa di uno
	 * per creare un nuovo record.
	 * La propriet&agrave; aggiornata viene reimpostata nel file di metadati usando
	 * il
	 * metodo {@link #setProperty(String, String)}.
	 * <p>
	 * Il record con i dati relativi al {@link a3b.climate.magazzeno.DatoGeografico} viene aggiunto al
	 * file CSV.
	 * <p>
	 * Nel caso in cui vi sia un errore nella scrittura del record, restituisce un
	 * {@link Result} con un codice di errore.
	 *
	 * @param dato {@link a3b.climate.magazzeno.DatoGeografico} da aggiungere al file CSV
	 * @return {@link Result} contenente la nuova istanza di {@link a3b.climate.magazzeno.DatoGeografico}
	 *         aggiunta e il record ID aggiornato
	 */
	public Result<DatoGeografico> addDato(DatoGeografico dato) {
		long newRID = App.rng.nextLong(0, 1000000);// Long.parseLong(getProperty("LastRID").get());
		// long newRID = Long.parseLong(getProperty("LastRID").get());
		// newRID++;
		// setProperty("LastRID", String.valueOf(newRID)).get();

		try {
			// haha
			p.printRecord(
					newRID,
					dato.getDato(TipoDatoGeografico.AltitudineGhiacciai),
					dato.getDato(TipoDatoGeografico.MassaGhiacciai),
					dato.getDato(TipoDatoGeografico.Precipitazioni),
					dato.getDato(TipoDatoGeografico.Pressione),
					dato.getDato(TipoDatoGeografico.Temperatura),
					dato.getDato(TipoDatoGeografico.Umidita),
					dato.getDato(TipoDatoGeografico.Vento),
					dato.getNota(TipoDatoGeografico.AltitudineGhiacciai),
					dato.getNota(TipoDatoGeografico.MassaGhiacciai),
					dato.getNota(TipoDatoGeografico.Precipitazioni),
					dato.getNota(TipoDatoGeografico.Pressione),
					dato.getNota(TipoDatoGeografico.Temperatura),
					dato.getNota(TipoDatoGeografico.Umidita),
					dato.getNota(TipoDatoGeografico.Vento));

			p.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return new Result<>(1, "Errore nella scrittura del record");
		}

		return new Result<>(new DatoGeografico(newRID, dato.getDati(), dato.getNote()));
	}

	@Override
	protected DataTable buildObject(CSVRecord record) {
		long rid = Long.parseLong(record.get("RID"));

		HashMap<TipoDatoGeografico, Byte> dati = new HashMap<>();
		HashMap<TipoDatoGeografico, String> note = new HashMap<>();

		for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
			dati.put(tipo, Byte.parseByte(record.get(tipo.name())));
			note.put(tipo, record.get("Nota" + tipo.name()));
		}

		DatoGeografico dato = new DatoGeografico(rid, dati, note);

		return dato;
	}
}

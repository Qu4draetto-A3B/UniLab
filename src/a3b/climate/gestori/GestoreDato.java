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

import a3b.climate.magazzeno.DatoGeografico;
import a3b.climate.utils.DataTable;
import a3b.climate.utils.TipoDatoGeografico;
import a3b.climate.utils.result.Result;

/**
 * Gestisce le operazioni di lettura e scrittura riguardanti oggetti di tipo DatoGeografico
 */
public class GestoreDato extends Gestore {

	/**
	 * Costruttore di un'istanza di GestoreDato
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
	 * Metodo che ricerca un determinato dato geografico in base al suo ID
	 * @param rid ID relativo al dato geografico
	 * @return Restituisce il dato geografico corrispondente all'ID fornito come parametro
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
	 * Metodo che crea un nuovo record relativo a un determinato dato geografico e lo memorizza nel file ParametriClimatici.CSV
	 * @param dato Dato geografico per cui si vuole creare un nuovo record
	 * @return Restituisce un nuovo record relativo al dato geografico fornito come parametro
	 */
	public Result<DatoGeografico> addDato(DatoGeografico dato) {
		long newRID = Long.parseLong(getProperty("LastRID").get());
		newRID++;
		setProperty("LastRID", String.valueOf(newRID)).get();

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

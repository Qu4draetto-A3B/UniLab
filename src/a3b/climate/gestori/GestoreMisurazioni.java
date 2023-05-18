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

import a3b.climate.magazzeno.Filtratore;
import a3b.climate.magazzeno.Misurazione;
import a3b.climate.utils.DataTable;
import a3b.climate.utils.result.*;

/**
 * Gestisce le operazioni di lettura e scrittura riguardanti oggetti di tipo Misurazione
 */
public class GestoreMisurazioni extends Gestore {
	/**
	 * Costruttore di un'istanza di GestoreMisurazioni
	 */
	public GestoreMisurazioni() {
		super(
				"./data/Misurazioni.CSV",
				new String[] { "RID", "DateTime", "Operatore", "Centro", "Area", "Dato" });
	}

	/**
	 * Metodo che crea un nuovo record relativo a una determinata misurazione
	 * @param mis Misurazione di cui si vuole creare un nuovo record
	 * @return Record relativo alla misurazione fornita come parametro
	 */
	public Result<Object> addMisurazione(Misurazione mis) {
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

		return new Result<>(new Object());
	}

	/**
	 * Metodo che memorizza i record relativi alle misurazioni presenti nel file ParametriClimatici.CSV
	 * in una lista, la quale inizializza un nuovo oggetto di tipo FIltratore
	 * @return Record //TODO
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

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

import org.apache.commons.csv.CSVRecord;

import a3b.climate.magazzeno.AreaGeografica;
import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Indirizzo;
import a3b.climate.magazzeno.ListaAree;
import a3b.climate.utils.CercaAree;
import a3b.climate.utils.DataTable;
import a3b.climate.utils.result.Result;

/**
 * La classe {@code GestoreCentro} estende la classe {@link Gestore}.
 * <p>
 * Gestisce le operazioni di lettura e scrittura su file CSV di dati riguardanti
 * istanze di {@link CentroMonitoraggio}.
 */
public class GestoreCentro extends Gestore {
	/**
	 * Costruttore di un'istanza di {@code GestoreCentro} che gestisce i dati
	 * relativi ai centri di monitoraggio.
	 *
	 * @see Gestore#Gestore(String, String[])
	 */
	public GestoreCentro() {
		super(
				"./data/CentriMonitoraggio.CSV",
				new String[] { "Name", "Address", "Areas" });
	}

	/**
	 * Recupera un'istanza di {@link CentroMonitoraggio} basandosi sul nome
	 * specificato.
	 * <p>
	 * Ricerca un record CSV con il nome specifico nella lista di record e
	 * costruisce il rispettivo {@link CentroMonitoraggio} usando il metodo
	 * {@link #buildObject(CSVRecord)}.
	 * <p>
	 * Nel caso in cui non venga trovato nessun record corrispondente al nome
	 * fornito,
	 * restituisce un {@link Result} con un codice di errore.
	 *
	 * @param nome nome relativo al centro di monitoraggio d'interesse
	 * @return restituisce l'istanza di {@link CentroMonitoraggio} corrispondente al
	 *         nome fornito come parametro
	 */
	public Result<CentroMonitoraggio> getCentro(String nome) {
		for (CSVRecord record : records) {
			if (record.get("Name").contains(nome)) {
				return new Result<>((CentroMonitoraggio) buildObject(record));
			}
		}

		return new Result<>(1, "Centro non trovato");
	}

	/**
	 * Aggiunge un nuovo centro di monitoraggio al relativo file CSV.
	 * <p>
	 * Controlla se un {@link CentroMonitoraggio} con lo stesso nome &egrave
	 * gi&agrave presente nel file utilizzando il metodo {@link #getCentro(String)}.
	 * <p>
	 * Nel caso in cui l'operazione non venga eseguita correttamente (il centro sia
	 * gi&agrave esistente e/o vi sia un errore nella scrittura del record),
	 * restituisce un {@link Result} con un codice di errore.
	 *
	 * @param cm {@link CentroMonitoraggio} da aggiungere al file CSV
	 * @return {@link Result} contenente il {@link CentroMonitoraggio} aggiunto
	 */
	public Result<CentroMonitoraggio> addCentro(CentroMonitoraggio cm) {
		if (getCentro(cm.getNome()).isValid()) {
			return new Result<>(1, "Centro gia esistente");
		}

		try {
			p.printRecord(cm.getNome(), cm.getIndirizzo().toCsv(), cm.getListaAree().toCsv());
			p.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return new Result<>(1, "Errore nella scrittura del record");
		}
		return new Result<CentroMonitoraggio>(cm);
	}

	@Override
	protected DataTable buildObject(CSVRecord record) {
		String nomo = record.get("Name");

		String indStr = record.get("Address");
		String[] indArray = indStr.split(":");

		Indirizzo ind = new Indirizzo(
				indArray[0],
				Integer.parseInt(indArray[1]),
				Integer.parseInt(indArray[2]),
				indArray[3],
				indArray[4]);

		ListaAree lag = new ListaAree();

		String[] coordArr = record.get("Areas").split(";");

		for (String coord : coordArr) {
			String[] ll = coord.split(":");
			double lat = Double.parseDouble(ll[0]);
			double lon = Double.parseDouble(ll[1]);

			lag.addFirst(DataBase.area.cercaAreeGeografiche(lat, lon).get());
		}

		CentroMonitoraggio cm = new CentroMonitoraggio(nomo, ind, lag);

		return cm;
	}
}

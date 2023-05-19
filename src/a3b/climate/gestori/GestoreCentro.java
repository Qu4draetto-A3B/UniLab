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

import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Indirizzo;
import a3b.climate.magazzeno.ListaAree;
import a3b.climate.utils.DataTable;
import a3b.climate.utils.result.Result;

/**
 * Classe che gestisce le operazioni di lettura e scrittura riguardanti oggetti
 * di tipo CentroMonitoraggio
 */
public class GestoreCentro extends Gestore {
	/**
	 * Costruttore di un'istanza di GestoreCentro
	 */
	public GestoreCentro() {
		super(
				"./data/CentriMonitoraggio.CSV",
				new String[] { "Name", "Address", "Areas" });
	}

	/**
	 * Metodo che ricerca un centro di monitoraggio in base al nome
	 *
	 * @param nome Nome del centro di monitoraggio di interesse
	 * @return Record relativo al centro di monitoraggio corrispondente al nome
	 *         fornito come parametro
	 */
	public Result<CentroMonitoraggio> getCentro(String nome) {
		CSVRecord target = null;

		for (CSVRecord record : records) {
			if (record.get("Name").equalsIgnoreCase(nome)) {
				target = record;
				break;
			}
		}

		if (target == null) {
			return new Result<>(1, "Centro non trovato");
		}

		return new Result<>((CentroMonitoraggio) buildObject(target));
	}

	/**
	 * Metodo che crea un nuovo record relativo a un determinato centro di
	 * monitoraggio e lo memorizza nel file CentriMonitoraggio.CSV
	 *
	 * @param cm Centro di monitoraggio di cui si vuole creare un nuovo record
	 * @return Booleano che indica se l'operazione e' andata a buon fine
	 */
	public boolean addCentro(CentroMonitoraggio cm) {
		if (getCentro(cm.getNome()).isValid()) {
			return false;
		}

		try {
			p.printRecord(cm.getNome(), cm.getIndirizzo().toCsv(), cm.getListaAree().toCsv());
			p.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
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

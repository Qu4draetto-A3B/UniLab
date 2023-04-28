package a3b.climate.gestori;

import org.apache.commons.csv.CSVRecord;

import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Indirizzo;
import a3b.climate.magazzeno.ListaAree;
import a3b.climate.utils.result.Result;

public class GestoreCentro extends Gestore {
	public GestoreCentro() {
		super(
				"./data/CentriMonitoraggio.CSV",
				new String[] { "Name", "Address", "Areas" });
	}

	public Result<CentroMonitoraggio> getCentro(String nome) {
		CSVRecord target = null;

		for (CSVRecord record : records) {
			if (record.get("Name").toLowerCase().equals(nome.toLowerCase())) {
				target = record;
				break;
			}
		}

		if (target == null) {
			return new Result<>(1, "Centro non trovato");
		}

		String nomo = target.get("Name");
		String[] indArray = target.get("Address").split("|");
		Indirizzo ind = new Indirizzo(indArray[0], Integer.parseInt(indArray[1]), Integer.parseInt(indArray[2]),
				indArray[3], indArray[4]);
		ListaAree lag = new ListaAree();

		String[] coordArr = target.get("Areas").split(";");

		for (String coord : coordArr) {
			String[] ll = coord.split("|");
			double lat = Double.parseDouble(ll[0]);
			double lon = Double.parseDouble(ll[1]);

			lag.addFirst(DataBase.area.cercaAreeGeografiche(lat, lon).get());
		}

		CentroMonitoraggio cm = new CentroMonitoraggio(nomo, ind, lag);

		return new Result<>(cm);
	}

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
}

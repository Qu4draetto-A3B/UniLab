package a3b.climate.cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Indirizzo;
import a3b.climate.magazzeno.ListaAree;
import a3b.climate.utils.IniFile;
import a3b.climate.utils.result.Result;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

/**
 * La classe {@code ComandoCentri} implementa l'interfaccia {@link View} per
 * gestire il comando che crea un nuovo centro di monitoraggio.
 * <p>
 * Legge la configurazione necessaria da un file INI per creare una nuova
 * istanza di {@link a3b.climate.magazzeno.CentroMonitoraggio} e la aggiunge al database.
 */
public class ComandoCentri implements View {

	/**
	 * Legge da un file INI la configurazione necessaria per creare un nuovo centro
	 * di monitoraggio.
	 * <p>
	 * Il nuovo {@link a3b.climate.magazzeno.CentroMonitoraggio} viene aggiunto al database e il risultato
	 * dell'operazione viene stampato nel terminale.
	 *
	 * @param term istanza di {@link Terminal} utilizzata per stampare il nuovo
	 *             centro di monitoraggio
	 */
	@Override
	public void start(Terminal term) throws IOException {
		Path misIni = Paths.get("./CENTRO.INI");
		Path resIni = Paths.get("./data/resources/CENTRO.INI");

		if (Files.notExists(misIni)) {
			Files.copy(resIni, misIni, StandardCopyOption.REPLACE_EXISTING);
			term.printfln(
					"File '%s' creato, riempilo con le informazioni necessarie e riavvia l'applicazione con lo stesso comando",
					misIni.toString());
			return;
		}

		IniFile ini = new IniFile(misIni.toString());

		String nome = ini.getString("centro_monitoraggio", "nome", "*");

		if (nome.contains("*")) {
			term.printfln("Non sono riuscito a recuperare il nome del centro");
			return;
		}

		String geoids = ini.getString("centro_monitoraggio", "aree", "*");

		if (geoids.contains("*")) {
			term.printfln("Non sono riuscito a recuperare i geoid");
			return;
		}

		ListaAree lag = new ListaAree();

		for (String str : geoids.split(" ")) {
			try {
				long id = Long.parseLong(str);
				DataBase.area.getArea(id).ifValid((v, e) -> lag.addFirst(v));
			} catch (Exception e) {
				term.printfln("Non sono riuscito a convertire '%s' in un geoid", str);
			}
		}

		Indirizzo ind = new Indirizzo(
				ini.getString("indirizzo", "via", "*"),
				ini.getInt("indirizzo", "civico", 0),
				ini.getInt("indirizzo", "cap", 0),
				ini.getString("indirizzo", "comune", "*"),
				ini.getString("indirizzo", "provincia", "*"));

		if (ind.toString().contains("*")) {
			term.printfln("Non sono riuscito a recuperare parti dell'indirizzo");
			return;
		}

		CentroMonitoraggio cm = new CentroMonitoraggio(nome, ind, lag);
		Result<CentroMonitoraggio> res = DataBase.centro.addCentro(cm);

		if (res.isValid()) {
			term.printfln("Centro aggiunto");
		} else {
			term.printfln("Errore nell'aggiunta del centro: %s", res.getFullMessage());
		}

		term.printfln("%s", cm);
	}
}

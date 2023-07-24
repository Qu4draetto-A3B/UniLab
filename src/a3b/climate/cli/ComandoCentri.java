package a3b.climate.cli;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Indirizzo;
import a3b.climate.magazzeno.ListaAree;
import a3b.climate.utils.IniFile;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

/**
 * ComandoCentri
 */
public class ComandoCentri implements View {

	@Override
	public void start(Terminal term) {
		String path = App.line.getOptionValue("crea-centro");
		if (path.isBlank())
			path = "./CENTRO.INI";

		IniFile ini = null;
		try {
			ini = new IniFile(path);
		} catch (Exception e) {
			term.printfln("Impossibile leggere il file '%s'", path);
			return;
		}


		String nome = ini.getString("centro", "nome", "*");
		String geoids = ini.getString("centro", "aree", "*");
		ListaAree lag = new ListaAree();

		for (String str : geoids.split(",")) {
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
			ini.getString("indirizzo", "provincia", "*")
		);

		if (nome.contains("*")) {
			term.printfln("Non sono riuscito a recuperare il nome del centro");
			return;
		}

		if (geoids.contains("*")) {
			term.printfln("Non sono riuscito a recuperare i geoid");
			return;
		}

		if (ind.toString().contains("*")) {
			term.printfln("Non sono riuscito a recuperare parti dell'indirizzo");
			return;
		}

		CentroMonitoraggio cm = new CentroMonitoraggio(nome, ind, lag);

		if (DataBase.centro.addCentro(cm)) {
			term.printfln("Centro aggiunto");
		} else {
			term.printfln("Errore nell'aggiunta del centro");
		}

		term.printfln("%s", cm);
	}
}

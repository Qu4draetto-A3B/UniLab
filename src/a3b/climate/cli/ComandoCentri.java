package a3b.climate.cli;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Indirizzo;
import a3b.climate.magazzeno.ListaAree;
import a3b.climate.utils.IniFile;
import a3b.climate.utils.result.Result;
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

		if (nome.contains("*")) {
			term.printfln("Non sono riuscito a recuperare il nome del centro");
			return;
		}

		String geoids = ini.getString("centro", "aree", "*");

		if (geoids.contains("*")) {
			term.printfln("Non sono riuscito a recuperare i geoid");
			return;
		}

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

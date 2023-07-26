package a3b.climate.cli;

import java.util.Objects;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.Filtratore;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.IniFile;
import a3b.climate.utils.result.Result;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

/**
 * Query
 */
public class Query implements View {

	@Override
	public void start(Terminal term) {
		String path = App.line.getOptionValue("query");
		if (path.isBlank())
			path = "./QUERY.INI";

		IniFile ini = null;
		try {
			ini = new IniFile(path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (Objects.isNull(ini)) {
			term.printfln("Errore nel recupero del file %", path);
			return;
		}

		Result<Operatore> rop = DataBase.operatore.login(
				ini.getString("utente", "nome_utente", ""),
				ini.getString("utente", "password", ""));

		if (rop.isError()) {
			term.printfln("Non sono riuscito ad autenticarti.\n%s", rop.getFullMessage());
			return;
		}

		Filtratore fil = DataBase.misurazioni.getMisurazioni().get();

		// momento stanchezza pigrizia
		String search = String.format("%s %s %s %s %s %s", ini.getString("query", "record_id", "*"),
				ini.getString("query", "aree", "*"), ini.getString("query", "operatori", "*"),
				ini.getString("query", "centri", "*"), ini.getString("query", "data_ora", "*"),
				ini.getString("query", "dato_geografico", "*"));

		fil = fil.filtraStrings(search.split(" "));

		term.printfln("%s", fil.toString());
	}
}

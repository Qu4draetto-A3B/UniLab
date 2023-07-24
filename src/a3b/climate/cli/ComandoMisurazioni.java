package a3b.climate.cli;

import a3b.climate.utils.IniFile;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

/**
 * ComandoMisurazioni
 */
public class ComandoMisurazioni implements View {

	@Override
	public void start(Terminal term) {
		String path = App.line.getOptionValue("query");
		if (path.isBlank())
			path = "./MISURAZIONE.INI";

		IniFile ini = null;
		try {
			ini = new IniFile(path);
		} catch (Exception e) {
			term.printfln("Impossibile leggere il file '%s'", path);
			return;
		}
	}
}

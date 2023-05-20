package a3b.climate.cli;

import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;
import org.apache.commons.cli.CommandLine;

import java.io.File;
import java.io.IOException;

public class Registrazione implements View {
	public void start(Terminal term) {
		File f = new File("./REGISTRAZIONE.INI");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			term.printfln("File 'REGISTRAZIONE.INI' creato.\nInserire i parametri richiesti, poi riavviare con '%s --avvia-registrazione avvia'", App.programName);
			return;
		}

		if (App.line.getOptionValue("avvia-registrazione", "NONE").equals("NONE")) {
			term.printfln("Forse intendevi '%s --avvia-registrazione avvia'?", App.programName);
		}
	}
}

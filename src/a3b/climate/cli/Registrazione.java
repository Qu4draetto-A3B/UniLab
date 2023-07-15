package a3b.climate.cli;

import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

import java.io.*;

public class Registrazione implements View {

	public void start(Terminal term) {
		File f = new File("./REGISTRAZIONE.INI");
		if (!f.exists()) {
			try {
				f.createNewFile();
				InputStream is = new BufferedInputStream(Thread.currentThread().getContextClassLoader().getResourceAsStream("a3b/climate/resources/REGISTRAZIONE.INI"));
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));
				bw.write(new String(is.readAllBytes()));
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

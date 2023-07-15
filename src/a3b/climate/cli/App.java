package a3b.climate.cli;

import a3b.climate.utils.terminal.Screen;
import org.apache.commons.cli.CommandLine;

public class App {
	public static Screen scn = new Screen();
	public static CommandLine line;
	public static final String programName = "unilab";
	public static void start(CommandLine line) {
		App.line = line;
		if (line.hasOption("avvia-registrazione")) {
			scn.show(new Registrazione());
		}
	}
}

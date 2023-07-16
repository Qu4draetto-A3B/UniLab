package a3b.climate.cli;

import a3b.climate.Main;
import a3b.climate.utils.terminal.Screen;

import org.apache.commons.cli.CommandLine;

public class App {
	public static Screen scn = new Screen();
	public static CommandLine line;
	public static final String programName = "Climate Monitoring";
	public static final String programExec = "unilab";


	
	public static void start(CommandLine line) {
		App.line = line;
		if (line.hasOption("avvia-registrazione")) {
			scn.show(new Registrazione());
		} else if (line.hasOption("query")) {
			scn.show(new Query());
		} else if (line.hasOption("lista-aree")) {
			scn.show(new ComandoAree());
		} else if (line.hasOption("lista-centri")) {
			scn.show(new MostraCentri());
		} else if (line.hasOption("lista-misurazioni")) {
			scn.show(new MostraMisurazioni());
		} else if (line.hasOption("lista-operatori")) {
			scn.show(new ComandoOperatori());
		} else if (line.hasOption("crea-centro")) {
			scn.show(new ComandoCentri());
		} else if (line.hasOption("crea-misurazione")) {
			scn.show(new ComandoMisurazioni());
		} else {
			Main.printHelp();
		}
	}
}

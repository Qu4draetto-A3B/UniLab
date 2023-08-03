package a3b.climate.cli;

import a3b.climate.Main;
import a3b.climate.utils.terminal.Screen;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

import org.apache.commons.cli.CommandLine;

public class App {
	public static Screen scn = new Screen();
	public static CommandLine line;
	public static final Random rng = new Random(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));// Long.parseLong(getProperty("LastRID").get());
	public static final String programName = "Climate Monitoring";
	public static final String programExec = "unilab";

	public static void start(CommandLine line) {
		App.line = line;
		if (line.hasOption("avvia-registrazione")) {
			scn.show(new Registrazione());
		} else if (line.hasOption("utente")) {
			scn.show(new MostraUtente());
		} else if (line.hasOption("lista-aree")) {
			scn.show(new MostraAree());
		} else if (line.hasOption("lista-centri")) {
			scn.show(new MostraCentri());
		} else if (line.hasOption("lista-misurazioni")) {
			scn.show(new MostraMisurazioni());
		} else if (line.hasOption("crea-centro")) {
			scn.show(new ComandoCentri());
		} else if (line.hasOption("crea-misurazione")) {
			scn.show(new ComandoMisurazioni());
		} else {
			Main.printHelp();
		}
	}
}

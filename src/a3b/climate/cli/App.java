package a3b.climate.cli;

import a3b.climate.Main;
import a3b.climate.utils.terminal.Screen;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

import org.apache.commons.cli.CommandLine;

/**
 * La classe {@code App} &egrave; la classe principale dell'applicazione <i>
 * Climate Monitoring</i>.
 * <p>
 * &egrave; il punto di partenza dell'applicazione e contiene un metodo che
 * permette di avviare diversi comandi in base ai parametri forniti.
 */
public class App {
	public static Screen scn = new Screen();
	public static CommandLine line;
	public static final Random rng = new Random(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));// Long.parseLong(getProperty("LastRID").get());
	public static final String programName = "Climate Monitoring";
	public static final String programExec = "unilab";

	/**
	 * Recupera i parametri forniti dall'applicazione e avvia il comando
	 * appropriato al suo interno.
	 *
	 * @param line argomenti della riga di comando da elaborare
	 */
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

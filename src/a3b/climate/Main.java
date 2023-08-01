/*
 * Interdisciplinary Workshop A
 * Climate Monitoring
 * A.A. 2022-2023
 *
 * Authors:
 * - Iuri Antico, 753144
 * - Beatrice Balzarini, 752257
 * - Michael Bernasconi, 752259
 * - Gabriele Borgia, 753262
 *
 * Some rights reserved.
 * See LICENSE file for additional information.
 */
package a3b.climate;

import a3b.climate.cli.App;
import org.apache.commons.cli.*;

/**
 * Interdisciplinary Workshop A <br>
 * Climate Monitoring <br>
 * A.A. 2022-2023
 *
 * @author Iuri Antico, 753144
 * @author Beatrice Balzarini, 752257
 * @author Michael Bernasconi, 752259
 * @author Gabriele Borgia, 753262
 */
public class Main {
	public static CommandLine line;
	private static Options opts;

	public static void main(String[] args) {
		opts = new Options();
		opts.addOption(Option.builder("u")
				.argName("utente")
				.longOpt("utente")
				.desc("Nome utente e password, separati da spazio. Mostra le generalita' dell'utente")
				.hasArg()
				.numberOfArgs(2)
				.optionalArg(false)
				.build());

		opts.addOption(Option.builder()
				.argName("avvia")
				.longOpt("avvia-registrazione")
				.desc("Avvia il processo di registrazione")
				.build());

		opts.addOption(Option.builder()
				.argName("parole")
				.longOpt("lista-aree")
				.desc("Avvia un'operazione di ricerca sulle aree geografiche")
				.hasArgs()
				.valueSeparator(',')
				.optionalArg(true)
				.build());

		opts.addOption(Option.builder()
				.argName("parole")
				.longOpt("lista-centri")
				.desc("Avvia un'operazione di ricerca sui centri di monitoraggio")
				.hasArgs()
				.valueSeparator(',')
				.optionalArg(true)
				.build());

		opts.addOption(Option.builder()
				.argName("parole")
				.longOpt("lista-misurazioni")
				.desc("Avvia un'operazione di ricerca sui centri di monitoraggio")
				.hasArgs()
				.valueSeparator(',')
				.optionalArg(true)
				.build());

		opts.addOption(Option.builder()
				.argName("avvia")
				.longOpt("crea-centro")
				.desc("Avvia la creazione di un centro")
				.build());

		opts.addOption(Option.builder()
				.argName("avvia")
				.longOpt("crea-misurazione")
				.desc("Avvia la creazione di una misurazione")
				.build());

		if (args.length < 1) {
			printHelp();
			return;
		}

		DefaultParser par = new DefaultParser();
		try {
			line = par.parse(opts, args);
		} catch (ParseException e) {
			System.out.printf("%s\n", e.getMessage());
			return;
		}

		App.start(line);
	}

	public static void printHelp() {
		new HelpFormatter().printHelp(
				App.programName,
				"Programma per il monitoraggio climatico",
				opts,
				"Leggi il Manuale Utente per informazioni aggiuntive",
				false);

	}
}

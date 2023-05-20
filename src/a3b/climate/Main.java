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
import a3b.climate.utils.terminal.Screen;
import org.apache.commons.cli.*;

import javax.swing.text.html.parser.Parser;

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
	public static Screen scn = new Screen();
	public static CommandLine line;

	public static void main(String[] args) {
		Options opts = new Options();
		opts.addOption(Option.builder("u")
			.argName("utente")
			.longOpt("utente")
			.desc("Nome utente e password, separati da virgola")
			.required(true)
			.valueSeparator(',')
			.numberOfArgs(2)
			.optionalArg(false)
			.build());

		opts.addOption(Option.builder("g")
			.argName("gettone utente")
			.longOpt("gettone")
			.desc("File con nome utente e password nel formato 'utente,password'")
			.required(true)
			.hasArg()
			.optionalArg(false)
			.build());

		opts.addOption(Option.builder()
			.argName("avvia")
			.longOpt("avvia-registrazione")
			.desc("Avvia il processo di registrazione")
			.build());

		opts.addOption(Option.builder("q")
			.argName("avvia")
			.longOpt("query")
			.desc("Avvia un'operazione di ricerca dati")
			.hasArg()
			.optionalArg(true)
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
			.argName("parole")
			.longOpt("lista-operatori")
			.desc("Avvia un'operazione di ricerca sui centri di monitoraggio")
			.hasArgs()
			.valueSeparator(',')
			.optionalArg(true)
			.build());

		opts.addOption(Option.builder()
			.argName("avvia")
			.longOpt("crea-centro")
			.desc("Avvia la creazione di un centro")
			.hasArg()
			.optionalArg(true)
			.build());

		opts.addOption(Option.builder()
			.argName("avvia")
			.longOpt("crea-misurazione")
			.desc("Avvia la creazione di una misurazione")
			.hasArg()
			.optionalArg(true)
			.build());

		if (args.length < 1) {
			new HelpFormatter().printHelp(
				"Climate Monitoring",
				"Programma per il monitoraggio climatico",
				opts,
				"Leggi il Manuale Utente per informazioni aggiuntive",
				false);
			return;
		}

		DefaultParser par = new DefaultParser();
		try {
			line = par.parse(opts, args);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			return;
		}

		App.start(line);
	}
}

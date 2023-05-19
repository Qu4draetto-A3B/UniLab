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

	public static void main(String[] args) throws Exception {
		Options opts = new Options();
		opts.addOption(Option.builder("u")
			.argName("utente")
			.longOpt("utente")
			.desc("Nome utente e password, separati da virgola")
			.valueSeparator(',')
			.numberOfArgs(2)
			.optionalArg(false)
			.build());

		opts.addOption(Option.builder("g")
			.argName("gettone")
			.longOpt("gettone")
			.desc("File con nome utente e password nel formato 'utente,password'")
			.hasArg()
			.optionalArg(false)
			.build());

		if (args.length < 1) {
			new HelpFormatter().printHelp(
				"Climate Monitoring",
				"Programma per il monitoraggio climatico",
				opts,
				"Leggi il Manuale Utente per informazioni aggiuntive",
				true);
		}

		DefaultParser par = new DefaultParser();
		line = par.parse(opts, args);
	}
}

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

package a3b.climate.cli;

import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

/**
 * Modella messaggi d'aiuto per l'utente
 */
public class Help implements View {

	/**
	 * Messaggio d'aiuto da stampare a schermo
	 */
	private static final String HELP_MSG = "Scrivere messaggio d'aiuto\n";

	@Override
	public void start(Terminal term) {
		term.printfln(HELP_MSG);
	}
}

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
package a3b.climate.utils.terminal;

/**
 * Modella l'interfaccia nel terminale
 */
public class Screen {
	protected Terminal term;

	/**
	 * Costruttore di un'istanza di Screen
	 */
	public Screen() {
		term = new Terminal();
	}

	/**
	 * Metodo che mostra
	 *
	 * @param v
	 */
	public void show(View v) {
		term.clear();
		v.start(term);
		term.clear();
	}
}

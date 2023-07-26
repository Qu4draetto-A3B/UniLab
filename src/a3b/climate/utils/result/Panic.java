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
package a3b.climate.utils.result;

/**
 * Classe che rappresenta un errore
 */
public class Panic extends Error {
	/**
	 * Costruttore di un'istanza di Panic
	 *
	 * @param msg Messaggio per l'utente che si vuole stampare a schermo
	 */
	public Panic(String msg) {
		super(msg);
	}

	/**
	 * Costruttore di un'istanza di Panic
	 *
	 * @param t Oggetto che può essere lanciato dalla JVM
	 */
	public Panic(Throwable t) {
		super(t);
	}
}

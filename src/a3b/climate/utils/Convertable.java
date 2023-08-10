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
package a3b.climate.utils;

/**
 * L'interfaccia {@link Convertable} si occupa di convertire il formato degli oggetti.
 */
public interface Convertable {

	/**
	 * Rappresenta l'oggetto in formato CSV.
	 */

	public String toCsv();

	/**
	 * Rappresenta l'oggetto in formato JSON.
	 */

	public String toJson();
}

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
public interface Convertable {

	/**
	 * Rappresentazione in formato CSV dell'oggetto
	 */

	public String toCsv();

	/**
	 * Rappresentazione in formato Json dell'oggetto
	 */

	public String toJson();
}

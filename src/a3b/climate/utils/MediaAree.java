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

import a3b.climate.magazzeno.AreaGeografica;
import a3b.climate.magazzeno.DatoGeografico;

/**
 * Interfaccia che si occupa di calcolare la media dei dati
 */
public interface MediaAree {

	/**
	 * Esegue la moda dei dati acquisiti in una certa area geografica
	 *
	 * @param area Area di interesse
	 * @return Moda delle misurazioni
	 */
	public DatoGeografico visualizzaAreaGeografica(AreaGeografica area);
}

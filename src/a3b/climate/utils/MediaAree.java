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
 * L'interfaccia {@link MediaAree} si occupa di calcolare la media dei dati di una {@link a3b.climate.magazzeno.AreaGeografica}.
 */
public interface MediaAree {

	public DatoGeografico visualizzaAreaGeografica(AreaGeografica area);
}

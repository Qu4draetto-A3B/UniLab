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
package a3b.climate.gestori;

/**
 * La classe {@code DataBase} si occupa di creare per ogni classe <i>Gestore</i>
 * un'istanza in grado di gestire i vari tipi di dati.
 */
public class DataBase {
	public static GestoreMisurazioni misurazioni = new GestoreMisurazioni();
	public static GestoreOperatore operatore = new GestoreOperatore();
	public static GestoreCentro centro = new GestoreCentro();
	public static GestoreArea area = new GestoreArea();
	public static GestoreDato dato = new GestoreDato();
}

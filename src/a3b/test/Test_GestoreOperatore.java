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
package a3b.test;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.Operatore;

public class Test_GestoreOperatore {
	public static void main(String[] args) {
		Operatore op = DataBase.operatore.login("Civile", "civile").get();
		System.out.println(op.toCsv());
	}
}

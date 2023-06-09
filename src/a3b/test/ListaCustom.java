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
import a3b.climate.magazzeno.AreaGeografica;
import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Indirizzo;
import a3b.climate.magazzeno.ListaAree;

public class ListaCustom {
	public static void main(String[] args) {

		ListaAree lista = new ListaAree();
		AreaGeografica ag1 = new AreaGeografica(0, -20, -12, "Italia", "Na");
		AreaGeografica ag2 = new AreaGeografica(0, 21, -70, "Italia", "Milano");
		AreaGeografica ag3 = new AreaGeografica(0, 42, 130, "italia", "Bergamo");

		lista.addFirst(ag1);
		lista.addFirst(ag2);
		lista.addFirst(ag3);

		// lista = lista.cercaAreaGeografica(null, "romania");
		// lista.addFirst(lista.cercaAreeGeografiche(21, -190).get());

		for (AreaGeografica tmp : lista) {
			System.out.println(tmp.getDenominazione());
		}
		/// ListaAree lista = ss.getListaAree();
		int i = 0;
		for (AreaGeografica tmp : lista)
			System.out.println("" + ++i + " " + tmp.toString());

		Indirizzo str = new Indirizzo("fascista", 12, 12345, "Milano", "Mussolini");
		CentroMonitoraggio cm = new CentroMonitoraggio("Milano", str, lista);

		DataBase.centro.addCentro(cm);

	}
}

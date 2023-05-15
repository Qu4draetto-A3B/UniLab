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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.AreaGeografica;
import a3b.climate.magazzeno.DatoGeografico;
import a3b.climate.magazzeno.Filtratore;
import a3b.climate.magazzeno.Misurazione;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.TipoDatoGeografico;

public class Test_Filtratore {
	public static void main(String[] args) {
		List<Misurazione> list = new ArrayList<Misurazione>();
		Random rng = new Random();
		Operatore op = new Operatore();
		AreaGeografica ag = DataBase.area.cercaAreaGeografica("Uras", "Italy").getFirst();
		for (int i = 0; i < 11; i++) {
			HashMap<TipoDatoGeografico, Byte> datiMap = new HashMap<>();
			for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
				datiMap.put(tipo, (byte) rng.nextInt(6));
			}
			DatoGeografico dato = new DatoGeografico(0, datiMap, null);
			list.add(new Misurazione(0, dato, op, ag));
		}

		System.out.printf("%s\n%s\n", op.toCsv(), ag);

		list.forEach(System.out::println);

		Filtratore fil = new Filtratore(list);

		System.out.println(fil);
		System.out.println(fil.visualizzaAreaGeografica(ag));
	}
}

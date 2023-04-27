package a3b.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import a3b.climate.gestori.GestoreArea;
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
		AreaGeografica ag = GestoreArea.cercaAreaGeografica("Uras", "Italy").getFirst();
		for (int i = 0; i < 11; i++) {
			HashMap<TipoDatoGeografico, Byte> datiMap = new HashMap<>();
			for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
				datiMap.put(tipo, (byte) rng.nextInt(6));
			}
			DatoGeografico dato = new DatoGeografico(datiMap, null);
			list.add(new Misurazione(dato, op, ag));
		}

		System.out.printf("%s\n%s\n", op.toCsv(), ag);

		list.forEach(System.out::println);

		Filtratore fil = new Filtratore(list);

		System.out.println(fil);
		System.out.println(fil.visualizzaAreaGeografica(ag));
	}
}

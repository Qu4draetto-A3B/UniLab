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
package a3b.climate.magazzeno;

import java.util.NoSuchElementException;

import a3b.climate.utils.CercaAree;
import a3b.climate.utils.Convertable;
import a3b.climate.utils.ListaCustom.*;
import a3b.climate.utils.result.Result;

import java.util.Iterator;

public class ListaAree implements Iterable<AreaGeografica>, CercaAree, Convertable {

	Nodo<AreaGeografica> head;
	Nodo<AreaGeografica> tail;

	/**
	 * @return Restituisce un booleano che indica se la lista di aree geografiche
	 *         che esegue il metodo e' vuota
	 */

	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * @return Restituisce l'area geografica che si trova in posizione k nella
	 *         ListaAree che esegue il metodo
	 * @param k Intero che indica l'indice di un elemento all'interno di ListaAree
	 */

	public AreaGeografica get(int k) {
		if (0 > k || k >= this.size())
			throw new IndexOutOfBoundsException("ERRORE : valore dell'indice non valido");
		Nodo<AreaGeografica> current = head;
		for (int i = 0; i < k; i++)
			current = current.getNext();

		return current.getDato();

	}

	/**
	 * Metodo che aggiunge un'area geografica alla ListaAree nell'indice k
	 *
	 * @param e Area geografica da aggiungere alla ListaAree che esegue il metodo
	 * @param k Intero che indica l'indice in cui inserire l'area geografica
	 */

	public void add(AreaGeografica e, int k) {
		if ((0 > k) || (k >= this.size()))
			throw new IndexOutOfBoundsException("ERRORE: valore dell'indice non valido");
		Nodo<AreaGeografica> current = head;
		for (int i = 0; i < k - 1; i++) {
			current = current.getNext();
		}
		Nodo<AreaGeografica> x = new Nodo<AreaGeografica>(e, current.getNext());
		current.setNext(x);
	}

	/**
	 * @return Restituisce il primo elemento nella ListaAree che esegue il metodo
	 */

	public AreaGeografica getFirst() {
		if (head == null)
			throw new NoSuchElementException("La listra e' vuota");
		return head.getDato();
	}

	/**
	 * @return Restituisce l'ultimo elemento nella ListaAree che esegue il metodo
	 */

	public AreaGeografica getLast() {
		if (head == null)
			throw new NoSuchElementException("La lista e' vuota");
		return tail.getDato();
	}

	/**
	 * @return Restituisce un intero che indica la dimensione della ListaAree che
	 *         esegue il metodo
	 */

	public int size() {
		int i = 0;
		Nodo<AreaGeografica> current = head;
		while (current != null) {
			i++;
			current = current.getNext();
		}
		return i;
	}

	/**
	 * Metodo che aggiunge un'area geografica alla ListaAree in prima posizione
	 *
	 * @param e Area geografica da aggiungere alla ListaAree che esegue il metodo
	 */

	public void addFirst(AreaGeografica e) {
		Nodo<AreaGeografica> x = new Nodo<AreaGeografica>(e, head);
		head = x;
		if (tail == null)
			tail = x;

	}

	/**
	 * //TODO
	 */

	public Iterator<AreaGeografica> iterator() {
		return new CollezioniIterator<AreaGeografica>(head);
	}





	@Override

	public ListaAree cercaAreaGeografica(String denominazione, String stato) {

		ListaAree la = new ListaAree();
		ListaAree lt = this;

		if ((denominazione != null) && (denominazione != "")) {
			for (AreaGeografica areaGeografica : lt) {
				if (areaGeografica.getDenominazione().toLowerCase().contains(denominazione.toLowerCase()))
					la.addFirst(areaGeografica);
			}

			lt = la;
			la = null;
		}

		if ((stato != null) && (stato != "")) {
			for (AreaGeografica areaGeografica : lt) {
				if (areaGeografica.getStato().toLowerCase().contains(stato.toLowerCase()))
					la.addFirst(areaGeografica);
			}

			lt = la;
			la = null;
		}

		return lt;
	}

	@Override

	public Result<AreaGeografica> cercaAreeGeografiche(double latitudine, double longitudine) {

		if ((latitudine < -90) || (latitudine > 90)) {
			return new Result<>(1, "hai inserito valori errati riprova");
		}
		if ((longitudine < -180) || (longitudine > 180)) {
			return new Result<>(1, "hai inserito valori errati riprova");
		}

		for (AreaGeografica areaGeografica : this) {
			if ((latitudine == areaGeografica.getLatitudine()) && (longitudine == areaGeografica.getLongitudine()))
				return new Result<>(areaGeografica);
		}

		AreaGeografica ag = this.getFirst();
		double differenzalat = latitudine - ag.getLatitudine();
		differenzalat *= differenzalat;

		double differenzalong = longitudine - ag.getLongitudine();
		differenzalong *= differenzalong;

		double min = Math.sqrt(differenzalat + differenzalong);
		for (AreaGeografica areaGeografica : this) {

			differenzalat = latitudine - areaGeografica.getLatitudine();
			differenzalat *= differenzalat;

			differenzalong = longitudine - areaGeografica.getLongitudine();
			differenzalong *= differenzalong;

			double dist = Math.sqrt(differenzalat + differenzalong);

			if (min > dist) {
				min = dist;
				ag = areaGeografica;
			}

		}
		return new Result<>(ag);

	}

	@Override
	public String toString() {
		String str = "";
		for (AreaGeografica tmp : this)
			str += tmp.toString() + "\n";
		return str;
	}

	@Override
	public String toCsv() {
		String str = "";
		for (AreaGeografica ag : this) {
			str += ag.getLatitudine() + ":" + ag.getLongitudine() + ";";
		}

		str = str.substring(0, str.length() - 1);
		return str;

	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toJson'");
	}

}

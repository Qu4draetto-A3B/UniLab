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
package a3b.climate.utils.ListaCustom;

import java.util.Iterator;

/**
 *
 */
public class CollezioniIterator<E> implements Iterator<E> {
	Nodo<E> current;

	/**
	 * Costruttore di un'istanza di CollezioniIterator
	 *
	 * @param head Riferimento alla nodo in testa alla lista
	 */
	public CollezioniIterator(Nodo<E> head) {
		current = head;
	}

	/**
	 * @return Booleano che indica se un nodo della lista che chiama il metodo ha un
	 *         succesivo
	 */
	public boolean hasNext() {
		return current != null;
	}

	/**
	 * @return Riferimento al nodo successivo nella lista che chiama il metodo
	 */
	public E next() {
		E e = current.getDato();
		current = current.getNext();

		return e;
	}

}

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

/**
 * Rappresenta un nodo contenente un elemento E
 */
public class Nodo<E>{
    private E dato;
    private Nodo<E> next;

    /**
	 * Costruttore di un'istanza di Nodo<E>
	 * @param dato Elemento da inserire nel nodo
	 * @param next Riferimento al nodo successivo
	 */
    public Nodo(E dato, Nodo<E> next){
        this.next = next;
        this.dato = dato;
    }

    /**
	 * Metodo che imposta il successivo di un nodo
	 * @param next Riferimento al nodo da impostare come successivo del nodo che chiama il metodo
	 */
    public void setNext(Nodo<E> next){this.next = next;}

	/**
	 * Metodo che imposta un dato
	 * @param dato Dato da impostare nel nodo che chiama il metodo
	 */
    public void setDato(E dato){this.dato = dato;}

	/**
	 * @return Riferimento al successivo del nodo che chiama il metodo
	 */
    public Nodo<E> getNext(){return next;}

	/**
	 * @return Dato relativo al nodo che chiama il metodo
	 */
    public E getDato(){return dato;}
}

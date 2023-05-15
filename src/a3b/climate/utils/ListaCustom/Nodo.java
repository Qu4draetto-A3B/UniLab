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

public class Nodo<E>{
    //CAMPI
    private E dato;
    private Nodo<E> next;

    //COSTRUTTORI
    public Nodo(E dato, Nodo<E> next){
        this.next = next;
        this.dato = dato;
    }

    //METODI
    public void setNext(Nodo<E> next){this.next = next;}
    public void setDato(E dato){this.dato = dato;}
    public Nodo<E> getNext(){return next;}
    public E getDato(){return dato;}
}

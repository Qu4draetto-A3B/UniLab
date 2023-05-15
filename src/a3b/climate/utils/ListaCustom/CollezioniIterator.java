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

public class CollezioniIterator<E> implements Iterator<E>{
    Nodo<E> current;

    public CollezioniIterator(Nodo<E> head){ current = head;}

    public boolean hasNext(){ return current != null;}

    public E next(){
        E e = current.getDato();
        current = current.getNext();

        return e;
    }

}

package Collezioni;
import java.util.Iterator;

class CollezioniIterator<E> implements Iterator<E>{
    Nodo<E> current;

    public CollezioniIterator(Nodo<E> head){ current = head;}

    public boolean hasNext(){ return current != null;}
    
    public E next(){
        E e = current.getDato();
        current = current.getNext();

        return e;
    } 

}

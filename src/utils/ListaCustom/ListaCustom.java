package Collezioni;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class ListaCustom<E> implements Iterable<E> {

    Nodo<E> head;
    Nodo<E> tail;

    public boolean isEmpty(){
        return head == null;
    }

    public E get(int k){
        if(0>k||k>= this.size()) throw new IndexOutOfBoundsException("ERRORE : valore dell'indice non valido");
        Nodo<E> current = head;
        for(int i = 0;i<k;i++)
            current = current.getNext();

        return current.getDato();
        
    }
    public E getFirst(){
        if(head == null) throw new NoSuchElementException("La listra e' vuota");
        return head.getDato();
    }

    public E getLast(){
        if(head == null) throw new NoSuchElementException("La lista e' vuota");
        return tail.getDato();
    }

    public int size(){
        int i = 0;
        Nodo<E> current = head;
        while(current != null){
            i++;
            current = current.getNext();
        }
        return i;
    }

    public void addLast(E e){
        Nodo<E> x = new Nodo<E>(e,null);
        if(head == null) head = tail = x;
        else{
            tail.setNext(x);
            tail = x;
        }
    }

    public void addFirst(E e){
        Nodo<E> x = new Nodo<E>(e,head);
        head = x;
        if(tail == null)tail = x;
        
    }

    public void add(E e, int k){
        if(0>k||k>= this.size()) throw new IndexOutOfBoundsException("ERRORE : valore dell'indice non valido");
        Nodo<E> current = head;
        for(int i = 0;i<k-1;i++)
            current = current.getNext();
        Nodo<E> x = new Nodo<E>(e,current.getNext());
        current.setNext(x);
    }


    public Iterator<E> iterator(){
        return new CollezioniIterator<E>(head);
    }

}
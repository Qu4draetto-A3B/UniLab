/*
+ visualizzaAreaGeografica(area: AreaGeografica):DatoGeografico // Aggregato
    1. una volta trovata l'area di interesse, deve essere possibile visualizzare tutte le informazioni relative ad essa

    2. tra le informazioni deve esserci un prospetto riassuntivo dei parametri climatici associati a quell'area 
       o l’indicazione che l'area non contiene dati inseriti dagli operatori. 
       Se presenti, si potranno visualizzare tali dati in forma aggregata (ad esempio, numero di rilevazioni per ciascun parametro e statistica opportuna del punteggio), 
       oltre agli eventuali commenti lasciati dagli operatori



+ cercaAreaGeografica(denom: String, stato: String): List<AreaGeografica> // Nullable
    1. ricerca per denominazione (prende in input una stringa di caratteri e restituisce le aree nel cui nome compare la stringa di caratteri) 
       e per Stato di appartenenza



+ cercaAreeGeografiche(lat: double, lon: double): List<AreaGeografica> // Nullable
    1. ricerca per coordinate geografiche (prende in input una latitudine e longitudine 
       e restituisce il nome dell'area corrispondente alle coordinate geografiche/delle aree corrispondenti con coordinate più vicine.
       Per latitudine e longitudine cercare area limitrofa se operatore sbaglia a inserirlo
*/

import java.util.NoSuchElementException;
import utils.ListaCustom.*;

import java.util.Iterator;

public class ListaAree implements Iterable<AreaGeografica> {

    Nodo<AreaGeografica> head;
    Nodo<AreaGeografica> tail;

    public boolean isEmpty(){
        return head == null;
    }

    public AreaGeografica get(int k){
        if(0>k||k>= this.size()) throw new IndexOutOfBoundsException("ERRORE : valore dell'indice non valido");
        Nodo<AreaGeografica> current = head;
        for(int i = 0;i<k;i++)
            current = current.getNext();

        return current.getDato();
        
    }
    public AreaGeografica getFirst(){
        if(head == null) throw new NoSuchElementException("La listra e' vuota");
        return head.getDato();
    }

    public AreaGeografica getLast(){
        if(head == null) throw new NoSuchElementException("La lista e' vuota");
        return tail.getDato();
    }

    public int size(){
        int i = 0;
        Nodo<AreaGeografica> current = head;
        while(current != null){
            i++;
            current = current.getNext();
        }
        return i;
    }

    public void addLast(AreaGeografica e){
        Nodo<AreaGeografica> x = new Nodo<AreaGeografica>(e,null);
        if(head == null) head = tail = x;
        else{
            tail.setNext(x);
            tail = x;
        }
    }

    public void addFirst(AreaGeografica e){
        Nodo<AreaGeografica> x = new Nodo<AreaGeografica>(e,head);
        head = x;
        if(tail == null)tail = x;
        
    }

    public void add(AreaGeografica e, int k){
        if(0>k||k>= this.size()) throw new IndexOutOfBoundsException("ERRORE : valore dell'indice non valido");
        Nodo<AreaGeografica> current = head;
        for(int i = 0;i<k-1;i++)
            current = current.getNext();
        Nodo<AreaGeografica> x = new Nodo<AreaGeografica>(e,current.getNext());
        current.setNext(x);
    }


    public Iterator<AreaGeografica> iterator(){
        return new CollezioniIterator<AreaGeografica>(head);
    }

}
import java.util.NoSuchElementException;
import utils.ListaCustom.*;
import java.lang.Math.*;

import java.util.Iterator;

public class ListaAree implements Iterable<AreaGeografica>, CercaAree {

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

    public void addFirst(AreaGeografica e){
        Nodo<AreaGeografica> x = new Nodo<AreaGeografica>(e,head);
        head = x;
        if(tail == null)tail = x;
        
    }


    public Iterator<AreaGeografica> iterator(){
        return new CollezioniIterator<AreaGeografica>(head);
    }

    public DatoGeografico visualizzaAreaGeografica (AreaGeografica area){
        /*
        + visualizzaAreaGeografica(area: AreaGeografica):DatoGeografico // Aggregato
        1. una volta trovata l'area di interesse, deve essere possibile visualizzare tutte le informazioni relative ad essa

        2. tra le informazioni deve esserci un prospetto riassuntivo dei parametri climatici associati a quell'area 
        o l’indicazione che l'area non contiene dati inseriti dagli operatori. 
        Se presenti, si potranno visualizzare tali dati in forma aggregata (ad esempio, numero di rilevazioni per ciascun parametro e statistica opportuna del punteggio), 
        oltre agli eventuali commenti lasciati dagli operatori
        */


        return null;
    }

    public ListaAree cercaAreaGeografica (String denominazione, String stato){
        /*
        + cercaAreaGeografica(denom: String, stato: String): List<AreaGeografica> // Nullable
        1. ricerca per denominazione (prende in input una stringa di caratteri e restituisce le aree nel cui nome compare la stringa di caratteri) 
        e per Stato di appartenenza
        */
    
        ListaAree la = new ListaAree();
        ListaAree lt = this;
        
        if((denominazione != null) && (denominazione !="")){
            for (AreaGeografica areaGeografica : lt) {
                if(areaGeografica.getDenominazione().toLowerCase().contains(denominazione.toLowerCase())) 
                la.addFirst(areaGeografica);
            }

            lt = la;
            la = null;
        }
        
        if((stato != null) && (stato !="")){
            for (AreaGeografica areaGeografica : lt) {
                if(areaGeografica.getStato().toLowerCase().contains(stato.toLowerCase())) 
                la.addFirst(areaGeografica);
            }

            lt = la;
            la = null;
        }

        return lt;
    }


    public AreaGeografica cercaAreeGeografiche (double latitudine, double longitudine){
        /* 
        + cercaAreeGeografiche(lat: double, lon: double): List<AreaGeografica> // Nullable
        1. ricerca per coordinate geografiche (prende in input una latitudine e longitudine 
        e restituisce il nome dell'area corrispondente alle coordinate geografiche/delle aree corrispondenti con coordinate più vicine.
        Per latitudine e longitudine cercare area limitrofa se operatore sbaglia a inserirlo
        */

        if((latitudine < -90) || (latitudine > 90)) {
            System.out.println("hai inserito valori errati riprova:");
            return null; // CHIEDERE NEL MAIN NUOVO INPUT (while)
        }
        if((longitudine < -180) || (longitudine > 180)) {
            System.out.println("hai inserito valori errati riprova:");
            return null; // CHIEDERE NEL MAIN NUOVO INPUT (while)
        }

        for (AreaGeografica areaGeografica :this) {
            if(latitudine == areaGeografica.getLatitudine()&& (longitudine == areaGeografica.getLongitudine())) return areaGeografica;    
        }

        AreaGeografica ag = this.getFirst();
        double differenzalat = latitudine - ag.getLatitudine();
        differenzalat *= differenzalat;

        double differenzalong = longitudine - ag.getLongitudine();
        differenzalong *= differenzalong;

        double min = Math.sqrt(differenzalat + differenzalong);
        for (AreaGeografica areaGeografica :this) {

            differenzalat = latitudine - areaGeografica.getLatitudine();
            differenzalat *= differenzalat;

            differenzalong = longitudine - areaGeografica.getLongitudine();
            differenzalong *= differenzalong;


            double dist = Math.sqrt(differenzalat + differenzalong);

            if(min > dist){
                min = dist;
                ag = areaGeografica;
            }

        }
        return ag;

    }

}
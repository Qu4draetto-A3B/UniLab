import utils.result.Result;

/*
CercaAree
<<Interface>>

// no attr
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

public interface CercaAree
{

    public DatoGeografico visualizzaAreaGeografica (AreaGeografica area);

    public ListaAree cercaAreaGeografica (String denominazione, String stato);

    public Result<AreaGeografica> cercaAreeGeografiche (double latitudine, double longitudine);
}

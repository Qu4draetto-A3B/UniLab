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
package a3b.climate.utils;
import a3b.climate.magazzeno.AreaGeografica;
import a3b.climate.magazzeno.ListaAree;
import a3b.climate.utils.result.Result;

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

*/

public interface CercaAree
{
    
    /**
	 * Metodo che ricerca un'area geografica a partire dalla sua denominazione e dal suo stato di appartenenza
	 * @return Restituisce le aree nel cui nome compare la stringa di caratteri
	 * @param denominazione Nome relativo a un'area geografica
	 * @param stato Stato di appartenenza di un'area geografica
	 */
    public ListaAree cercaAreaGeografica (String denominazione, String stato);

    /**
	 * Metodo che ricerca delle aree geografiche a partire da coordinate geografiche
	 * @return Restituisce il nome dell'area corrispondente alle cordinate geografiche corrispondenti o delle aree geografiche corrispondenti con coordinate più vicine
	 * @param latitudine Latitudine di una coordinata geografica
	 * @param longitudine Longitudine di una coordinata geografica
	 */
    
    public Result<AreaGeografica> cercaAreeGeografiche (double latitudine, double longitudine);
}
    
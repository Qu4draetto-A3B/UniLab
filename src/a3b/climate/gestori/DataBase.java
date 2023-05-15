package a3b.climate.gestori;
/**
 * Si occupa di creare (per ogni classe "Gestore") un oggetto in grado di richiamare le funzione associate.
 */
public class DataBase {
	public static GestoreMisurazioni misurazioni = new GestoreMisurazioni();
	public static GestoreOperatore operatore = new GestoreOperatore();
	public static GestoreCentro centro = new GestoreCentro();
	public static GestoreArea area = new GestoreArea();
	public static GestoreDato dato = new GestoreDato();
}

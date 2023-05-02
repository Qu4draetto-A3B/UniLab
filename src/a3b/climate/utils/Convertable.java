package a3b.climate.utils;
public interface Convertable {

	/**
	 * Rappresentazione in formato CSV dell'oggetto
	 */

	public String toCsv();

	/**
	 * Rappresentazione in formato Json dell'oggetto
	 */

	public String toJson();
}

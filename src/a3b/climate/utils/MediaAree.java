package a3b.climate.utils;

import a3b.climate.magazzeno.AreaGeografica;
import a3b.climate.magazzeno.DatoGeografico;

public interface MediaAree {

	/**
	 * Esegue la moda dei dati acquisiti in una certa area geografica
	 * @param area L'area di interesse
	 * @return La moda delle misurazioni
	 */
	public DatoGeografico visualizzaAreaGeografica (AreaGeografica area);
}

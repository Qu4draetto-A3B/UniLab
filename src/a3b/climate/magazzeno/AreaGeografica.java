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
package a3b.climate.magazzeno;

import a3b.climate.utils.DataTable;

/**
 * Classe che rappresenta un'area geografica
 */

public class AreaGeografica implements DataTable {
	private long geoID;
	private double latitudine;
	private double longitudine;
	private String stato;
	private String denominazione;

	/**
	 * Costruttore di un'istanza di AreaGeografica
	 *
	 * @param geoID         Id dell'area geografica
	 * @param latitudine    Latitudine relativa all'area geografica
	 * @param longitudine   Longitudine relativa all'area geografica
	 * @param stato         Stato in cui si trova l'area geografica
	 * @param denominazione Nome dell'area geografica
	 */

	public AreaGeografica(long geoID, double latitudine, double longitudine, String stato, String denominazione) {
		this.geoID = geoID;
		this.latitudine = latitudine;
		this.longitudine = longitudine;
		this.stato = stato;
		this.denominazione = denominazione;
	}

	/**
	 * @return GeoID relativo all'area geografica che chiama il metodo
	 */
	public long getGeoID() {
		return geoID;
	}

	/**
	 * @return Latitudine relativa all'area geografica che chiama il metodo
	 */
	public double getLatitudine() {
		return latitudine;
	}

	/**
	 * @return Logitudine relativa all'area geografica che chiama il metodo
	 */
	public double getLongitudine() {
		return longitudine;
	}

	/**
	 * @return Stato in cui si trova l'area geografica che chiama il metodo
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * @return Nome dell'area geografica che chiama il metodo
	 */
	public String getDenominazione() {
		return denominazione;
	}

	@Override
	public String toString() {
		return String.format("[%d] %s (%s) LAT:%f LON:%f", this.geoID, this.denominazione, this.stato, this.latitudine,
				this.longitudine);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AreaGeografica)) {
			return super.equals(obj);
		}

		AreaGeografica ag = (AreaGeografica) obj;

		if (this.geoID == ag.getGeoID())
			return true;
		else
			return false;
	}
}

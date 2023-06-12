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

import a3b.climate.utils.Convertable;

/**
 * Rappresenta un indirizzo
 */

public class Indirizzo implements Convertable {

	private String nomeVia;
	private int civico;
	private int cap;
	private String comune;
	private String provincia;

	/**
	 * Costruttore vuoto di un'istanza di Indirizzo
	 */

	public Indirizzo() {
		nomeVia = "Via Durin I";
		civico = 42;
		cap = 12345;
		comune = "Westfalia";
		provincia = "Norrenia";
	}

	/**
	 * Costruttore di un'istanza di Indirizzo
	 * @param nomeVia Nome della via relativa all'indirizzo
     * @param civico Numero civico relativo all'indirizzo
	 * @param cap Codice di avviamento postale relativo all'indirizzo
	 * @param comune Comune relativo all'indirizzo
	 * @param provincia Provincia relativa all'indirizzo
	 */

	public Indirizzo(String nomeVia, int civico, int cap, String comune, String provincia) {
		if (cap >= 100000)
			throw new IllegalArgumentException("CAP invalido");
		this.nomeVia = nomeVia;
		this.civico = civico;
		this.cap = cap;
		this.comune = comune;
		this.provincia = provincia;
	}

	/**
	 * @return Restituisce il nome della via relativo all'indirizzo che chiama il metodo
	 */

	public String getNomeVia() {
		return nomeVia;
	}

	/**
	 * @return Restituisce il numero civico relativo all'indirizzo che chiama il metodo
	 */

	public int getCivico() {
		return civico;
	}

	/**
	 * @return Restituisce il codice di avviamento postale relativo all'indirizzo che chiama il metodo
	 */

	public int getCap() {
		return cap;
	}

	/**
	 * @return Restituisce il comune relativo all'indirizzo che chiama il metodo
	 */
	public String getComune() {
		return comune;
	}

	public String getProvincia() {
		return provincia;
	}

	@Override
	public String toString() {
		return String.format(
				"%s: (\n\tnomeVia: %s\n\tCivico: %d\n\tCap: %d\n\tComune: %s\n\tProvincia: %s\n)",
				super.toString(), nomeVia, civico, cap, comune, provincia);
	}

	@Override
	public String toCsv() {
		return nomeVia + ":" + civico + ":" + cap + ":" + comune + ":" + provincia;
	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toJson'");
	}
}

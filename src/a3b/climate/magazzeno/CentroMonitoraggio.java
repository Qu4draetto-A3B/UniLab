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
import a3b.climate.utils.DataTable;

/**
 * Rappresenta un centro di monitoraggio
 */

public class CentroMonitoraggio implements Convertable, DataTable {
	private String nome;
	private Indirizzo indirizzo;
	private ListaAree aree;

	/**
	 * Costruttore di un'istanza di CentroMonitoraggio
	 * @param nome Nome del centro di monitoraggio
     * @param indirizzo Indirizzo del centro di monitoraggio
	 * @param lag Lista di aree geografiche relative al centro di monitoraggio
	 */

	public CentroMonitoraggio(String nome, Indirizzo indirizzo, ListaAree lag) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.aree = lag;
	}

	/**
	 * Costruttore vuoto di un'istanza di CentroMonitoraggio
	 */

	public CentroMonitoraggio() {
		nome = "Torre Civile";
		indirizzo = new Indirizzo();
		aree = new ListaAree();
	}

	/**
	 * @return Nome del centro di monitoraggio che chiama il metodo
	 */

	public String getNome() {
		return nome;
	}

	/**
	 * @return Lista delle aree relative al centro di monitoraggio che chiama il metodo
	 */

	public ListaAree getListaAree() {
		return this.aree;
	}

	/**
	 * @return Indirizzo del centro di monitoraggio che chiama il metodo
	 */

	public Indirizzo getIndirizzo() {
		return indirizzo;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(String.format(
				"%s: (\n\tNome: %s\n\tIndirizzo: %s, %d, %d, %s (%s)",
				super.toString(), nome,
				indirizzo.getNomeVia(), indirizzo.getCivico(),
				indirizzo.getCap(), indirizzo.getComune(), indirizzo.getProvincia()));

		for (AreaGeografica area : aree) {
			sb.append(String.format("\n\t\t%s", area));
		}
		sb.append("\n)");

		return sb.toString();
	}


	@Override
	public String toCsv() {
		return nome + "," + indirizzo.toCsv() + "," + aree.toCsv();

	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toJson'");
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof CentroMonitoraggio)) {
			return super.equals(obj);
		}

		CentroMonitoraggio cm = (CentroMonitoraggio) obj;

		if (nome.equals(cm.getNome()))
			return true;

		return false;
	}
}

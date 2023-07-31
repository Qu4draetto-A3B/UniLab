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
package a3b.climate.cli;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.Filtratore;
import a3b.climate.magazzeno.Misurazione;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

/**
 * La classe {@code MostraMisurazioni} implementa l'interfaccia {@link View} per
 * visualizzare le misurazioni in base a criteri forniti da un filtratore.
 * <p>
 * Legge il {@link Filtratore} dalla linea di comando e recupera le istanze di {@link Misurazione} corrispondenti dal database, le quali vengono
 * stampate nel terminale.
 */
public class MostraMisurazioni implements View {
	public MostraMisurazioni() {
		super();
	}

	/**
	 * Recupera le misurazioni in base ai ciriteri dati nel filtratore fornito nella
	 * linea di comando.
	 * <p>
	 * Richiede al database le istanze di {@link Misurazione} corrispondenti e le
	 * stampa nel terminale.
	 *
	 * @param term istanza di {@link Terminal} utilizzata per stampare le
	 *             misurazioni
	 */
	@Override
	public void start(Terminal term) {
		String[] args = App.line.getOptionValues("lista-misurazioni");
		if (args == null) {
			args = new String[]{""};
		}
		Filtratore fil = DataBase.misurazioni.getMisurazioni().get();
		fil = fil.filtraStrings(args);
		term.printfln("%s", fil);
	}
}

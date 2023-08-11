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
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

public class MostraMisurazioni implements View {
	public MostraMisurazioni() {
		super();
	}

	/**
	 * Recupera le misurazioni in base ai ciriteri dati nel filtratore fornito nella
	 * linea di comando.
	 * <p>
	 * Richiede al database le istanze di {@link a3b.climate.magazzeno.Misurazione} corrispondenti e le
	 * stampa nel terminale.
	 *
	 * @param term istanza di {@link Terminal} utilizzata per stampare le
	 *             misurazioni
	 */
	@Override
	public void start(Terminal term) {
		String[] args = App.line.getOptionValues("lista-misurazioni");
		Filtratore fil = DataBase.misurazioni.getMisurazioni().get();
		if (args == null) {
			term.printfln("%s", fil);
			return;
		}
		fil = fil.filtraStrings(args);
		term.printfln("%s", fil);
	}
}

package a3b.climate.cli;

import java.util.Deque;
import java.util.LinkedList;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

/**
 * La classe {@code MostraCentri} implementa l'interfaccia {@link View} per
 * visualizzare centri di monitoraggio in base al relativo nome.
 * <p>
 * Legge il nome dalla linea di comando e recupera l'istanza di
 * {@link CentroMonitoraggio} corrispondente dal database, la quale viene
 * stampata nel terminale.
 */
public class MostraCentri implements View {

	/**
	 * Recupera il centro di monitoraggio in base al nome fornito nella linea di
	 * comando.
	 * <p>
	 * Richiede al database l'istanza di {@link CentroMonitoraggio}
	 * corrispondente e la stampa nel terminale.
	 *
	 * @param term istanza di {@link Terminal} utilizzata per stampare il centro di
	 *             monitoraggio
	 */
	@Override
	public void start(Terminal term) {
		String[] args = App.line.getOptionValues("lista-centri");
		Deque<CentroMonitoraggio> dcm = new LinkedList<>();

		for (String str : args) {
			DataBase.centro.getCentro(str)
					.ifValid((v, e) -> dcm.push(v));
		}

		for (CentroMonitoraggio cm : dcm) {
			term.printfln("%s", cm);
		}
	}
}

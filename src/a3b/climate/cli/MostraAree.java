package a3b.climate.cli;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.AreaGeografica;
import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

/**
 * La classe {@code MostraAree} implementa l'interfaccia {@link View} per
 * visualizzare aree geografiche in base alle relative coordinate o ai relativi
 * ID.
 * <p>
 * Legge le coordinate e gli ID dalla linea di comando e recupera le istanze di
 * {@link AreaGeografica} corrispondenti dal database, le quali vengono
 * stampate nel terminale.
 */
public class MostraAree implements View {
	/**
	 * Recupera le aree geografiche in base alle coordinate o
	 * agli ID forniti nella linea di comando.
	 * <p>
	 * Richiede al database le istanze di {@link AreaGeografica}
	 * corrispondenti e le stampa nel terminale.
	 *
	 * @param term istanza di {@link Terminal} utilizzata per stampare le aree
	 *             geografiche
	 */
	public void start(Terminal term) {
		String[] preArgs = App.line.getOptionValues("lista-aree");
		List<String> args = Arrays.asList(preArgs == null ? new String[]{""} : preArgs);
		Deque<double[]> coords = new LinkedList<double[]>();
		Deque<Long> geoids = new LinkedList<Long>();
		Deque<String> terms = new LinkedList<>();

		for (String str : args) {
			if (str.contains(":")) {
				String[] coord = str.split(":");
				if (coord.length == 2) {
					coords.offer(new double[] { Double.parseDouble(coord[0]), Double.parseDouble(coord[1]) });
				}
			} else {
				try {
					geoids.offer(Long.parseLong(str));
				} catch (Exception e) {
					terms.offer(str);
				}
			}
		}

		Deque<AreaGeografica> dag = new LinkedList<>();

		for (long g : geoids) {
			DataBase.area.getArea(g)
				.ifValid((v, e) -> dag.offer(v));
		}

		for (double[] c : coords) {
			DataBase.area.cercaAreeGeografiche(c[0], c[1])
				.ifValid((v, e) -> dag.offer(v));
		}

		for (String t : terms) {
			for (AreaGeografica ag : DataBase.area.cercaAreaGeografica(t, "")) {
				dag.offer(ag);
			}
		}

		for (AreaGeografica ag : dag) {
			term.printfln("%s", ag);
		}
	}
}

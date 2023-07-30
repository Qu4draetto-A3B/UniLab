package a3b.climate.cli;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.AreaGeografica;
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
		List<String> args = Arrays.asList(App.line.getOptionValues("lista-aree"));
		Deque<double[]> coords = new LinkedList<double[]>();
		Deque<Long> geoids = new LinkedList<Long>();

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
					// pass;
				}
			}
		}

		Deque<AreaGeografica> dag = new LinkedList<>();

		for (int i = 0; i < Math.max(coords.size(), geoids.size()); i++) {
			double[] c = coords.poll();
			long g = geoids.poll();
			DataBase.area.cercaAreeGeografiche(c[0], c[1])
					.ifValid((v, e) -> dag.offer(v));

			DataBase.area.getArea(g)
					.ifValid((v, e) -> dag.offer(v));
		}

		for (AreaGeografica ag : dag) {
			term.printfln("%s", ag);
		}
	}
}

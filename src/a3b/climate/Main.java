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
package a3b.climate;

import java.util.Optional;

import a3b.climate.cli.Help;
import a3b.climate.cli.MainMenu;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.terminal.Screen;
import a3b.climate.utils.terminal.View;

/**
 * Interdisciplinary Workshop A <br>
 * Climate Monitoring <br>
 * A.A. 2022-2023
 *
 * @author Iuri Antico, 753144
 * @author Beatrice Balzarini, 752257
 * @author Michael Bernasconi, 752259
 * @author Gabriele Borgia, 753262
 */
public class Main {
	public static Screen scn = new Screen();
	private static View menu = new MainMenu();

	/**
	 * Session cookie for the logged in operator
	 */
	public static Optional<Operatore> oper = Optional.empty();

	public static void main(String[] args) throws Exception {
		if (System.console() == null) {
			System.out.println("Error: System.console() is null");
			stop();
		}
		if (args.length > 0 && (args[0].equals("-h") || args[0].equals("--help"))) {
			scn.show(new Help());
			return;
		}
		menu();
	}

	public static void menu() {
		scn.show(menu);
	}

	public static void stop() {
		System.out.println("Uscendo...");
		System.exit(0);
	}
}

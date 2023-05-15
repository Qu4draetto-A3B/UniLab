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

import a3b.climate.Main;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Main application menu
 */
public class MainMenu implements View {
	public MainMenu() {
		super();
	}

	@Override
	public void start(Terminal term) {
		String menu = "Comandi: \nQ: Esci\nR: Registrazione\nL: Login\nC: Cerca le misurazioni";
		String in;
		char c;

		while (true) {
			Main.oper.ifPresent(operatore -> term.printfln("Benvenuto %s", operatore.getNome()));

			in = term.readLineOrDefault(".", menu);

			c = in.toLowerCase().charAt(0);

			term.clear();

			basic(c);
		}
	}

	private boolean basic(char c) {
		switch (c) {
			case '.':
				break;

			case 'q':
				Main.stop();
				break;

			case 'r':
				Main.scn.show(new Registrazione());
				break;

			case 'l':
				Main.scn.show(new Login());
				break;

			case 'c':
				Main.scn.show(new MostraMisurazioni());
				break;

			default:
				return true;
		}
		return false;
	}
}

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
import java.util.Optional;
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
		StringBuilder menu = new StringBuilder();
		String in;
		char c;
		boolean error;

		while (true) {
			if (Main.oper.isPresent()) {
				menu.append(String.format("Benvenuto %s\n", Main.oper.get().getNome()));
				menu.append("Comandi: \nQ: Esci\nP: Profilo\nL: Logout\nC: Cerca le misurazioni\nI: Inserisci misurazione");
			} else {
				menu.append("Comandi: \nQ: Esci\nR: Registrazione\nL: Login\nC: Cerca le misurazioni");
			}

			in = term.readLineOrDefault(".", menu.toString());

			c = in.toLowerCase().charAt(0);

			menu.delete(0, menu.length());
			term.clear();

			if (Main.oper.isPresent()) error = user(c);
			else error = basic(c);

			if (error) menu.append("[!!] Comando Errato\n");
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

	private boolean user(char c) {
		switch (c) {
			case '.':
				break;

			case 'q':
				Main.stop();
				break;

			case 'p':
				Main.scn.show(new Profile());
				break;

			case 'l':
				Main.oper = Optional.empty();
				break;

			case 'c':
				Main.scn.show(new MostraMisurazioni());
				break;

			case 'i':
				//TODO
				break;

			default:
				return true;
		}
		return false;
	}
}

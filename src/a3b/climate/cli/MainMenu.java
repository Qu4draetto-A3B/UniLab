package a3b.climate.cli;

import a3b.climate.Main;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * App:
 * - Schermata iniziale
 *
 * Civile:
 * - Guardare e basta (fare query di ricerca)
 *
 * Operatore:
 * - Creare centro di monitoraggio (poi rimane incastonateo nella pietra)
 * - Aggiungere misurazioni
 * - Vedere il proprio profilo
 */
public class MainMenu implements View {
	public MainMenu() {
		super();
	}

	@Override
	public void start(Terminal term) {
		while (true) {
			Main.oper.ifPresent(operatore -> term.printfln("Benvenuto %s", operatore.getNome()));

			String menu = "Comandi: \nQ: Esci\nR: Registrazione\nL: Login\nC: Cerca le misurazioni";

			String str = term.readLineOrDefault(".", menu);

			char c = str.toLowerCase().charAt(0);

			term.clear();

			switch (c) {
				case '.':
					break;

				case 'q':
					term.printf("Uscendo...\n");
					return;

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
					term.printfln("[!!] Comando Errato");
					break;
			}
		}
	}
}

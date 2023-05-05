package a3b.climate.cli;

import a3b.climate.Main;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

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
		if (Main.oper.isPresent()) {
			term.printfln("Benvenuto %s", Main.oper.get().getNome());
		}

        term.printf("Comandi: \nQ: Esci\nR: Registrazione\nL: Login\nC: Cerca le misurazioni\n");

        while (true) {
            String str = term.readLine();

            if (str.length() < 1) {
                str = ".";
            }

            char c = str.toLowerCase().charAt(0);

            switch (c) {
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
                    term.clear();
                    term.printf("Comandi: \nQ: Esci\nR: Registrazione\nL: Login\nC: Cerca le misurazioni\n");
                    continue;
            }
        }
    }
}

package a3b.climate.cli;

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
public class MainMenu extends View {

    public MainMenu() {
        super();
    }

    @Override
    public void start() {
        term.clear();

        term.printf("Comandi: \nQ: Esci\nR: Registrazione\nL: Login\nC: Cerca le misurazioni\n");

        while (true) {
            String str = term.readLine().toLowerCase();

            if (str.length() < 1) {
                str = ".";
            }

            char c = str.charAt(0);

            switch (c) {
                case 'q':
                    term.printf("Uscendo...\n");
                    return;
                
                case 'r':
                    new Registrazione().start();
                    break;
            
                case 'l':
                    new Login().start();
                    break;

                case 'c':
                    new MostraMisurazioni().start();
                    break;

                default:
                    term.clear();
                    term.printf("Comandi: \nQ: Esci\nR: Registrazione\nL: Login\nC: Cerca le misurazioni\n");
                    continue;
            }
        }
    }
}
package a3b.climate.cli;

import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

/**
 * La classe {@code Registrazione} implementa l'interfaccia {@link View} per
 * eseguire la registrazione di un {@link Operatore} al sistema..
 */
public class Registrazione implements View {

	/**
	 * Permette a un {@link Operatore} di effetturare la registrazione al
	 * sistema tramite terminale.
	 */
	@Override
	public void start(Terminal term) {
		term.printfln("Registrazione non implementata");
	}
}

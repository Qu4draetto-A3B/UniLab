package a3b.climate.cli;

import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

public class Help implements View {
	private static final String HELP_MSG =
		"Scrivere messaggio d'aiuto\n";
	@Override
	public void start(Terminal term) {
		term.printfln(HELP_MSG);
	}
}

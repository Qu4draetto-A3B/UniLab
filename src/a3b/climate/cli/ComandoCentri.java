package a3b.climate.cli;

import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

/**
 * ComandoCentri
 */
public class ComandoCentri implements View {

	@Override
	public void start(Terminal term) {
		String path = App.line.getOptionValue("query");
		if (path.isBlank())
			path = "./CENTRO.INI";
	}
}

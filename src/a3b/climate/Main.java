package a3b.climate;

import java.util.Optional;

import a3b.climate.cli.Help;
import a3b.climate.cli.MainMenu;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.terminal.Screen;

public class Main {
	public static Screen scn = new Screen();
	public static Optional<Operatore> oper = Optional.empty();

	public static void main(String[] args) throws Exception {
		if (args.length > 0 && (args[0].equals("-h") || args[0].equals("--help"))) {
			scn.show(new Help());
			return;
		}
		scn.show(new MainMenu());
	}

	public static void stop() {
		System.exit(0);
	}
}

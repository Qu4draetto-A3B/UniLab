package a3b.climate.cli;

import java.util.Optional;

import a3b.climate.Main;
import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.result.Result;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

public class Login implements View {
	public Login() {
		super();
	}

	@Override
	public void start(Terminal term) {
		Result<Operatore> rop;

		while (true) {
			String user = term.readWhile((str) -> str.length() < 6, "Inserire il nome utente");

			String pwd = term.readPasswordWhile((str) -> str.length() < 9, "Inserire la password");

			rop = DataBase.operatore.login(user, pwd);

			if (!rop.isValid()) {
				term.printfln("Errore %d: %s", rop.getError(), rop.getMessage());
				String out = term.readLineOrDefault("y", "Vuoi riprovare? [Y/n]").toLowerCase();
				if (out.equals("y")) {
					continue;
				} else {
					return;
				}
			}

			break;
		}

		Main.oper = Optional.of(rop.get());
	}
}

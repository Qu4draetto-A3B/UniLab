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

import java.util.Optional;

import a3b.climate.Main;
import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.result.Result;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

/**
 * Classe che
 */
public class Login implements View {
	/**
	 * Costruttore di un'istanza di Login
	 */
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

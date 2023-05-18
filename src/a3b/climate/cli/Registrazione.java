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
import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.result.Result;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

import java.util.Optional;

/**
 * Permette all'utente di registrarsi
 */
public class Registrazione implements View {
	public Registrazione() {
		super();
	}

	@Override
	public void start(Terminal term) {
		boolean keep = true;
		while (keep) {
			String cf = term.readWhile(String::isBlank, "Inserisci codice fiscale").trim();
			String nome = term.readWhile(String::isBlank, "Inserisci il tuo nome").trim();
			String cognome = term.readWhile(String::isBlank, "Inserisci il tuo cognome").trim();
			String uid = term.readWhile(String::isBlank, "Inserisci User ID").trim();
			String email = term.readWhile(String::isBlank, "Inserisci email").trim();
			String nomeCentro = term.readWhile(String::isBlank, "Inserisci il nome del tuo centro").trim();
			String pwd = term.readPasswordWhile(s -> s.length() < 8, "Inserisci password");

			Result<CentroMonitoraggio> rcm = DataBase.centro.getCentro(nomeCentro);

			if (rcm.isError()) {
				if (term.promptUser(true, "Centro non trovato, vuoi riprovare?")) {
					continue;
				}
			}

			Result<Operatore> rop = DataBase.operatore.registrazione(new Operatore(cf, uid, nome, cognome, email, rcm.get()), pwd);

			if (rop.isError()) {
				term.printfln("Errore %d: %s", rop.getError(), rop.getMessage());
				if (term.promptUser(true, "Vuoi riprovare?")) {
					continue;
				}
			}

			Main.oper = Optional.of(rop.get());
		}
	}
}

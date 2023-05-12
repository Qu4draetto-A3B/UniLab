package a3b.climate.cli;

import java.util.Optional;

import a3b.climate.Main;
import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.result.Result;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

public class Registrazione implements View {
    public Registrazione() {
        super();
    }

    @Override
    public void start(Terminal term) {
        String cf = term.readLine("Inserisci codice fiscale");
        String nome = term.readLine("Inserisci nome");
        String cognome = term.readLine("Inserisci cognome");
        String uid = term.readLine("Inserisci ID");
        String email = term.readLine("Inserisci email");
        String nomeCentro = term.readLine("inserisci nome centro di monitoraggio");

		String pwd = term.readPasswordWhile((s) -> s.length() < 8, "Inserisci password");

        Result<CentroMonitoraggio> rcm = DataBase.centro.getCentro(nomeCentro);

        if (!rcm.isValid()) {
            term.printf("Non funge");
        }

        Result<Operatore> rop = DataBase.operatore.registrazione(new Operatore(cf, uid, nome, cognome, email, rcm.get()), pwd);

        if (!rop.isValid()) {
            term.printf("Errore %d: %s", rop.getError(), rop.getMessage());
        }

        Main.oper = Optional.of(rop.get());
    }
}

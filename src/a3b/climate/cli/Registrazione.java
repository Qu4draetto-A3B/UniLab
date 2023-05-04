package a3b.climate.cli;

import java.util.Optional;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.result.Result;

public class Registrazione extends View {
    public Registrazione() {
        super();
    }

    @Override
    public void start() {
        term.clear();

        String cf = term.readLine("Inserisci codice fiscale");
        String nome = term.readLine("Inserisci nome");
        String cognome = term.readLine("Inserisci cognome");
        String uid = term.readLine("Inserisci ID");

        String email = term.readLine("Inserisci email");
        String pwd = term.readLine("Inserisci password");

        String nomeCentro = term.readLine("inserisci nome centro di monitoraggio");
        Result<CentroMonitoraggio> rcm = DataBase.centro.getCentro(nomeCentro);

        if (!rcm.isValid()) {
            term.printf("Non funge");
        }

        Result<Operatore> rop = DataBase.operatore.registrazione(new Operatore(cf, uid, nome, cognome, email, rcm.get()), pwd);

        if (!rop.isValid()) {
            term.printf("Errore n.%d: %s", rop.getError(), rop.getMessage());
        }

        op = Optional.of(rop.get());
    }
}
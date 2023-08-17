package a3b.climate.cli;

import java.io.IOException;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.result.Result;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

/**
 * La classe {@code MostraUtente} implementa l'interfaccia {@link View} per
 * visualizzare profili utente in base ai relativi UserID e password.
 * <p>
 * Legge il UID e password dalla linea di comando e recupera l'istanza di
 * {@link Operatore} corrispondente dal database, la quale viene
 * stampata nel terminale.
 */
public class MostraUtente implements View {

	/**
	 * Recupera il profilo utente in base a UserID e password forniti nella linea di
	 * comando.
	 * <p>
	 * Richiede al database l'istanza di {@link Operatore}
	 * corrispondente e la stampa nel terminale.
	 *
	 * @param term istanza di {@link Terminal} utilizzata per stampare l'utente
	 */
	@Override
	public void start(Terminal term) throws IOException {
		String[] args = App.line.getOptionValues("utente");

		Result<Operatore> rop = DataBase.operatore.login(args[0], args[1]);

		if (rop.isError()) {
			term.printfln("%s", rop.getMessage());
			return;
		}

		Operatore op = rop.get();

		term.printfln("%s", op);
	}

}

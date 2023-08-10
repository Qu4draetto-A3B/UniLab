package a3b.climate.cli;

import java.io.IOException;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.result.Result;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

public class MostraUtente implements View {

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

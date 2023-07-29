package a3b.climate.cli;

import java.util.Deque;
import java.util.LinkedList;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

public class MostraCentri implements View {

	@Override
	public void start(Terminal term) {
		String[] args = App.line.getOptionValues("lista-centri");

		if (args == null) {
			args = new String[]{""};
		}

		Deque<CentroMonitoraggio> dcm = new LinkedList<>();

		for (String str : args) {
			DataBase.centro.getCentro(str)
				.ifValid((v, e) -> dcm.push(v));
		}

		for (CentroMonitoraggio cm : dcm) {
			term.printfln("%s", cm);
		}
	}
}

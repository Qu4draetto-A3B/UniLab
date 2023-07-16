package a3b.climate.cli;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

public class MostraCentri implements View {

	@Override
	public void start(Terminal term) {
		List<String> args = Arrays.asList(App.line.getOptionValues("lista-centri"));
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

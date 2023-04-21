package a3b.test;

import a3b.climate.gestori.GestoreOperatore;
import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.Operatore;

public class Test_GestoreOperatore {
	public static void main(String[] args) {
		GestoreOperatore.start();

		Operatore op = GestoreOperatore.login("Civile", "civile").get();
		System.out.println(op.toCsv());

		GestoreOperatore.stop();
	}
}

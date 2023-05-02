package a3b.test;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.Operatore;

public class Test_GestoreOperatore {
	public static void main(String[] args) {
		Operatore op = DataBase.operatore.login("Civile", "civile").get();
		System.out.println(op.toCsv());
	}
}

package a3b.climate.magazzeno;
import java.time.LocalDateTime;

import a3b.climate.gestori.DataBase;
import a3b.climate.utils.Convertable;
import a3b.climate.utils.DataTable;
import a3b.climate.utils.result.Result;

public class Operatore implements Convertable, DataTable {
	private String nome, cognome, email, cf, uid;
	private CentroMonitoraggio centro;

	public Operatore(String cf, String uid, String nome, String cognome, String email, CentroMonitoraggio centro) {
		this.cf = cf;
		this.uid = uid;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.centro = centro;
	}

	public Operatore() {
		cf = "CIVILE";
		uid = "civile";
		nome = "Civile";
		cognome = "Civile";
		email = "civile@example.com";
		centro = new CentroMonitoraggio();
	}

	public Result<Object> inserisciParametri(AreaGeografica area, DatoGeografico dato, LocalDateTime tempo) {
		Misurazione mis = new Misurazione(0, tempo, this, centro, area, dato);
		return DataBase.misurazioni.addMisurazione(mis);
	}

	public String getCf() {
		return cf;
	}

	public CentroMonitoraggio getCentro() {
		return centro;
	}

	public String getCognome() {
		return cognome;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getUid() {
		return uid;
	}

	@Override
	public String toString() {
		String str = String.format(
			"%s: (\n\tCF: %s\n\tUserID: %s\n\tNome: %s\n\tCognome: %s\n\tEmail: %s\n\tCentro: %s\n)",
			super.toString(), cf, uid, nome, cognome, email, centro.getNome());

		return str;
	}

	@Override
	public String toCsv() {
		String csv = String.format("%s,%s,%s,%s,%s,%s", cf, uid, nome, cognome, email, centro.getNome());
		return csv;
	}

	@Override
	public String toJson() {
		//
		return "";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Operatore)) {
			return super.equals(obj);
		}

		Operatore op = (Operatore) obj;

		if(cf.equals(op.getCf()))
			return true;

		return false;
	}
}

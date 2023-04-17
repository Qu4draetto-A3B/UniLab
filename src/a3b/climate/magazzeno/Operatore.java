package a3b.climate.magazzeno;
import java.time.LocalDateTime;

import a3b.climate.utils.Convertable;

public class Operatore implements Convertable {
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

	public boolean inserisciParametri(AreaGeografica area, DatoGeografico dato, LocalDateTime tempo) {
		// TODO
		return false;
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

	public void setCentro(CentroMonitoraggio centro) {
		this.centro = centro;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toCsv() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toCsv'");
	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toJson'");
	}
}

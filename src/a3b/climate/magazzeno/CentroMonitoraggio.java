package a3b.climate.magazzeno;

import a3b.climate.utils.Convertable;

public class CentroMonitoraggio implements Convertable {
	// CAMPI
	private String nome;
	private Indirizzo indirizzo;
	private ListaAree aree;

	// COSTRUTTORE
	public CentroMonitoraggio(String nome, Indirizzo indirizzo, ListaAree lag) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.aree = lag;
	}

	public CentroMonitoraggio() {
		nome = "Torre Civile";
		indirizzo = new Indirizzo();
		aree = new ListaAree();
	}

	// METODO
	public String getNome() {
		return nome;
	}

	public ListaAree getListaAree() {
		return this.aree;
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

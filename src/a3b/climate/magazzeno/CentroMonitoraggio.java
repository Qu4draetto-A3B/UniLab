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

	public Indirizzo getIndirizzo() {
		return indirizzo;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(String.format(
				"%s: (\n\tNome: %s\n\tIndirizzo: %s, %d, %d, %s (%s)",
				super.toString(), nome,
				indirizzo.getNomeVia(), indirizzo.getCivico(),
				indirizzo.getCap(), indirizzo.getComune(), indirizzo.getProvincia()));

		for (AreaGeografica area : aree) {
			sb.append(String.format("\n\t\t%s", area));
		}
		sb.append("\n)");

		return sb.toString();
	}

	@Override
	public String toCsv() {
		return nome + "," + indirizzo.toCsv() + "," + aree.toCsv();

	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toJson'");
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof CentroMonitoraggio)) {
			return super.equals(obj);
		}

		CentroMonitoraggio cm = (CentroMonitoraggio) obj;

		if (nome.equals(cm.getNome()))
			return true;

		return false;
	}
}

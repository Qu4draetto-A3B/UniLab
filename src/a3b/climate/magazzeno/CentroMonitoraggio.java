package a3b.climate.magazzeno;
public class CentroMonitoraggio {
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
	public ListaAree getListaAree() {
		return this.aree;
	}
}

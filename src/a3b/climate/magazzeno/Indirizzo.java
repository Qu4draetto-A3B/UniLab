package a3b.climate.magazzeno;

import a3b.climate.utils.Convertable;

public class Indirizzo implements Convertable {

	private String nomeVia;
	private int civico;
	private int cap;
	private String comune;
	private String provincia;

	public Indirizzo() {
		nomeVia = "Via Durin I";
		civico = 42;
		cap = 12345;
		comune = "Westfalia";
		provincia = "Norrenia";
	}

	public Indirizzo(String nomeVia, int civico, int cap, String comune, String provincia) {
		if(cap >= 100000)
			throw new IllegalArgumentException("CAP invalido");
		this.nomeVia = nomeVia;
		this.civico = civico;
		this.cap = cap;
		this.comune = comune;
		this.provincia = provincia;
	}

	public String getNomeVia() {
		return nomeVia;
	}

	public void setNomevia(String nomeVia) {
		this.nomeVia = nomeVia;
	}

	public int getCivico() {
		return civico;
	}

	public void setCivico(int civico) {
		this.civico = civico;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap)
	{
		this.cap = cap;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}



	public String toCsv()
	{
		return nomeVia+ "|"+ civico+ "|"+cap+ "|" +comune+ "|" +provincia;
	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toJson'");
	}
}

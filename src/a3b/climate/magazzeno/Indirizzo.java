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
		if (cap >= 100000)
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

	public int getCivico() {
		return civico;
	}

	public int getCap() {
		return cap;
	}

	public String getComune() {
		return comune;
	}

	public String getProvincia() {
		return provincia;
	}

	@Override
	public String toString() {
		return String.format(
				"%s: (\n\tnomeVia: %s\n\tCivico: %d\n\tCap: %d\n\tComune: %s\n\tProvincia: %s\n)",
				super.toString(), nomeVia, civico, cap, comune, provincia);
	}

	public String toCsv() {
		return nomeVia + "|" + civico + "|" + cap + "|" + comune + "|" + provincia;
	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toJson'");
	}
}

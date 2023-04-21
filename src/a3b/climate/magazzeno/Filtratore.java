package a3b.climate.magazzeno;

import java.util.List;

import a3b.climate.utils.result.Result;

public class Filtratore implements CercaAree{
	private List<Misurazione> lm;

	public Filtratore(List<Misurazione> lm) {
		this.lm = lm;
	}

	public Filtratore filtraOperatore(Operatore... op) {
		return null;
	}

	public Filtratore filtraCentro(CentroMonitoraggio... cm) {
		return null;
	}

	public Filtratore filtraAree(AreaGeografica... ag) {
		return null;
	}

	public Filtratore filtraNote(String... nota) {
		return null;
	}

	public Filtratore filtraDato(DatoGeografico... dato) {
		return null;
	}

	@Override
	public DatoGeografico visualizzaAreaGeografica(AreaGeografica area) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'visualizzaAreaGeografica'");
	}

	@Override
	public ListaAree cercaAreaGeografica(String denominazione, String stato) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cercaAreaGeografica'");
	}

	@Override
	public Result<AreaGeografica> cercaAreeGeografiche(double latitudine, double longitudine) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cercaAreeGeografiche'");
	}
}

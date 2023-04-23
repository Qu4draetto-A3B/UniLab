package a3b.climate.magazzeno;

import java.util.LinkedList;
import java.util.List;

import a3b.climate.utils.CercaAree;
import a3b.climate.utils.DataTable;
import a3b.climate.utils.MediaAree;
import a3b.climate.utils.TipoDatoGeografico;
import a3b.climate.utils.result.Result;

public class Filtratore implements CercaAree, MediaAree {
	private List<Misurazione> lm;

	public Filtratore(List<Misurazione> lm) {
		this.lm = lm;
	}

	public Filtratore filtra(DataTable dts) {
		return null;
	}

	public Filtratore filtraOperatore(Operatore... ops) {
		List<Misurazione> nlm = new LinkedList<>();
		for (Misurazione mis : lm) {
			for (Operatore op : ops) {
				if (mis.getOperatore().equals(op)) {
					nlm.add(mis);
					break;
				}
			}
		}

		return new Filtratore(nlm);
	}

	public Filtratore filtraCentro(CentroMonitoraggio... cms) {
		List<Misurazione> nlm = new LinkedList<>();
		for (Misurazione mis : lm) {
			for (CentroMonitoraggio cm : cms) {
				if (mis.getCentro().equals(cm)) {
					nlm.add(mis);
					break;
				}
			}

		}
		return new Filtratore(nlm);
	}

	public Filtratore filtraAree(AreaGeografica... ags) {
		List<Misurazione> nlm = new LinkedList<>();
		for (Misurazione mis : lm) {
			for (AreaGeografica ag : ags) {
				if (mis.getArea().equals(ag)) {
					nlm.add(mis);
					break;
				}
			}

		}
		return new Filtratore(nlm);
	}

	public Filtratore filtraNote(String... note) {
		List<Misurazione> nlm = new LinkedList<>();
		for (Misurazione mis : lm) {
			for (String nota : note) {
				for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
					if (mis.getDato().getNota(tipo).equals(nota)) {
						nlm.add(mis);
						break;
					}
				}
			}

		}
		return new Filtratore(nlm);
	}

	public Filtratore filtraDato(DatoGeografico... dati) {
		List<Misurazione> nlm = new LinkedList<>();
		for (Misurazione mis : lm) {
			for (DatoGeografico dat : dati) {
				if (mis.getDato().equals(dat)) {
					nlm.add(mis);
					break;
				}
			}

		}
		return new Filtratore(nlm);
	}



	@Override
	public DatoGeografico visualizzaAreaGeografica(AreaGeografica area) {

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

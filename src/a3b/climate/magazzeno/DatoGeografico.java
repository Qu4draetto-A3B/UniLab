package a3b.climate.magazzeno;

import java.util.HashMap;
import java.util.Map;

import a3b.climate.utils.DataTable;
import a3b.climate.utils.TipoDatoGeografico;

/**
 * Rappresenta un dato geografico
 */
public class DatoGeografico implements DataTable {
	private long rid;
	private byte massaGhiacciai;
	private byte altitudineGhiacciai;
	private byte precipitazioni;
	private byte temperatura;
	private byte pressione;
	private byte umidita;
	private byte vento;

	private HashMap<TipoDatoGeografico, String> note;

	public DatoGeografico(long rid, TipoDatoGeografico tipo, byte dato, String nota) {
		this.rid = rid;
		setDato(tipo, dato);

		note = new HashMap<>();
		for (TipoDatoGeografico t : TipoDatoGeografico.values()) {
			note.put(t, "");
		}

		if (!setNota(tipo, nota))
			throw new IllegalArgumentException("Nota troppo lunga");
	}

	public DatoGeografico(long rid, byte massaGhiacciai, byte altitudineGhiacciai, byte precipitazioni, byte temperatura,
			byte pressione, byte umidita, byte vento, HashMap<TipoDatoGeografico, String> note) {
		/*
		 * I byte vengono inizializzati a 0 implicitamente,
		 * se tutti i dati sono 0 anche la loro somma sara' 0,
		 * quindi posso dedurre che non sono stati inseriti dati
		 */
		int all = massaGhiacciai + altitudineGhiacciai + precipitazioni + temperatura + pressione + umidita + vento;
		if (all < 1 || all > 30) {
			throw new IllegalArgumentException("Almeno un dato deve essere > 0 in DatoGeografico");
		}
		this.rid = rid;

		for (String nota : note.values()) {
			if (nota.length() > 255) {
				throw new IllegalArgumentException("Nota troppo lunga");
			}
		}

		this.massaGhiacciai = massaGhiacciai;
		this.altitudineGhiacciai = altitudineGhiacciai;
		this.precipitazioni = precipitazioni;
		this.temperatura = temperatura;
		this.pressione = pressione;
		this.umidita = umidita;
		this.vento = vento;
		this.note = note;
	}

	public DatoGeografico(long rid, Map<TipoDatoGeografico, Byte> dati, Map<TipoDatoGeografico, String> note) {
		this.rid = rid;
		this.note = new HashMap<>();

		if (dati == null) {
			dati = new HashMap<>();
		}
		if (note == null) {
			note = new HashMap<>();
		}

		for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
			try {
				setDato(tipo, dati.get(tipo));
			} catch (NullPointerException e) {
				setDato(tipo, (byte) 0);
			}
			try {
				setNota(tipo, note.get(tipo));
			} catch (NullPointerException e) {
				setNota(tipo, "");
			}
		}
	}

	public long getRid() {
		return rid;
	}

	private void setDato(TipoDatoGeografico tipo, byte dato) {
		if (dato < 0 || dato > 5) {
			throw new IllegalArgumentException("dato non e' 0 >= X >= 5");
		}

		switch (tipo) {
			case MassaGhiacciai:
				massaGhiacciai = dato;
				break;

			case AltitudineGhiacciai:
				altitudineGhiacciai = dato;
				break;

			case Precipitazioni:
				precipitazioni = dato;
				break;

			case Pressione:
				pressione = dato;
				break;

			case Temperatura:
				temperatura = dato;
				break;

			case Umidita:
				umidita = dato;
				break;

			case Vento:
				vento = dato;
				break;
		}
	}

	public byte getDato(TipoDatoGeografico tipo) {
		byte dato = 0;

		switch (tipo) {
			case MassaGhiacciai:
				dato = massaGhiacciai;
				break;

			case AltitudineGhiacciai:
				dato = altitudineGhiacciai;
				break;

			case Precipitazioni:
				dato = precipitazioni;
				break;

			case Pressione:
				dato = pressione;
				break;

			case Temperatura:
				dato = temperatura;
				break;

			case Umidita:
				dato = umidita;
				break;

			case Vento:
				dato = vento;
				break;
		}

		return dato;
	}

	public String getNota(TipoDatoGeografico key) {
		return note.get(key);
	}

	private boolean setNota(TipoDatoGeografico key, String nota) {
		if (nota.length() > 255) {
			return false;
		}
		note.put(key, nota);
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DatoGeografico)) {
			return super.equals(obj);
		}

		DatoGeografico dato = (DatoGeografico) obj;

		boolean res = true;

		for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
			res &= getDato(tipo) == dato.getDato(tipo);
			res &= getNota(tipo).equals(dato.getNota(tipo));
		}

		return res;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s: (\n", super.toString()));
		for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
			sb.append(String.format("\t%s: %s '%s'\n", tipo.name(), getDato(tipo), getNota(tipo)));
		}
		sb.append(")");
		return sb.toString();
	}

	public boolean noteEquals(DatoGeografico dato) {
		boolean res = true;

		for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
			res &= getNota(tipo).equals(dato.getNota(tipo));
		}

		return res;
	}

	public boolean datoEquals(DatoGeografico dato) {
		boolean res = true;

		for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
			res &= getDato(tipo) == dato.getDato(tipo);
		}

		return res;
	}
}

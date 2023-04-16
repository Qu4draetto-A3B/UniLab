import java.util.HashMap;
import utils.TipoDatoGeografico;

/**
 * DatoGeografico
 *
 * Rappresenta un dato
 */
public class DatoGeografico {
	private byte massaGhiacciai;
	private byte altitudineGhiacciai;
	private byte precipitazioni;
	private byte temperatura;
	private byte pressione;
	private byte umidita;
	private byte vento;

	private HashMap<TipoDatoGeografico, String> note;

	public DatoGeografico(TipoDatoGeografico tipo, byte dato, String nota) {
		if (dato < 1) {
			throw new IllegalArgumentException("Almeno un dato deve essere > 0 in DatoGeografico");
		}

		setDato(tipo, dato);

		note = new HashMap<>();
		for (TipoDatoGeografico t : TipoDatoGeografico.values()) {
			note.put(t, "");
		}

		note.put(tipo, nota);
	}

	public DatoGeografico(byte massaGhiacciai, byte altitudineGhiacciai, byte precipitazioni, byte temperatura,
			byte pressione, byte umidita, byte vento, HashMap<TipoDatoGeografico, String> note) {
		/*
		 * I byte vengono inizializzati a 0 implicitamente,
		 * se tutti i dati sono 0 anche la loro somma sara' 0,
		 * quindi posso dedurre che non sono stati inseriti dati
		 */
		int all = massaGhiacciai + altitudineGhiacciai + precipitazioni + temperatura + pressione + umidita + vento;
		if (all < 1) {
			throw new IllegalArgumentException("Almeno un dato deve essere > 0 in DatoGeografico");
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

	public void setDato(TipoDatoGeografico tipo, byte dato) {
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

	public void setNota(TipoDatoGeografico key, String nota) {
		note.put(key, nota);
	}
}

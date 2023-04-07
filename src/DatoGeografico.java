import java.util.HashMap;

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

	public DatoGeografico(TipoDatoGeografico tipo, byte dato) {
		setDato(tipo, dato);
	}

	public DatoGeografico(byte MassaGhiacciai, byte altitudineGhiacciai, byte precipitazioni, byte temperatura, byte pressione, byte umidita, byte vento, HashMap<TipoDatoGeografico, String> note) {
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

enum TipoDatoGeografico {
	MassaGhiacciai,
	AltitudineGhiacciai,
	Precipitazioni,
	Temperatura,
	Pressione,
	Umidita,
	Vento;
}
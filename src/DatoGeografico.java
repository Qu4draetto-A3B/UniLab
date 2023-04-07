import java.util.HashMap;

/**
 * DatoGeografico
 */
public class DatoGeografico {
	private byte massaGhicciai;
	private byte altitudineGhiacciai;
	private byte precipitazioni;
	private byte temperatura;
	private byte pressione;
	private byte umidita;
	private byte vento;
	
	private HashMap<TipoDatoGeografico, String> note;
	
	public DatoGeografico() {
		//
	}
	
	public String getNota(TipoDatoGeografico key) {
		//
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
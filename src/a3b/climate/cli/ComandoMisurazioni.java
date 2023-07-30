package a3b.climate.cli;

import java.time.LocalDateTime;
import java.util.HashMap;

import a3b.climate.gestori.DataBase;
import a3b.climate.magazzeno.AreaGeografica;
import a3b.climate.magazzeno.CentroMonitoraggio;
import a3b.climate.magazzeno.DatoGeografico;
import a3b.climate.magazzeno.Misurazione;
import a3b.climate.magazzeno.Operatore;
import a3b.climate.utils.IniFile;
import a3b.climate.utils.TipoDatoGeografico;
import a3b.climate.utils.result.Result;
import a3b.climate.utils.terminal.Terminal;
import a3b.climate.utils.terminal.View;

/**
 * La classe {@code ComandoMisurazioni} implementa l'interfaccia {@link View}
 * per gestire il comando che crea una nuova misurazione.
 * <p>
 * Legge la configurazione necessaria da un file INI per creare una nuova
 * istanza di {@link Misurazione} e la aggiunge al database.
 */
public class ComandoMisurazioni implements View {

	/**
	 * Legge da un file INI la configurazione necessaria per creare una nuova
	 * misurazione.
	 * <p>
	 * La nuova {@link Misurazione} viene aggiunta al database e il
	 * risultato dell'operazione viene stampato nel terminale.
	 *
	 * @param term istanza di {@link Terminal} utilizzata per stampare la nuova
	 *             misurazione
	 */
	@Override
	public void start(Terminal term) {
		String path = App.line.getOptionValue("crea-misurazione");
		if (path.isBlank())
			path = "./MISURAZIONE.INI";

		IniFile ini = null;
		try {
			ini = new IniFile(path);
		} catch (Exception e) {
			term.printfln("Impossibile leggere il file '%s'", path);
			return;
		}

		// check e get operatore
		Result<Operatore> rop = DataBase.operatore.login(
				ini.getString("utente", "nome_utente", "*"),
				ini.getString("utente", "password", "*"));

		Operatore op = null;
		switch (rop.getError()) {
			case 0:
				op = rop.get();
				break;

			case 1:
				term.printfln("Utente non trovato");
				return;

			case 2:
				term.printfln("Password sbagliata");
				return;

			default:
				term.printfln("Errore sconosciuto");
				return;
		}

		// check e get centro
		Result<CentroMonitoraggio> rcm = DataBase.centro.getCentro(ini.getString("misurazione", "centro", "*"));

		if (rcm.isError()) {
			term.printfln("centro non trovato");
		}

		CentroMonitoraggio cm = rcm.get();

		// check e get area
		long geoid = 0;
		try {
			geoid = ini.getInt("misurazione", "area", 0);
		} catch (NumberFormatException e) {
			term.printfln("Non mi hai dato un geoid");
			return;
		}

		Result<AreaGeografica> rag = cm.getListaAree().getArea(geoid);

		if (rag.isError()) {
			term.printfln("Area non trovata");
			return;
		}

		AreaGeografica ag = rag.get();

		// check e get Dato geografico
		DatoGeografico dg = null;

		HashMap<TipoDatoGeografico, String> note = new HashMap<>();
		note.put(TipoDatoGeografico.AltitudineGhiacciai, ini.getString("note_dato", "altitudine_ghiacciai", "//"));
		note.put(TipoDatoGeografico.MassaGhiacciai, ini.getString("note_dato", "massa_ghiacciai", "//"));
		note.put(TipoDatoGeografico.Precipitazioni, ini.getString("note_dato", "precipitazioni", "//"));
		note.put(TipoDatoGeografico.Pressione, ini.getString("note_dato", "pressione", "//"));
		note.put(TipoDatoGeografico.Temperatura, ini.getString("note_dato", "temperatura", "//"));
		note.put(TipoDatoGeografico.Umidita, ini.getString("note_dato", "umidita", "//"));
		note.put(TipoDatoGeografico.Vento, ini.getString("note_dato", "vento", "//"));

		try {
			dg = new DatoGeografico(
					(byte) 0,
					(byte) ini.getInt("dato_geografico", "altitudine_ghiacciai", 0),
					(byte) ini.getInt("dato_geografico", "massa_ghiacciai", 0),
					(byte) ini.getInt("dato_geografico", "precipitazioni_ghiacciai", 0),
					(byte) ini.getInt("dato_geografico", "pressione_ghiacciai", 0),
					(byte) ini.getInt("dato_geografico", "temperatura_ghiacciai", 0),
					(byte) ini.getInt("dato_geografico", "umidita_ghiacciai", 0),
					(byte) ini.getInt("dato_geografico", "vento_ghiacciai", 0),
					note);
		} catch (NumberFormatException e) {
			term.printfln("Non hai inserito dei numeri tra 0 e 5 nella sezione [dato_geografico]");
		}

		// inserimento nel database
		dg = DataBase.dato.addDato(dg).get();

		Misurazione mis = new Misurazione(0, LocalDateTime.now(), op, cm, ag, dg);

		mis = DataBase.misurazioni.addMisurazione(mis).get();

		term.printfln("Misurazione inserita");
		term.printfln("%s", mis);
	}
}

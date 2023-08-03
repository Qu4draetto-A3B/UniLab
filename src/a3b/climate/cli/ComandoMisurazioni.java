package a3b.climate.cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
 * ComandoMisurazioni
 */
public class ComandoMisurazioni implements View {

	@Override
	public void start(Terminal term) throws IOException {
		Path misIni = Paths.get("./MISURAZIONE.INI");
		Path resIni = Paths.get("./data/resources/MISURAZIONE.INI");

		if (Files.notExists(misIni)) {
			Files.copy(resIni, misIni);
			term.printfln(
					"File '%s' creato, riempilo con le informazioni necessarie e riavvia l'applicazione con lo stesso comando",
					misIni.toString());
			return;
		}

		IniFile ini = new IniFile(misIni.toString());

		// check e get operatore
		String userid = ini.getString("utente", "nome_utente", "*").strip();
		String passwd = ini.getString("utente", "password", "*").strip();

		Result<Operatore> rop = DataBase.operatore.login(userid, passwd);

		Operatore op = null;
		switch (rop.getError()) {
			case 0:
				op = rop.get();
				break;

			default:
				term.printfln("%s", rop.getMessage());
				return;
		}

		// check e get centro
		String centro = ini.getString("misurazione", "centro", "*").strip();
		Result<CentroMonitoraggio> rcm = DataBase.centro.getCentro(centro);

		if (rcm.isError()) {
			term.printfln("'%s': %s", centro, rcm.getMessage());
			return;
		}

		CentroMonitoraggio cm = rcm.get();

		// check e get area
		long geoid = 0;
		try {
			geoid = ini.getInt("misurazione", "geoid_area", 0);
		} catch (NumberFormatException e) {
			term.printfln("Non mi hai dato un geoid");
			return;
		}

		Result<AreaGeografica> rag = cm.getListaAree().getArea(geoid);

		if (rag.isError()) {
			term.printfln("[%d]: %s", geoid, rag.getMessage());
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
					(byte) ini.getInt("dato_geografico", "precipitazioni", 0),
					(byte) ini.getInt("dato_geografico", "pressione", 0),
					(byte) ini.getInt("dato_geografico", "temperatura", 0),
					(byte) ini.getInt("dato_geografico", "umidita", 0),
					(byte) ini.getInt("dato_geografico", "vento", 0),
					note);
		} catch (NumberFormatException e) {
			term.printfln("Non hai inserito dei numeri tra 0 e 5 nella sezione [dato_geografico]");
			return;
		}

		// inserimento nel database
		dg = DataBase.dato.addDato(dg).get();

		Misurazione mis = new Misurazione(0, LocalDateTime.now(), op, cm, ag, dg);

		mis = DataBase.misurazioni.addMisurazione(mis).get();

		term.printfln("Misurazione inserita");
		term.printfln("%s", mis);
	}
}

package a3b.climate.gestori;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.csv.CSVRecord;

import a3b.climate.magazzeno.DatoGeografico;
import a3b.climate.utils.DataTable;
import a3b.climate.utils.TipoDatoGeografico;
import a3b.climate.utils.result.Result;

public class GestoreDato extends Gestore {

	public GestoreDato() {
		super(
				"./data/ParametriClimatici.CSV",
				new String[] {
						"RID", "AltitudineGhiacciai", "MassaGhiacciai", "Precipitazioni", "Pressione",
						"Temperatura", "Umidita", "Vento",
						"NotaAltitudineGhiacciai", "NotaMassaGhiacciai", "NotaPrecipitazioni",
						"NotaPressione", "NotaTemperatura", "NotaUmidita", "NotaVento" });
	}

	public Result<DatoGeografico> getDato(long rid) {
		CSVRecord rec = null;
		long dbRid = 0;

		for (CSVRecord record : records) {
			dbRid = Long.parseLong(record.get("RID"));
			if (dbRid == rid) {
				rec = record;
				break;
			}
		}

		if (rec == null) {
			return new Result<>(1, "Dato non trovato");
		}

		return new Result<>((DatoGeografico) buildObject(rec));
	}

	public Result<Object> addDato(DatoGeografico dato) {
		long newRID = Long.parseLong(getProperty("LastRID").get());
		newRID++;
		setProperty("LastRID", String.valueOf(newRID)).get();

		try {
			// haha
			p.printRecord(
					newRID,
					dato.getDato(TipoDatoGeografico.AltitudineGhiacciai),
					dato.getDato(TipoDatoGeografico.MassaGhiacciai),
					dato.getDato(TipoDatoGeografico.Precipitazioni),
					dato.getDato(TipoDatoGeografico.Pressione),
					dato.getDato(TipoDatoGeografico.Temperatura),
					dato.getDato(TipoDatoGeografico.Umidita),
					dato.getDato(TipoDatoGeografico.Vento),
					dato.getNota(TipoDatoGeografico.AltitudineGhiacciai),
					dato.getNota(TipoDatoGeografico.MassaGhiacciai),
					dato.getNota(TipoDatoGeografico.Precipitazioni),
					dato.getNota(TipoDatoGeografico.Pressione),
					dato.getNota(TipoDatoGeografico.Temperatura),
					dato.getNota(TipoDatoGeografico.Umidita),
					dato.getNota(TipoDatoGeografico.Vento));

			p.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return new Result<>(1, "Errore nella scrittura del record");
		}

		return new Result<>(new Object());

	}

	@Override
	protected DataTable buildObject(CSVRecord record) {
		long rid = Long.parseLong(record.get("RID"));

		HashMap<TipoDatoGeografico, Byte> dati = new HashMap<>();
		HashMap<TipoDatoGeografico, String> note = new HashMap<>();

		for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
			dati.put(tipo, Byte.parseByte(record.get(tipo.name())));
			note.put(tipo, record.get("Nota" + tipo.name()));
		}

		DatoGeografico dato = new DatoGeografico(rid, dati, note);

		return dato;
	}
}

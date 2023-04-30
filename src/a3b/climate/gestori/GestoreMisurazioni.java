package a3b.climate.gestori;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import a3b.climate.magazzeno.Filtratore;
import a3b.climate.magazzeno.Misurazione;
import a3b.climate.utils.result.*;

public class GestoreMisurazioni extends Gestore {
	public GestoreMisurazioni() {
		super(
				"./data/Misurazioni.CSV",
				new String[] { "RID", "DateTime", "Operatore", "Centro", "Area", "Dato" });
	}

	public Result<Object> addMisurazione(Misurazione mis) {
		long newRID = Long.parseLong(getProperty("LastRID").get());
		newRID++;
		setProperty("LastRID", String.valueOf(newRID)).get();

		try {
			p.printRecord(
					newRID,
					mis.getTimeString(),
					mis.getOperatore().getCf(),
					mis.getCentro().getNome(),
					mis.getArea().getGeoID(),
					mis.getDato().getRid());

			p.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return new Result<>(1, "Errore nella scrittura del record");
		}

		return new Result<>(new Object());
	}

	public Result<Filtratore> getMisurazioni() {
		List<Misurazione> lm = new LinkedList<>();
		for (CSVRecord record : records) {
			Misurazione mis = new Misurazione(
					Long.parseLong(record.get("RID")),
					LocalDateTime.parse(record.get("DateTime"), Misurazione.DATE_TIME_FORMAT),
					DataBase.operatore.getOperatore(record.get("Operatore")).get(),
					DataBase.centro.getCentro(record.get("Centro")).get(),
					DataBase.area.getArea(Long.parseLong(record.get("Area"))).get(),
					DataBase.dato.getDato(0).get());

			lm.add(mis);
		}
		return new Result<>(new Filtratore(lm));
	}
}

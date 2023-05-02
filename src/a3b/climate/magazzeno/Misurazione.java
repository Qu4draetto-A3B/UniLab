package a3b.climate.magazzeno;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import a3b.climate.utils.Convertable;

/**
 * Rappresenta una misurazione
 */

public class Misurazione implements Convertable {
	private DatoGeografico dato;
	private LocalDateTime time;
	private Operatore operatore;
	private CentroMonitoraggio centro;
	private AreaGeografica area;

	/**
	 * Costruttore di un'istanza di Misurazione
	 * @param dato Aggregato di informazioni relative al dato geografico
     * @param operatore Operatore che effettua la misurazione
	 * @param area Area geografica nella quale e' stata effettuata la misurazione
	 */

	public Misurazione(DatoGeografico dato, Operatore operatore, AreaGeografica area) {
		this.dato = dato;
		this.operatore = operatore;
		this.area = area;
		time = LocalDateTime.now();
		centro = operatore.getCentro();
	}

	/**
	 * @return Restituisce il l'aggregato di informazioni relative al dato della misurazione che chiama il metodo
	 */

	public DatoGeografico getDato() {
		return this.dato;
	}

	/**
	 * @return Restituisce la data in cui è stata effettuata la misurazione che chiama il metodo
	 */

	public LocalDateTime getTime() {
		return this.time;
	}

	/**
	 * @return Restituisce l'operatore che ha effettuato misurazione che chiama il metodo
	 */

	public Operatore getOperatore() {
		return this.operatore;
	}

	/**
	 * @return Restituisce il centro di monitoraggio per il qule e' stata effettuata la misurazione che chiama il metodo
	 */

	public CentroMonitoraggio getCentro() {
		return this.centro;
	}

	/**
	 * @return Restituisce l'area geografica nella quale e' stata effettuata la misurazione che chiama il metodo
	 */

	public AreaGeografica getArea() {
		return this.area;
	}

	@Override
	public String toString() {
		String str = String.format(
			"%s <<<\n- DateTime: \n%s\n- AreaGeografica: \n%s\n- Operatore: \n%s\n- Centro: \n%s\n- Dato: \n%s\n>>> %s",
			super.toString(), time.format(DateTimeFormatter.ISO_DATE_TIME),
			area, operatore, centro, dato, super.toString());
		return str;
	}

	@Override
	public String toCsv() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toCsv'");
	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'toJson'");
	}
}

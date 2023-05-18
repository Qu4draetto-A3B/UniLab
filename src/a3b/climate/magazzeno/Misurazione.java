/*
 * Interdisciplinary Workshop A
 * Climate Monitoring
 * A.A. 2022-2023
 *
 * Authors:
 * - Iuri Antico, 753144
 * - Beatrice Balzarini, 752257
 * - Michael Bernasconi, 752259
 * - Gabriele Borgia, 753262
 *
 * Some rights reserved.
 * See LICENSE file for additional information.
 */
package a3b.climate.magazzeno;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import a3b.climate.utils.Convertable;
import a3b.climate.utils.DataTable;

/**
 * Rappresenta una misurazione
 */
public class Misurazione implements Convertable, DataTable {
	private long rid;
	private DatoGeografico dato;
	private LocalDateTime time;
	private Operatore operatore;
	private CentroMonitoraggio centro;
	private AreaGeografica area;
	public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ISO_INSTANT;

	/**
	 * Costruttore di un'istanza di Misurazione
	 * @param rid Id della misurazione
	 * @param dato Aggregato di informazioni relative al dato geografico
     * @param operatore Operatore che effettua la misurazione
	 * @param area Area geografica nella quale e' stata effettuata la misurazione
	 */
	public Misurazione(long rid, DatoGeografico dato, Operatore operatore, AreaGeografica area) {
		this.dato = dato;
		this.operatore = operatore;
		this.area = area;
		time = LocalDateTime.now();
		centro = operatore.getCentro();
	}

	public Misurazione(long rid, LocalDateTime dateTime, Operatore operatore, CentroMonitoraggio centro,
			AreaGeografica area, DatoGeografico dato) {
		this.dato = dato;
		this.operatore = operatore;
		this.area = area;
		time = LocalDateTime.now();
		centro = operatore.getCentro();
	}

	public long getRid() {
		return rid;
	}

	/**
	 * @return Aggregato di informazioni relative al dato della misurazione che chiama il metodo
	 */
	public DatoGeografico getDato() {
		return this.dato;
	}

	/**
	 * @return Data in cui è stata effettuata la misurazione che chiama il metodo
	 */
	public LocalDateTime getTime() {
		return this.time;
	}

	public String getTimeString() {
		return time.format(DATE_TIME_FORMAT);
	}

	/**
	 * @return Operatore che ha effettuato misurazione che chiama il metodo
	 */
	public Operatore getOperatore() {
		return this.operatore;
	}

	/**
	 * @return Centro di monitoraggio per il qule e' stata effettuata la misurazione che chiama il metodo
	 */
	public CentroMonitoraggio getCentro() {
		return this.centro;
	}

	/**
	 * @return Area geografica nella quale e' stata effettuata la misurazione che chiama il metodo
	 */
	public AreaGeografica getArea() {
		return this.area;
	}

	@Override
	public String toString() {
		String str = String.format(
				"%s <<<\n- DateTime: \n%s\n- AreaGeografica: \n%s\n- Operatore: \n%s\n- Centro: \n%s\n- Dato: \n%s\n>>> %s",
				super.toString(), getTimeString(),
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

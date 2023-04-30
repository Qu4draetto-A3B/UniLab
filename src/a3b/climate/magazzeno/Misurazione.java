package a3b.climate.magazzeno;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import a3b.climate.utils.Convertable;

public class Misurazione implements Convertable {
	private long rid;
	private DatoGeografico dato;
	private LocalDateTime time;
	private Operatore operatore;
	private CentroMonitoraggio centro;
	private AreaGeografica area;
	public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ISO_INSTANT;

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

	public DatoGeografico getDato() {
		return this.dato;
	}

	public LocalDateTime getTime() {
		return this.time;
	}

	public String getTimeString() {
		return time.format(DATE_TIME_FORMAT);
	}

	public Operatore getOperatore() {
		return this.operatore;
	}

	public CentroMonitoraggio getCentro() {
		return this.centro;
	}

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

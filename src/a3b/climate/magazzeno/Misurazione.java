package a3b.climate.magazzeno;

import java.time.LocalDateTime;

public class Misurazione {
	private DatoGeografico dato;
	private LocalDateTime time;
	private Operatore operatore;
	private CentroMonitoraggio centro;
	private AreaGeografica area;

	public Misurazione(DatoGeografico dato, Operatore operatore, AreaGeografica area) {
		this.dato = dato;
		this.operatore = operatore;
		this.area = area;
	}

	public DatoGeografico getDato() {
		return this.dato;
	}

	public LocalDateTime getTime() {
		return this.time;
	}

	public Operatore getOperatore() {
		return this.operatore;
	}

	public CentroMonitoraggio getCentroMonitoraggio() {
		return this.centro;
	}

	public AreaGeografica getArea() {
		return this.area;
	}

}

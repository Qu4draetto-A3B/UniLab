package a3b.climate.magazzeno;

import java.time.LocalDateTime;

import a3b.climate.utils.Convertable;

public class Misurazione implements Convertable {
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

	public CentroMonitoraggio getCentro() {
		return this.centro;
	}

	public AreaGeografica getArea() {
		return this.area;
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

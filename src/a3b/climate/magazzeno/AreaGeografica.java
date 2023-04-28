package a3b.climate.magazzeno;

import a3b.climate.utils.DataTable;

public class AreaGeografica implements DataTable {
	private long geoID;
    private double latitudine;
    private double longitudine;
    private String stato;
    private String denominazione;

    public AreaGeografica(long geoID, double latitudine, double longitudine, String stato, String denominazione) {
		this.geoID = geoID;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.stato = stato;
        this.denominazione = denominazione;
    }

	public long getGeoID() {
		return geoID;
	}

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public String getStato() {
        return stato;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public String toString() {
        return String.format("%s (%s) LAT:%f LON:%f", this.denominazione, this.stato, this.latitudine, this.longitudine);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AreaGeografica)) {
            return super.equals(obj);
        }

        AreaGeografica ag = (AreaGeografica) obj;

        if (this.geoID == ag.getGeoID())
            return true;
        else
            return false;
    }
}

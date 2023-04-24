package a3b.climate.magazzeno;

public class AreaGeografica {
    private double latitudine;
    private double longitudine;
    private String stato;
    private String denominazione;

    public AreaGeografica(double latitudine, double longitudine, String stato, String denominazione) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.stato = stato;
        this.denominazione = denominazione;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getStato() {
        return stato;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
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

        if (this.latitudine == ag.latitudine && this.longitudine == ag.longitudine)
            return true;
        else
            return false;
    }
}

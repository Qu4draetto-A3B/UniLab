package a3b.climate.magazzeno;

import a3b.climate.utils.DataTable;

/**
 * Rappresenta un'area geografica
 */

public class AreaGeografica implements DataTable {
    private double latitudine;
    private double longitudine;
    private String stato;
    private String denominazione;

    /**
	 * Costruttore di un'istanza di AreaGeografica
	 * @param latitudine Latitudine relativa all'area geografica
     * @param logitudine Longitudine relativa all'area geografica
     * @param stato Stato in cui si trova l'area geografica
     * @param denominazione Nome dell'area geografica
	 */

    public AreaGeografica(double latitudine, double longitudine, String stato, String denominazione) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.stato = stato;
        this.denominazione = denominazione;
    }

    /**
	 * Metodo che imposta la latitudine relativa all'area geografica che chiama il metodo
     * @param latitudine Latitudine relativa all'area geografica
	 */

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    /**
	 * @return Restituisce la latitudine relativa all'area geografica che chiama il metodo
	 */

    public double getLatitudine() {
        return latitudine;
    }

    /**
	 * Metodo che imposta la longitudine relativa all'area geografica che chiama il metodo
     * @param logitudine Longitudine relativa all'area geografica
	 */

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    /**
	 * @return Restituisce la logitudine relativa all'area geografica che chiama il metodo
	 */

    public double getLongitudine() {
        return longitudine;
    }

    /**
	 * Metodo che imposta lo stato in cui si trova l'area geografica che chiama il metodo
     * @param stato Stato in cui si trova l'area geografica
	 */

    public void setStato(String stato) {
        this.stato = stato;
    }

    /**
	 * @return Restituisce lo stato in cui si trova l'area geografica che chiama il metodo
	 */

    public String getStato() {
        return stato;
    }

    /**
	 * Metodo che imposta il nome dell'area geografica che chiama il metodo
     * @param denominazione Nome dell'area geografica
	 */

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    /**
	 * @return Restituisce il nome dell'area geografica che chiama il metodo
	 */

    public String getDenominazione() {
        return denominazione;
    }

    @Override
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

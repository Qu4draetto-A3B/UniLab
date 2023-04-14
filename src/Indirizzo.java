public class Indirizzo {

    private String tipoVia;
    private String nomeVia;
    private int civico;
    private int cap;
    private String comune;
    private String provincia;

    public Indirizzo() {
    }

    public Indirizzo(String tipoVia, String nomeVia, int civico, int cap, String comune, String provincia) {
        this.tipoVia = tipoVia;
        this.nomeVia = nomeVia;
        this.civico = civico;
        this.cap = cap;
        this.comune = comune;
        this.provincia = provincia;
    }

    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipovia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getNomeVia() {
        return nomeVia;
    }

    public void setNomevia(String nomeVia) {
        this.nomeVia = nomeVia;
    }

    public int getCivico() {
        return civico;
    }

    public void setCivico(int civico) {
        this.civico = civico;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

}

package account;

public class AddressEntity {
    private String via;
    private String cap;
    private String civico;
    private String città;
    private String nazione;

    public AddressEntity() {

    }

    public AddressEntity(String via, String cap, String civico, String città, String nazione) {
        this.via = via;
        this.cap = cap;
        this.civico = civico;
        this.città = città;
        this.nazione = nazione;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getCittà() {
        return città;
    }

    public void setCittà(String città) {
        this.città = città;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }
}

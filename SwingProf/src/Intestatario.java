import java.util.ArrayList;

public class Intestatario {

    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String dataNascita;
    private String email;
    private String telefono;

    private ArrayList<ContoCorrente> ElencoConti;


    public Intestatario() {
        ElencoConti = new ArrayList<ContoCorrente>();
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getCodiceFiscale() {
        return codiceFiscale;
    }
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
    public String getDataNascita() {
        return dataNascita;
    }
    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }




    public void addConto(ContoCorrente c) {
        if(!ElencoConti.contains(c))
            ElencoConti.add(c);
    }

}

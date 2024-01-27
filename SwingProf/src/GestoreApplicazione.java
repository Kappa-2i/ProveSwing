
public class GestoreApplicazione {

    LoginFrame lf;
    HomePage hpf;
    CreaIntestatarioFrame creaIntFrame;




    public static void main(String[] args) {
        GestoreApplicazione ga = new GestoreApplicazione();
    }


    public GestoreApplicazione() {
        lf = new LoginFrame(this);
        hpf= new HomePage(this);
        creaIntFrame = new CreaIntestatarioFrame(this);
        lf.setVisible(true);
    }

    public void controllaLogin(String usr, String pwd) {
        //Controllo validità usr e pwd da database
        /*
         * connettiti al db
         *
         * crea query di selezione * from user where usr....
         *
         * if n� tuple restituite ==1 login fatto con successo
         *
         */
        boolean userExists=true;
        if(userExists) {
            lf.setVisible(false);
            hpf.setVisible(true);
        }
    }

    public void inserisciNuovoIntestatario() {
        hpf.setVisible(false);
        creaIntFrame.setVisible(true);
    }

    public void creaNuovoIntestatario(String nome, String cognome) {
        Intestatario i = new Intestatario();
        i.setNome(nome);
        i.setCodiceFiscale(cognome);


    }


}

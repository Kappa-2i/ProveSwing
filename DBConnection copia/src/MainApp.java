import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class MainApp {
    LoginPage lp;
    SignInPage sip;

    public static void main(String[] args) {
        MainApp Ma = new MainApp();
    }

    public void MainApp(){
        lp = new LoginPage(this);

    }

    public void CheckLogin(String email, String password){
        // Chiamata al metodo checkCredentials
        DBConnection dbConnection = DBConnection.getDBConnection();

        // Tentativo di stabilire una connessione al database.
        Connection conn = null;
        try {
            conn = dbConnection.getConnection();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        // Controllo se la connessione è stata stabilita con successo.
        if (conn != null) {
            String[] infoUtente = null;
            try {
                infoUtente = UtenteDAO.checkCredentials(email, password);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (infoUtente != null) {
                if ("false".equals(infoUtente[2]))
                    //JOptionPane.showMessageDialog(LoginPage, "Accesso effettuato. Benvenuto "+infoUtente[0]+ " non hai nessun conto aperto.");
                    lp.setVisible(true);
                else
                    lp.setVisible(false);
                    sip.setVisible(true);
            } else {
                //JOptionPane.showMessageDialog(LoginPage.this, "Accesso fallito. Per favore controlla le tue credenziali.");
                lp.setVisible(true);
            }
        }
    }
}


import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class MainApp {
    LoginPage lp;
    SignInPage sip;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Usausa Nimbus Look and Feel
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            MainApp ma = new MainApp();
        });
    }

    public MainApp(){
        lp = new LoginPage(this);
        lp.setVisible(true);
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
                    //return 1 se hai un account ma nessun conto aperto
                    gestoreLogin(1);
                else
                    //return 2 se hai un account e un conto aperto
                    gestoreLogin(2);
            } else {
                //return 3 se le credenziali inserite non esistono
                gestoreLogin(3);
            }
        }
        else {
            JOptionPane.showMessageDialog(lp, "Accesso fallito. Per favore controlla le tue credenziali.");
        }

    }

    public void gestoreLogin(int caso){
        switch (caso){
            case 1:
                lp.setVisible(false);
                //registraContoFrame.setVisible(true);
                break;
            case 2:
                lp.setVisible(false);
                //sip.setVisible(true);
                break;
            case 3:
                JOptionPane.showMessageDialog(lp, "Credenziali errate - Riprova.");
                break;
            default:
                break;
        }
    }
}


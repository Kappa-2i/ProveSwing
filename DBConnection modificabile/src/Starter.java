import java.sql.*;
import java.util.Scanner;

// Definizione della classe principale.
public class Starter {
    // Metodo main: punto di ingresso dell'applicazione.
    public static void main(String[] args) {
        // Creazione di un oggetto Scanner per leggere l'input dell'utente.
        Scanner scanner = new Scanner(System.in);

        // Creazione di un oggetto DBConnection per gestire la connessione al database.
        DBConnection dbConnection = DBConnection.getDBConnection();

        try {
            // Tentativo di stabilire una connessione al database.
            Connection conn = dbConnection.getConnection();

            // Controllo se la connessione è stata stabilita con successo.
            if (conn != null) {
                System.out.println("Connessione al Database riuscita!");

                System.out.print("Inserisci email: ");
                String email = scanner.nextLine();
                System.out.print("Inserisci password: ");
                String password = scanner.nextLine();

                // Metodo esistente per il controllo delle credenziali
                String[] infoUtente = UtenteDAO.checkCredentials(email, password);

                if (infoUtente != null) {
                    String message = "Accesso effettuato - Benvenuto " + infoUtente[0];
                    if ("false".equals(infoUtente[2])) {
                        message += ", non hai nessun conto aperto.";
                    } else {
                        message += ". Saldo disponibile: " + infoUtente[1] + "€.";
                    }
                    System.out.println(message);
                } else {
                    System.out.println("Riprova - Email o password sbagliati.");
                }

                // Disconnessione dal database.
                dbConnection.disconnect();
                System.out.println("Il database è stato disconnesso.");
            } else {
                System.out.println("Connessione NON riuscita!");
            }
        } catch (SQLException e) {
            // Gestione delle eccezioni SQL.
            e.printStackTrace();
            System.out.println("Errore di connessione al database.");
        }
    }
}


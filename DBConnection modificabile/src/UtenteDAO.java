import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Definizione della classe UtenteDAO
public class UtenteDAO {

    // Metodo statico checkCredentials per verificare le credenziali dell'utente
    public static String[] checkCredentials(String email, String password) throws SQLException {
        // Query SQL per ottenere i dettagli dell'utente
        String sql = "SELECT a.NomeUtente, cc.Saldo, CASE WHEN cc.IBAN IS NULL THEN 'false' ELSE 'true' END as ContoEsistente " +
                "FROM test.account a LEFT JOIN test.contocorrente cc ON a.email = cc.account_email " +
                "WHERE a.email ILIKE ? AND a.password = ?";

        // Utilizzo di un blocco try-with-resources per la gestione automatica delle risorse
        try (Connection conn = DBConnection.getDBConnection().getConnection();  // Ottenimento della connessione al database
             PreparedStatement statement = conn.prepareStatement(sql)) {  // Creazione di un PreparedStatement

            statement.setString(1, email);  // Impostazione del primo parametro (email)
            statement.setString(2, password);  // Impostazione del secondo parametro (password)

            // Esecuzione della query e gestione del ResultSet
            try (ResultSet resultSet = statement.executeQuery()) {
                // Verifico se ho una tupla da analizzare
                if (resultSet.next()) {
                    // Estrazione dei dati dal ResultSet
                    String NomeUtente = resultSet.getString("NomeUtente");
                    String Saldo = resultSet.getString("Saldo") != null ? resultSet.getString("Saldo") : "0";
                    String ContoEsistente = resultSet.getString("ContoEsistente");

                    // Ritorno dei dati dell'utente sotto forma di array di stringhe
                    return new String[]{NomeUtente, Saldo, ContoEsistente};
                }
            }
        }
        catch (SQLException e) {
            // Gestione delle eccezioni SQL
            e.printStackTrace();
        }
        // Ritorno di null in caso di errore o se le credenziali non sono valide
        return null;
    }
}

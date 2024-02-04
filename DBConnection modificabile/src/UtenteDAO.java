import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


// Definizione della classe UtenteDAO
public class UtenteDAO {

    // Metodo statico checkCredentials per verificare le credenziali dell'utente
    public static String[] checkCredentials(String email, String password) throws SQLException {
        // Query SQL per ottenere i dettagli dell'utente
        String sql = "SELECT a.NomeUtente, cc.Saldo, CASE WHEN cc.IBAN IS NULL THEN 'false' ELSE 'true' END as ContoEsistente " +
                "FROM db.account a LEFT JOIN db.contocorrente cc ON a.email = cc.account_email " +
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


    public static void insertDati(String codiceFiscale, String nome, String cognome, String dataDiNascita,
                                  String numeroTelefono, String citta, String via, String nCivico, String cap) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getDBConnection().getConnection();
            connection.setAutoCommit(false); // Imposta l'auto commit su "off"

            // Crea un'istruzione SQL di inserimento con parametri segnaposto (?)
            String sql = "INSERT INTO db.persona (codicefiscale, nome, cognome, datanascita, numerotelefono, città, via, n_civico, cap) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, codiceFiscale);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, cognome);

            // Converte la data di nascita da stringa a java.sql.Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adatta il formato alla tua stringa
            java.util.Date parsedDate = dateFormat.parse(dataDiNascita);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());


            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setString(5, numeroTelefono);
            preparedStatement.setString(6, citta);
            preparedStatement.setString(7, via);
            preparedStatement.setString(8, nCivico);
            preparedStatement.setString(9, cap);

            // Esegui l'istruzione di inserimento
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(
                    null,
                    "Dati inseriti con successo.",
                    "Benvenuta/o",
                    JOptionPane.INFORMATION_MESSAGE
            );
            // Commit delle modifiche
            connection.commit();
        } catch (SQLException e) {
            // Gestisci l'eccezione e esegui il rollback in caso di errore
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            if (e.getMessage().contains("value too long for type character varying(5)")) {
                JOptionPane.showMessageDialog(
                        null,
                        "Il campo CAP deve avere al massimo 5 caratteri.",
                        "Errore di validazione",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            if (e.getMessage().contains("value too long for type character varying(16)")) {
                JOptionPane.showMessageDialog(
                        null,
                        "Il campo Codice Fiscale deve avere al massimo 16 caratteri.",
                        "Errore di validazione",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            if (e.getMessage().contains("new row for relation \"persona\" violates check constraint \"persona_numerotelefono_check\"")) {
                JOptionPane.showMessageDialog(
                        null,
                        "Il campo Telefono deve avere al massimo 10 caratteri.",
                        "Errore di validazione",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            if (e.getMessage().contains("new row for relation \"persona\" violates check constraint \"persona_datanascita_check\"")) {
                JOptionPane.showMessageDialog(
                        null,
                        "La data di nascita non è valida. Devi essere maggiorenne.",
                        "Errore di validazione",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                // Gestisci altri tipi di errore
                e.printStackTrace();
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            // Chiudi la connessione e il preparedStatement
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }
        }
    }
}




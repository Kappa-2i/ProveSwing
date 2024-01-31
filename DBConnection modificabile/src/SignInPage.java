import javax.naming.event.ObjectChangeListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Console;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;






public class SignInPage extends JFrame {
    private MainApp myGestore;
    private RoundedTextField emailField;
    private Font fontRegular;
    private Font fontBold;
    private Font fontExtraBold;
    private Font fontRegularSmall;
    private Font fontRegularBold;
    private GridBagConstraints gbcOriginal;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Imposta il Look and Feel di sistema
                //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                // Oppure, usa Nimbus Look and Feel
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            SignInPage signFrame = new SignInPage(new MainApp());
        });
    }


    public SignInPage(MainApp ma){
        myGestore = ma;
        setTitle("SignIn Page");
        setSize(1400, 800);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        fontBold();
        fontRegular();
        fontExtraBold();
        fontRegularSmall();
        fontRegularBold();


        //Creazione dei pannelli


        // Creazione del contentPane
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(new Color(255, 255, 255));


        //Creazine dei constraints per il GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();


        // Creazione del panel 'panelSignIn'
        JPanel panelSignIn = new JPanel(new GridBagLayout());
        panelSignIn.setBackground(new Color(133, 53, 53)); // Scegli il colore che preferisci
        panelSignIn.setOpaque(true);


        // Creazione dello scrollPane 'scrollPane1'
        JScrollPane scrollPane1 = new JScrollPane(panelSignIn);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Imposta la barra di scorrimento verticale solo quando necessario
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Imposta la barra di scorrimento orizzontale solo quando necessario
        scrollPane1.getVerticalScrollBar().setUI(new CustomScrollBarUI()); // Aggiunta della scroll bar vertcale personalizzata
        scrollPane1.getHorizontalScrollBar().setUI(new CustomScrollBarUI()); // Aggiunta della scroll bar orizzontale personalizzata
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.6;
        gbc.weighty = 1;
        gbc.insets = new Insets(-300, -500, 0, 0);
        contentPane.add(scrollPane1, gbc); // Aggiunge lo scorllPane1 al contentPane


        //Creazione di un JPanel 'panelSignInTrasparent' con GridBagLayout
        JPanel panelSignInTrasparent = new JPanel(new GridBagLayout());
        panelSignInTrasparent.setBackground(new Color(95, 94, 65)); // Scegli il colore che preferisci
        panelSignInTrasparent.setOpaque(false); // Imposta come trasparente per mostrare il gradiente
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.weightx = 0.4;
        contentPane.add(panelSignInTrasparent, gbc); // Aggiunge il panelSignInTrasparent al contentPane


        //Creazione dei componenti utili alla resgistrazione


        //Creazione della label 'creaAccountLabel'
        JLabel creaAccountLabel = new JLabel("Crea Account");
        creaAccountLabel.setForeground(Color.WHITE);
        if(fontExtraBold != null)
            creaAccountLabel.setFont(fontExtraBold);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(50, 5, 100, 5);
        panelSignIn.add(creaAccountLabel, gbc); // Aggiunge creaAccountLabel al panelSignIn


        //Creazione della label 'Nome' e della textfiel per il campo Nome
        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setForeground(Color.WHITE);
        if(fontBold != null)
            nomeLabel.setFont(fontRegularBold);
        RoundedTextField nomeField = new RoundedTextField(20);
        nomeField.setBackground(new Color(217, 217, 217));
        if(fontRegular != null)
            nomeField.setFont(fontRegular);
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelSignIn.add(nomeLabel, gbc); // Aggiunge nomeLabel al panelSignIn
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 20, 5);
        panelSignIn.add(nomeField, gbc); // Aggiunge nomeField al panelSignIn


        //Creazione della label cognomeLabel e della textfield per il campo Cognome
        JLabel cognomeLabel = new JLabel("Cognome");
        cognomeLabel.setForeground(Color.WHITE);
        if(fontBold != null)
            cognomeLabel.setFont(fontBold);
        RoundedTextField cognomeField = new RoundedTextField(20);
        cognomeField.setBackground(new Color(217, 217, 217));
        if(fontRegular != null)
            cognomeField.setFont(fontRegular);
        gbc.insets = new Insets(5, 100, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelSignIn.add(cognomeLabel, gbc); // Aggiunge cognomeLabel al panelSignIn
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 100, 20, 5);
        panelSignIn.add(cognomeField, gbc); // Aggiunge cognomeField al panelSignIn


        //Creazione della label 'dataLabel' e della textfield per il campo data di nascita
        JLabel dataLabel = new JLabel("Data di Nascita");
        dataLabel.setForeground(Color.WHITE);
        if(fontBold != null)
            dataLabel.setFont(fontBold);;
        RoundedTextField dataField = new RoundedTextField(20);
        dataField.setBackground(new Color(217, 217, 217));
        if(fontRegular != null)
            dataField.setFont(fontRegular);
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelSignIn.add(dataLabel, gbc); // Aggiunge dataLabel al panelSignIn
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 20, 5);
        panelSignIn.add(dataField, gbc); // Aggiunge dataField al panelSignIn


        // Creazione della label 'luogoNascitaLabel' e della textfield per il campo luogo di nascita
        JLabel luogoNascitaLabel = new JLabel("Luogo di Nascita");
        luogoNascitaLabel.setForeground(Color.WHITE);
        if(fontBold != null)
            luogoNascitaLabel.setFont(fontBold);
        RoundedTextField luogoNascitaField = new RoundedTextField(20);
        luogoNascitaField.setBackground(new Color(217, 217, 217));
        if(fontRegular != null)
            luogoNascitaField.setFont(fontRegular);
        gbc.insets = new Insets(5, 100, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelSignIn.add(luogoNascitaLabel, gbc); // Aggiunge luogoNascitaLabel al panelSignIn
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 100, 20, 5);
        panelSignIn.add(luogoNascitaField, gbc); // Aggiunge luogoNascitaField al panelSignIn


        //Creazione della label 'codiceFiscaleLabel' e della textfield per il campo codice fiscale
        JLabel codiceFiscaleLabel = new JLabel("Codice Fiscale");
        codiceFiscaleLabel.setForeground(Color.WHITE);
        if(fontBold != null)
            codiceFiscaleLabel.setFont(fontBold);
        RoundedTextField codiceFiscaleField = new RoundedTextField(16);
        codiceFiscaleField.setBackground(new Color(217, 217, 217));
        if(fontRegular != null)
            codiceFiscaleField.setFont(fontRegular);
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelSignIn.add(codiceFiscaleLabel, gbc); // Aggiunge codiceFiscaleLabel al panelSignIn
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 5, 20, 5);
        panelSignIn.add(codiceFiscaleField, gbc); // Aggiunge codiceFiscaleField al panelSignIn


        //Creazione della label 'telefonoLabel' e della textfield per il campo telefono
        JLabel telefonoLabel = new JLabel("Telefono");
        telefonoLabel.setForeground(Color.WHITE);
        if(fontBold != null)
            telefonoLabel.setFont(fontBold);
        RoundedTextField telefonoField = new RoundedTextField(20);
        telefonoField.setBackground(new Color(217, 217, 217));
        if(fontRegular != null)
            telefonoField.setFont(fontRegular);
        gbc.insets = new Insets(5, 100, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panelSignIn.add(telefonoLabel, gbc); // Aggiunge telefonoLabel al panelSignIn
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 100, 20, 5);
        panelSignIn.add(telefonoField, gbc); // Aggiunge telefonoField al panelSignIn


        //Creazione della label 'emailLabel' e della textfield per il campo email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setForeground(Color.WHITE);
        if(fontBold != null)
            emailLabel.setFont(fontBold);
        emailField = new RoundedTextField(20);
        emailField.setBackground(new Color(217, 217, 217));
        if (fontRegular != null)
            emailField.setFont(fontRegular);
        //Controllo della validità dell'email quando ci si sposta dal campo email
        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                validateEmail(emailField.getText());
            }
        });
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 7;
        panelSignIn.add(emailLabel, gbc);  // Aggiunge emailLabel al panelSignIn
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelSignIn.add(emailField, gbc); // Aggiunge emailField al panelSignIn


        //Creazione della label 'passeordLabel' e della textfield per il campo password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        if(fontBold != null)
            passwordLabel.setFont(fontBold);
        RoundedTextField passwordField = new RoundedTextField(20);
        passwordField.setBackground(new Color(217, 217, 217));
        if(fontRegular != null)
            passwordField.setFont(fontRegular);
        passwordField.setEchoChar('*');
        gbc.insets = new Insets(5, 100, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 7;
        panelSignIn.add(passwordLabel, gbc); // Aggiunge passwordLabel al panelSignIn
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.insets = new Insets(5, 100, 5, 5);
        panelSignIn.add(passwordField, gbc); // Aggiunge passwordField al panelSignIn


        //Crazione del bottone 'SignInButton'
        JButton signInButton = new JButton("Registrati");
        signInButton.setForeground(Color.WHITE);
        if(fontRegular != null)
            signInButton.setFont(fontRegular);
        signInButton.setBackground(new Color(34, 40, 35, 255));
        signInButton.setForeground(Color.WHITE);
        signInButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = 0;
        gbc.insets = new Insets(30, 5, 5, 5);
        panelSignIn.add(signInButton, gbc); // Aggiunge signInButton al panelSignIn


        //Crazione del bottone 'backButton'
        JButton backButton = new JButton("Indietro");
        backButton.setForeground(Color.WHITE);
        if(fontRegular != null)
            backButton.setFont(fontRegular);
        backButton.setBackground(new Color(34, 40, 35, 255));
        backButton.setForeground(Color.WHITE);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        backButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                // Quando con il mouse clicco sul pulsante
                myGestore.backToLogin();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = 0;
        panelSignIn.add(backButton, gbc); // Aggiunge backButton al panelSignIn

        setContentPane(contentPane);
    }


    //Creazione del fontExtraBold
    private void fontExtraBold() {
        try {
            InputStream is = LoginPage.class.getResourceAsStream("/Rubik/static/Rubik-ExtraBold.ttf"); // Sostituisci con il tuo percorso
            fontExtraBold = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(52f); // Modifica la dimensione a piacimento
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontExtraBold);
        } catch (Exception e) {
            e.printStackTrace();
            fontExtraBold = null;
        }
    }


    //Creazione del fontBold
    private void fontBold() {
        try {
            InputStream is = LoginPage.class.getResourceAsStream("/Rubik/static/Rubik-Bold.ttf"); // Sostituisci con il tuo percorso
            fontBold = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24f); // Modifica la dimensione a piacimento
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontBold);
        } catch (Exception e) {
            e.printStackTrace();
            fontBold = null;
        }
    }


    //Creazione del fontRegular
    private void fontRegular() {
        try {
            InputStream is = LoginPage.class.getResourceAsStream("/Rubik/static/Rubik-Regular.ttf"); // Sostituisci con il tuo percorso
            fontRegular = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f); // Modifica la dimensione a piacimento
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontRegular);
        } catch (Exception e) {
            e.printStackTrace();
            fontRegular = null;
        }
    }


    //Creazioned del fontRegularSmall
    private void fontRegularSmall() {
        try {
            InputStream is = LoginPage.class.getResourceAsStream("/Rubik/static/Rubik-Regular.ttf"); // Sostituisci con il tuo percorso
            fontRegularSmall = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(12f); // Modifica la dimensione a piacimento
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontRegularSmall);
        } catch (Exception e) {
            e.printStackTrace();
            fontRegularSmall = null;
        }
    }


    //Creazioned del fontRegularBold
    private void fontRegularBold() {
        try {
            InputStream is = LoginPage.class.getResourceAsStream("/Rubik/static/Rubik-Bold.ttf"); // Sostituisci con il tuo percorso
            fontRegularBold = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(22f); // Modifica la dimensione a piacimento
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fontRegularBold);
        } catch (Exception e) {
            e.printStackTrace();
            fontRegularBold = null;
        }
    }


    //Crazione della funzione validateEmail che controlla se l'email contiene una chiocciola
    private void validateEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Indirizzo email non valido", "Errore", JOptionPane.ERROR_MESSAGE);
            emailField.requestFocus(); // Ora emailField è accessibile
        }
    }


}

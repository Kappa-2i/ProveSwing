import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginPage extends JFrame {
    private MainApp myGestore;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    private JButton loginButton;
    private Font fontRegular;
    private Font fontBold;
    private Font fontExtraBold;
    private Font fontRegularSmall;
    private Font fontRegularBold;


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
            LoginPage frame = new LoginPage(new MainApp());
        });
    }

    public LoginPage(MainApp ma) {
        myGestore = ma;
        setTitle("Login Page");
        setSize(1400, 800);
        setMinimumSize(new Dimension(600, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        fontBold();
        fontRegular();
        fontExtraBold();
        fontRegularSmall();
        fontRegularBold();


        //Inserimento immagine maialino
        ImageIcon imageIcon = new ImageIcon(LoginPage.class.getResource("/noun-piggy-bank-55037.png")); // Sostituisci con il tuo percorso
        JLabel imageLabel = new JLabel(imageIcon) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image scaledImage = imageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                g.drawImage(scaledImage, 0, 0, null);
            }
        };


        // Creazione dei pannelli


        // Creazione del pannello di sfondo e setta il GridBagLayout
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255,255,255));
        contentPane.setLayout(new GridBagLayout());


        //Creazione constraints per il GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();


        //Creazione di un JPanel con BoxLayout per contenere i componenti utili per il login
        JPanel panelLoginWhite = new JPanel(new GridBagLayout());
        panelLoginWhite.setBackground(new Color(255, 255, 255)); // Scegli il colore che preferisci
        panelLoginWhite.setOpaque(true); // Imposta come trasparente per mostrare il gradiente
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, -200, 0, 0);
        gbc.weightx = 0.2;
        gbc.weighty = 1;
        contentPane.add(panelLoginWhite, gbc);//Aggiunge il panelLoginWhite al contentPane


        //Creazione di un JPanel 'PanelLoginRed' con BoxLayout
        JPanel panelLoginRed = new JPanel(new GridBagLayout());
        panelLoginRed.setBackground(new Color(133, 53, 53)); // Scegli il colore che preferisci
        panelLoginRed.setOpaque(true); // Imposta come trasparente per mostrare il gradiente
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.6;
        gbc.weighty = 1;
        contentPane.add(panelLoginRed, gbc);//Aggiunge il panelLoginRed al contentPane


        // Creazione e aggiunta dei componenti sul pannello 'PanelLoginWhite'


        //Creazione della label 'Login'
        JLabel loginLabel = new JLabel("Login");
        if (fontExtraBold != null)
            loginLabel.setFont(fontExtraBold);
        gbc.gridwidth = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(50, 5, 100, 5);
        panelLoginWhite.add(loginLabel, gbc); //aggiunge la loginLabel al panelLoginWhite


        //Creazione della label 'Email' e della textfield per il campo email.
        JLabel emailLabel = new JLabel("Email:");
        RoundedTextField emailField = new RoundedTextField(20);
        if (fontRegularBold != null)
            emailLabel.setFont(fontRegularBold);
        if (fontRegular != null){
            emailField.setFont(fontRegular);
        }
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, emailField.getPreferredSize().height));
        emailField.setBackground(new Color(217, 217, 217));
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelLoginWhite.add(emailLabel, gbc); //Aggiunge la emailLabel al panelLoginWhite
        gbc.gridy = 3;
        panelLoginWhite.add(emailField, gbc); //Aggiunge la emailfield al panelLoginWhite


        //Creaione della label 'Password' e della textfield per il campo password.
        JLabel passwordLabel = new JLabel("Password:");
        RoundedTextField passwordField = new RoundedTextField(20);
        passwordField.setBackground(new Color(217, 217, 217));
        passwordField.setEchoChar('*');
        if (fontRegularBold != null)
            passwordLabel.setFont(fontRegularBold);
        if (fontRegular != null){
            passwordField.setFont(fontRegular);
        }
        gbc.gridy = 4;
        panelLoginWhite.add(passwordLabel, gbc); //Aggiunge la passwordLabel al panelLoginWhite
        gbc.gridy = 5;
        panelLoginWhite.add(passwordField, gbc); //Aggiunge la passwordfield al panelLoginWhite


        // Inizializza il JCheckBox per mostrare/nascondere la password
        showPasswordCheckBox = new JCheckBox("Mostra Password");
        if (fontRegularSmall != null)
            showPasswordCheckBox.setFont(fontRegularSmall);
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Mostra la password
                } else {
                    passwordField.setEchoChar('*'); // Nasconde la password
                }
            }
        });
        gbc.gridy = 6;
        panelLoginWhite.add(showPasswordCheckBox, gbc); //Aggiunge la showPasswordCheckBox al panelLoginWhite


        // Crazione della label 'CreaUtenteLabel'
        JLabel creaUtenteLabel = new JLabel("Crea Utente");
        creaUtenteLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        if (fontRegularSmall != null)
            creaUtenteLabel.setFont(fontRegularSmall);

        // Aggiungi un MouseListener alla label 'creaUtenteLabel'
        creaUtenteLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Quando il mouse entra nella JLabel, applica la sottolineatura
                creaUtenteLabel.setText("<html><u>Crea Utente</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Quando il mouse esce dalla JLabel, rimuovi la sottolineatura
                creaUtenteLabel.setText("Crea Utente");
            }

            @Override
            public void mouseClicked(MouseEvent e){
                myGestore.newUser();
            }
        });
        gbc.gridy = 7;
        panelLoginWhite.add(creaUtenteLabel, gbc); //Aggiunge la creaUtenteLabel al panelLoginWhite


        // Crazione della label 'passwordDimenticataLabel'
        JLabel passwordDimenticataLabel = new JLabel("Password dimenticata?");
        if (fontRegularSmall != null)
            passwordDimenticataLabel.setFont(fontRegularSmall);
        passwordDimenticataLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        if (fontRegularSmall != null)
            creaUtenteLabel.setFont(fontRegularSmall);

        // Aggiungi un MouseListener alla JLabel
        passwordDimenticataLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Quando il mouse entra nella JLabel, applica la sottolineatura
                passwordDimenticataLabel.setText("<html><u>Password dimenticata?</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Quando il mouse esce dalla JLabel, rimuovi la sottolineatura
                passwordDimenticataLabel.setText("Password dimenticata?");
            }
        });
        gbc.gridy = 8;
        panelLoginWhite.add(passwordDimenticataLabel, gbc); //Aggiunge la passwordDimenticatalabel al panelLoginWhite


        // Creazione del button 'loginButton'
        loginButton = new JButton("Accedi");
        if (fontBold != null)
            loginButton.setFont(fontBold);
        loginButton.setBackground(new Color(34, 40, 35, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = 0;
        panelLoginWhite.add(loginButton, gbc); //Aggiunge il loginButton al panelLoginWhite

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
            fontBold = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(16f); // Modifica la dimensione a piacimento
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


    //Creazione del fontRegularSmall
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


    // Creazione finestra messaggio errore
    private void mostraMessageDialog(String testo, String titolo) {
        JOptionPane.showMessageDialog(this, testo, titolo, JOptionPane.INFORMATION_MESSAGE);
    }

}






import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.InputStream;


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
        setSize(1920, 800);
        setMinimumSize(new Dimension(500, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fontBold();
        fontRegular();
        fontExtraBold();
        fontRegularSmall();

        ImageIcon imageIcon = new ImageIcon(LoginPage.class.getResource("/noun-piggy-bank-55037.png")); // Sostituisci con il tuo percorso
        JLabel imageLabel = new JLabel(imageIcon);

        // Crea i componenti
        JLabel loginLabel = new JLabel("Login");
        if (fontExtraBold != null)
            loginLabel.setFont(fontExtraBold);

        JLabel emailLabel = new JLabel("Email:");
        RoundedTextField emailField = new RoundedTextField(20);
        if (fontRegular != null){
            emailLabel.setFont(fontRegular);
            //emailField.setFont(fontRegular);
        }
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, emailField.getPreferredSize().height));
        emailField.setBackground(new Color(217, 217, 217));

        JLabel passwordLabel = new JLabel("Password:");
        RoundedTextField passwordField = new RoundedTextField(20);
        passwordField.setBackground(new Color(217, 217, 217));
        passwordField.setEchoChar('*');


        if (fontRegular != null){
            passwordLabel.setFont(fontRegular);
            //passwordField.setFont(fontRegular);
        }

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


        JLabel creaUtente = new JLabel("Crea Utente");
        creaUtente.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        if (fontRegularSmall != null)
            creaUtente.setFont(fontRegularSmall);

        // Aggiungi un MouseListener alla JLabel
        creaUtente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Quando il mouse entra nella JLabel, applica la sottolineatura
                creaUtente.setText("<html><u>Crea Utente</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Quando il mouse esce dalla JLabel, rimuovi la sottolineatura
                creaUtente.setText("Crea Utente");
            }
        });

        JLabel passwordDimenticata = new JLabel("Password dimenticata?");
        if (fontRegularSmall != null)
            passwordDimenticata.setFont(fontRegularSmall);
        passwordDimenticata.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile
        if (fontRegularSmall != null)
            creaUtente.setFont(fontRegularSmall);
        // Aggiungi un MouseListener alla JLabel
        passwordDimenticata.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Quando il mouse entra nella JLabel, applica la sottolineatura
                passwordDimenticata.setText("<html><u>Password dimenticata?</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Quando il mouse esce dalla JLabel, rimuovi la sottolineatura
                passwordDimenticata.setText("Password dimenticata?");
            }
        });


        loginButton = new JButton("Accedi");
        if (fontBold != null)
            loginButton.setFont(fontBold);
        loginButton.setBackground(new Color(255, 138, 91));
        loginButton.setForeground(Color.WHITE);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia il cursore per indicare che è cliccabile



        // Aggiungi un pannello vuoto come spazio
        JPanel spacer = new JPanel();
        spacer.setOpaque(false); // Rendi il pannello trasparente

        // Crea un JPanel con BoxLayout per contenere i componenti
        JPanel panelLogin = new JPanel(new GridBagLayout());
        panelLogin.setBackground(new Color(255, 255, 255)); // Scegli il colore che preferisci
        panelLogin.setOpaque(true); // Imposta come trasparente per mostrare il gradiente
        panelLogin.setPreferredSize(new Dimension(500, 100));
        GridBagConstraints gbc = new GridBagConstraints();
        // Impostazioni comuni per GridBagConstraints
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(7, 7, 7, 7); // Margini (top, left, bottom, right)


        // Aggiungi i componenti al panelLogin con GridBagConstraints
        // Aggiungi loginLabel
        panelLogin.add(loginLabel, gbc);

        // Aggiungo una label vuota invisibile per fare spazio
        panelLogin.add(spacer, gbc);

        // Aggiungi emailLabel e emailField
        panelLogin.add(emailLabel, gbc);
        panelLogin.add(emailField, gbc);

        // Aggiungi passwordLabel e passwordField
        panelLogin.add(passwordLabel, gbc);
        panelLogin.add(passwordField, gbc);
        panelLogin.add(showPasswordCheckBox, gbc);

        // Aggiungi password dimenticata e crea utente
        panelLogin.add(creaUtente, gbc);
        panelLogin.add(passwordDimenticata, gbc);
        // Per posizionare il loginButton sulla destra
        gbc.fill = GridBagConstraints.NONE; // Non riempire orizzontalmente
        gbc.anchor = GridBagConstraints.LINE_END; // Allinea a destra
        panelLogin.add(loginButton, gbc);


        // ActionListener per il pulsante di login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                if(email.isEmpty())
                    mostraMessageDialog("Inserire l'email", "Attenzione");
                else if(password.isEmpty())
                    mostraMessageDialog("Inserire la password", "Attenzione");
                else
                    myGestore.CheckLogin(email, password);
            }
        });

        // Crea il gradiente di sfondo
        GradientPanel gradientPanel = new GradientPanel(new Color(0x9A, 0xD2, 0xD4), new Color(0x00, 0x7E, 0xA7));
        gradientPanel.setLayout(new BorderLayout());
        gradientPanel.add(panelLogin, BorderLayout.WEST);

        // Imposta il gradientPanel come content pane del JFrame
        setContentPane(gradientPanel);
        gradientPanel.add(imageLabel, BorderLayout.CENTER);

    }

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
    private void mostraMessageDialog(String testo, String titolo) {
        JOptionPane.showMessageDialog(this, testo, titolo, JOptionPane.INFORMATION_MESSAGE);

    }



}




import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private GestoreApplicazione myGestore;

    private JPanel contentPane;
    private JTextField userNameTF;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame(new GestoreApplicazione());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    /**
     * Create the frame.
     */
    public LoginFrame(GestoreApplicazione ga) {
        myGestore=ga;

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        //setMinimumSize(new Dimension(500, 300));
        userNameTF = new JTextField();
        userNameTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        userNameTF.setBounds(146, 54, 200, 30);
        contentPane.add(userNameTF);
        userNameTF.setColumns(10);

        JLabel lblNewLabel = new JLabel("Username");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(33, 57, 101, 23);
        contentPane.add(lblNewLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        passwordField.setEchoChar('*');
        passwordField.setBounds(146, 110, 200, 30);
        contentPane.add(passwordField);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPassword.setBounds(33, 113, 101, 23);
        contentPane.add(lblPassword);

        JButton OKBtn = new JButton("Login");
        OKBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usr = userNameTF.getText();
                String pwd = new String(passwordField.getPassword());

                if(usr.isEmpty())
                    mostraMessageDialog("Inserire la username", "Attenzione");
                else if(pwd.isEmpty())
                    mostraMessageDialog("Inserire la password", "Attenzione");
                else
                    myGestore.controllaLogin(usr, pwd);


            }
        });
        OKBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        OKBtn.setBounds(294, 209, 100, 30);
        contentPane.add(OKBtn);
    }


    private void mostraMessageDialog(String testo, String titolo) {
        JOptionPane.showMessageDialog(this, testo, titolo, JOptionPane.INFORMATION_MESSAGE);

    }
}

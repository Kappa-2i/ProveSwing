import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreaIntestatarioFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private GestoreApplicazione myGestore;
    private JTextField NomeTF;
    private JTextField CognomeTF;
    private JLabel lblNewLabel_2;
    private JTextField textField_2;
    private JLabel lblNewLabel_3;
    private JTextField textField_3;
    private JLabel lblNewLabel_4;
    private JTextField textField_4;
    private JLabel lblNewLabel_5;
    private JTextField textField_5;
    private JButton InserisciBtn;
    private JButton btnAnnulla;
    private JPanel panel1;


    /**
     * Create the frame.
     */
    public CreaIntestatarioFrame(GestoreApplicazione ga) {
        setTitle("Insersci Nuovo Intestatario");
        myGestore = ga;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 801, 610);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        NomeTF = new JTextField();
        NomeTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        NomeTF.setBounds(292, 94, 200, 30);
        contentPane.add(NomeTF);
        NomeTF.setColumns(10);

        JLabel lblNewLabel = new JLabel("Nome Intestatario");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setBounds(137, 97, 140, 23);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Cognome Intestatario");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(137, 141, 140, 23);
        contentPane.add(lblNewLabel_1);

        CognomeTF = new JTextField();
        CognomeTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
        CognomeTF.setColumns(10);
        CognomeTF.setBounds(292, 138, 200, 30);
        contentPane.add(CognomeTF);

        lblNewLabel_2 = new JLabel("Nome Intestatario");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(137, 181, 140, 23);
        contentPane.add(lblNewLabel_2);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_2.setColumns(10);
        textField_2.setBounds(292, 178, 200, 30);
        contentPane.add(textField_2);

        lblNewLabel_3 = new JLabel("Nome Intestatario");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_3.setBounds(137, 221, 140, 23);
        contentPane.add(lblNewLabel_3);

        textField_3 = new JTextField();
        textField_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_3.setColumns(10);
        textField_3.setBounds(292, 218, 200, 30);
        contentPane.add(textField_3);

        lblNewLabel_4 = new JLabel("Nome Intestatario");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_4.setBounds(137, 261, 140, 23);
        contentPane.add(lblNewLabel_4);

        textField_4 = new JTextField();
        textField_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_4.setColumns(10);
        textField_4.setBounds(292, 258, 200, 30);
        contentPane.add(textField_4);

        lblNewLabel_5 = new JLabel("Nome Intestatario");
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_5.setBounds(137, 301, 140, 23);
        contentPane.add(lblNewLabel_5);

        textField_5 = new JTextField();
        textField_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        textField_5.setColumns(10);
        textField_5.setBounds(292, 298, 200, 30);
        contentPane.add(textField_5);

        InserisciBtn = new JButton("Inserisci");
        InserisciBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = NomeTF.getText();
                String cognome = CognomeTF.getText();

                myGestore.creaNuovoIntestatario(nome, cognome);


            }
        });
        InserisciBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        InserisciBtn.setBounds(592, 499, 100, 30);
        contentPane.add(InserisciBtn);

        btnAnnulla = new JButton("Annulla");
        btnAnnulla.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAnnulla.setBounds(475, 499, 100, 30);
        contentPane.add(btnAnnulla);
    }
}

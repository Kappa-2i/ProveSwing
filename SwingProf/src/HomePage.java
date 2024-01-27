import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private GestoreApplicazione myGestore;
    private JPanel panel1;


    /**
     * Create the frame.
     */
    public HomePage(GestoreApplicazione ga) {
        setTitle("Menu Applicazione");
        myGestore = ga;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 866, 570);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton InserisciIntestatarioButton = new JButton("Inserisci nuovo Intestatario");
        InserisciIntestatarioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myGestore.inserisciNuovoIntestatario();
            }
        });
        InserisciIntestatarioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        InserisciIntestatarioButton.setBounds(258, 147, 300, 30);
        contentPane.add(InserisciIntestatarioButton);

        JButton btnVisualizzaIntestatari = new JButton("Visualizza Intestatari");
        btnVisualizzaIntestatari.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnVisualizzaIntestatari.setBounds(258, 187, 300, 30);
        contentPane.add(btnVisualizzaIntestatari);

        JButton btnVisualizzaContiCorrenti = new JButton("Visualizza Conti Correnti");
        btnVisualizzaContiCorrenti.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnVisualizzaContiCorrenti.setBounds(258, 227, 300, 30);
        contentPane.add(btnVisualizzaContiCorrenti);
    }

}

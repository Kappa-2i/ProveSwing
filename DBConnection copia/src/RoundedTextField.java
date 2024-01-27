import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedTextField extends JPasswordField {
    private Shape shape;

    public RoundedTextField(int size) {
        super(size);
        setOpaque(false); // As it paints the background
        setEchoChar((char) 0); // Setta l'EchoChar su 0 per mostrare il testo normale
    }

    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(new Color(217, 217, 217)); // Sostituisci con il colore desiderato per il bordo
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }

    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }
}

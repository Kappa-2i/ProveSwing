import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import javax.swing.*;
public class CustomScrollBarUI extends BasicScrollBarUI {
    @Override
    protected void configureScrollBarColors() {
        // Personalizza i colori della barra di scorrimento qui
        thumbColor = new Color(164, 164, 164); // Colore del "pulsante" della barra di scorrimento
        thumbDarkShadowColor = new Color(153, 153, 153); // Colore dell'ombra superiore del "pulsante"
        thumbHighlightColor = new Color(177, 177, 177); // Colore dell'ombra inferiore del "pulsante"
        thumbLightShadowColor = new Color(153, 153, 153); // Colore dell'ombra laterale del "pulsante"
        trackColor = new Color(217, 217, 217); // Colore della traccia della barra di scorrimento
        trackHighlightColor = new Color(217, 217, 217); // Colore dell'area di trascinamento della traccia
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new JButton(); // Rimuovi i pulsanti di scorrimento
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new JButton(); // Rimuovi i pulsanti di scorrimento
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(thumbColor);
        g2d.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
        g2d.dispose();
    }
}

package Frame.Component.Button;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class RoundedButton extends JButton {
    
    private static final long serialVersionUID = 1L;
    private static final int ARC_WIDTH = 10;
    private static final int ARC_HEIGHT = 10;
    
    public RoundedButton(String text) {
        super(text);
        setOpaque(false);
        setBorderPainted(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(new Font("Verdana",0,12));
        setBackground(new Color(0, 120, 220));
        setForeground(new Color(0,0, 0));

    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RoundRectangle2D.Float shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);
        g2.setColor(getBackground());
        g2.fill(shape);
        g2.setColor(getForeground());
        
        // Centrer le texte à l'intérieur du bouton arrondi
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
        g2.drawString(getText(), x, y);
        
        g2.dispose();
    }
}
                
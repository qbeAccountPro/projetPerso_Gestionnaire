package Frame.Component.ScrollBar;

import java.awt.GradientPaint;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class CardUser_ScrollBarUI extends BasicScrollBarUI {
    private  final Color BUTTON_COLOR = new Color(40, 40, 40);
    private final int THUMB_SIZE = 20;
    private final Color TRACK_COLOR = new Color(30, 30, 30);
    private final Color THUMB_HIGHLIGHT_COLOR = new Color(21, 21, 21);

    @Override
    public Dimension getPreferredSize(JComponent c) {
        return new Dimension(THUMB_SIZE, THUMB_SIZE);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2d = (Graphics2D) g.create();
        GradientPaint gp = new GradientPaint(0, 0,
                    new Color(48, 155, 233), 0,20,
                    new Color(21, 21, 21));
        g2d.setPaint(gp);
        g2d.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
        g2d.setColor(THUMB_HIGHLIGHT_COLOR);
        g2d.drawRect(thumbBounds.x, thumbBounds.y, thumbBounds.width - 1, thumbBounds.height - 1);
        g2d.dispose();
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(TRACK_COLOR);
        g2d.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
        g2d.dispose();
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        JButton button = super.createDecreaseButton(orientation);
        button.setBackground(BUTTON_COLOR);
        return button;
    }
    @Override
    protected JButton createIncreaseButton(int orientation) {
        JButton button = super.createIncreaseButton(orientation);
        button.setBackground(BUTTON_COLOR);
        return button;
    }
}

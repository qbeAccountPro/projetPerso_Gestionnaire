package Frame.Component.Button;

import java.awt.*;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import org.jdesktop.animation.timing.*;

public class ButtonIcon extends JButton {


    public Color getEffectColor() {
        return effectColor;
    }

    public void setEffectColor(Color effectColor) {
        this.effectColor = effectColor;
    }

    private Animator animator;
    private Color effectColor = new Color(200, 0, 0);

    public ButtonIcon() {
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (animator.isRunning()) {
                    animator.stop();
                }
                animator.start();
            }
        });
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                repaint();
            }
        };
        animator = new Animator(400, target);
    }

   

}

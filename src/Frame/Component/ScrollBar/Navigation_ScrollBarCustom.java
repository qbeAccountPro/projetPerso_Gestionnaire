package Frame.Component.ScrollBar;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class Navigation_ScrollBarCustom extends JScrollBar {

    public Navigation_ScrollBarCustom() {
        setUI(new Navigation_ScrollBarUI());
        setPreferredSize(new Dimension(5, 5));
        setForeground(new Color(94, 139, 231));
        setUnitIncrement(20);
        setOpaque(false);
    }
}

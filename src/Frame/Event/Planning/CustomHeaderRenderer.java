package Frame.Event.Planning;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class CustomHeaderRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (column == 0) {
            label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.gray));
        }
        else{
            label.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.gray));
        }   
        label.setBackground(new Color(38, 38, 38));
        label.setForeground(Color.white);
        label.setFont(new Font("Verdana", Font.PLAIN, 15));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(0,35));
        return label;
    }
}


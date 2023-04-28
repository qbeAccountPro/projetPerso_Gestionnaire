package Frame.Event.Planning;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import javax.swing.*;

public class CustomCellRenderer extends DefaultTableCellRenderer {
    private String[][] hour;

    public CustomCellRenderer(String[][] hour) {
        this.hour = hour;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        Boolean condition = false;
        Color evenRow = new Color(20, 20, 20);
        Color oddRow = new Color(30, 30, 30);
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setForeground(Color.white);
        label.setFont(new Font("Verdana", Font.PLAIN, 12));

        if (column == 0 && row % 2 == 0) {
            label.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.gray));
        } else if (column == 0 && row % 2 != 0) {
            label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.gray));
        } else if (row % 2 == 0 && (column == 1 || column == 2)) {
            label.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.gray));
        } else if (row % 2 != 0 && (column == 1 || column == 2)) {
            label.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.gray));
        }
        if (column == 0) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }

        row++;

        for (String[] strings : hour) {
            int startRow = getRowIndex(strings[0]) + 1; //Need +1 to be egal to the slotHour 10 --> 10h to10h30
            int endRow = getRowIndex(strings[1]);
            if (row >= startRow && row <= endRow && column != 0) {
                condition = true;
            }
            if (row == startRow && column != 0) {
                label.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.gray));
                if (column != 0) {
                }
                break;
            } else if (row == endRow && column != 0) {
                label.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.gray));
                break;
            } else if (row >= startRow && row <= endRow && column != 0) {
                label.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.gray));
                break;
            }
        }
        if (condition) {
            label.setBackground(Color.BLUE);
        } else if (row % 2 == 0) {
            label.setBackground(row % 4 == 0 ? evenRow : oddRow);
        } else {
            label.setBackground((row + 1) % 4 == 0 ? evenRow : oddRow);
        }
        return label;
    }

    public static int getRowIndex(String time) {
        int hours = Integer.parseInt(time.substring(0, 2));
        int minutes = Integer.parseInt(time.substring(3));
        int rowIndex = (hours * 2) + (minutes / 30);
        return rowIndex;
    }

}

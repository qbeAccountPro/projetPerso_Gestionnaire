package Frame.Event.Planning;

import javax.swing.table.AbstractTableModel;

public class CustomTableModelPlanning extends AbstractTableModel {
    private static Object[][] data;
    private Object[] columnNames = { "", "Description", "Location" };


    public CustomTableModelPlanning() {
        int sizeColumn = columnNames.length;
        data = new Object[48][sizeColumn];
        int i = 0;
        String startTime = "00:00";
        String endTime = "23:30";
        int intervalMinutes = 30;
        int minutes = Integer.parseInt(startTime.substring(0, 2)) * 60 +
                Integer.parseInt(startTime.substring(3));
        while (minutes <= Integer.parseInt(endTime.substring(0, 2)) * 60 +
                Integer.parseInt(endTime.substring(3))) {
            int hours = minutes / 60;
            int mins = minutes % 60;
            String time = String.format("%02d:%02d", hours, mins);
            data[i][0] = time;
            i++;
            minutes += intervalMinutes;

      }
   
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col].toString();
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public boolean isCellEditable(int row, int col) {
        if (col == columnNames.length - 1) {
            return true;
        } else {
            return false;
        }
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    

}


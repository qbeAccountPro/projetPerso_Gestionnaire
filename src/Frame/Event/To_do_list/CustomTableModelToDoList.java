package Frame.Event.To_do_list;

import Frame.Constructor.*;
import Frame.Database.laodTask;
import Frame.Event.To_do_list.CustomTableModelToDoList;
import java.util.*;
import javax.swing.table.AbstractTableModel;

public class CustomTableModelToDoList extends AbstractTableModel {
    private static Object[][] data;
    private Object[] columnNames;

    public CustomTableModelToDoList() {
        List<String> columnNameList = laodTask.getColumsName();


        int columnNumber = columnNameList.size() - 3;//  <-- +1 for add checkbox and -4 to delete id; archive; date archive, archiveby
        columnNames = new Object[columnNumber];
        for (int i = 1; i < columnNumber; i++) {
            columnNames[i - 1] = columnNameList.get(i);
            columnNames[i-1] = columnNames[i-1].toString().substring(0, 1).toUpperCase() + columnNames[i-1].toString().substring(1);
        }
        columnNames[columnNumber - 1] = "Select"; // -1 to place on the last position but not out the tab
                List<Task> taskList = laodTask.getTaskList();
        int taskNumber = taskList.size();
        for (Task task : taskList) {
            if (task.getArchive() == true) {
                taskNumber--;
            }
        }
        data = new Object[taskNumber][columnNumber];
        int i = 0;
        for (Task task : taskList) {
            if (task.getArchive() != true) {
                data[i][0] = task.getDescription();
                data[i][1] = task.getDeadline();
                data[i][2] = task.getDuration();
                data[i][3] = task.getPriority();
                data[i][4] = task.getThematic();
                data[i][5] = task.getAssignementBy();
                data[i][6] = task.getAssignementFor();
                data[i][7] = false;
                i++;
            }
        }
    }

    public  int getColumnCount() {
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
    
    public void filter(String[] filterValues) {
        List<Task> taskList = laodTask.getTaskList();
             List<Task> filteredTaskList = new ArrayList<>();
        for (Task task : taskList) {
            if (!task.getArchive()) {
                boolean match = true;
                for (int i = 0; i < filterValues.length; i++) {
                    if (!filterValues[i].isEmpty()) {
                        switch (i) {
                            case 0:
                                if (!task.getDescription().toLowerCase().contains(filterValues[i].toLowerCase())) {
                                    match = false;
                                }
                                break;
                            case 1:
                                if (!task.getThematic().toLowerCase().contains(filterValues[i].toLowerCase())) {
                                    match = false;
                                }
                                break;
                            case 2:
                                if (!task.getAssignementBy().toLowerCase().contains(filterValues[i].toLowerCase())) {
                                    match = false;
                                }
                                break;
                            case 3:
                                if (!task.getDuration().toLowerCase().contains(filterValues[i].toLowerCase())) {
                                    match = false;
                                }
                                break;
                            case 4:
                                if (!task.getPriority().toLowerCase().contains(filterValues[i].toLowerCase())) {
                                    match = false;
                                }
                                break;
                            case 5:
                                if (!task.getAssignementFor().toLowerCase().contains(filterValues[i].toLowerCase())) {
                                    match = false;
                                }
                                break;
                        }
                    }
                }
                if (match) {
                    filteredTaskList.add(task);
                }
            }
        }
    
        // Update table model with filtered data
        int filteredTaskNumber = filteredTaskList.size();
        data = new Object[filteredTaskNumber][getColumnCount()];
        int i = 0;
        for (Task task : filteredTaskList) {
            data[i][0] = task.getDescription();
            data[i][1] = task.getDeadline();
            data[i][2] = task.getDuration();
            data[i][3] = task.getPriority();
            data[i][4] = task.getThematic();
            data[i][5] = task.getAssignementBy();
            data[i][6] = task.getAssignementFor();
            data[i][7] = false;
            i++;
        }
    
        fireTableDataChanged();
    }
    
}

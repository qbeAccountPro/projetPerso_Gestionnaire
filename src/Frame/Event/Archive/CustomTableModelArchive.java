package Frame.Event.Archive;

import Frame.Constructor.*;
import Frame.Database.laodTask;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CustomTableModelArchive extends AbstractTableModel {
    private static Object[][] data;
    private Object[] columnNames;

    public CustomTableModelArchive() {
        List<String> columnNameList = laodTask.getColumsName();
        int columnNumber = columnNameList.size() - 5;
        columnNameList.remove("id");
        columnNameList.remove("duration");
        columnNameList.remove("priority");
        columnNameList.remove("assignmentBy");
        columnNameList.remove("assignmentFor");
        columnNameList.remove("archive");
        columnNameList.add("Select");
        columnNames = new Object[columnNumber];
        for (int i = 0; i < columnNumber; i++) {
            columnNames[i] = columnNameList.get(i);
            columnNames[i] = columnNames[i].toString().substring(0, 1).toUpperCase()
                    + columnNames[i].toString().substring(1);
        }

        List<Task> taskList = laodTask.getTaskList();
        int taskNumber = taskList.size();
        for (Task task : taskList) {
            if (task.getArchive() != true) {
                taskNumber--;
            }
        }

        data = new Object[taskNumber][columnNumber];
        int i = 0;
        for (Task task : taskList) {
            if (task.getArchive() == true) {
                data[i][0] = task.getDescription();
                data[i][1] = task.getDeadline();
                data[i][2] = task.getThematic();
                data[i][3] = task.getDateArchive();
                data[i][4] = task.getArchiveBy();
                data[i][5] = false;
                i++;
            }
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

    public void filter(String[] filterValues) {
        List<Task> taskList = laodTask.getTaskList();
          List<Task> filteredTaskList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getArchive()) {
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
                                if (!task.getDeadline().toLowerCase().contains(filterValues[i].toLowerCase())) {
                                    match = false;
                                }
                                break;
                            case 2:
                                if (!task.getThematic().toLowerCase().contains(filterValues[i].toLowerCase())) {
                                    match = false;
                                }
                                break;
                            case 3:
                                if (!task.getDateArchive().toLowerCase().contains(filterValues[i].toLowerCase())) {
                                    match = false;
                                }
                                break;
                            case 4:
                                if (!task.getArchiveBy().toLowerCase().contains(filterValues[i].toLowerCase())) {
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

        int filteredTaskNumber = filteredTaskList.size();
        data = new Object[filteredTaskNumber][getColumnCount()];
        int i = 0;
        for (Task task : filteredTaskList) {
            data[i][0] = task.getDescription();
            data[i][1] = task.getDeadline();
            data[i][2] = task.getThematic();
            data[i][3] = task.getDateArchive();
            data[i][4] = task.getArchiveBy();
            data[i][5] = false;
            i++;
        }

        fireTableDataChanged();
    }

}

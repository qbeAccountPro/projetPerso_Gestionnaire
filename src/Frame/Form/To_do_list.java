package Frame.Form;

import Frame.MainFrame;
import Frame.Component.BackGround.RoundedPanel;
import Frame.Component.Button.*;
import Frame.Component.ScrollBar.*;
import Frame.Constructor.*;
import Frame.Database.laodTask;
import Frame.Event.CheckBoxEvent.CheckBox;
import Frame.Event.DialogError.ErrorDialog;
import Frame.Event.To_do_list.*;

import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.*;
import java.util.List;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.table.*;

public class To_do_list extends JPanel implements DocumentListener {
    private JTextField[] filtres;
    private MainFrame mainFrame;
    private Task newTask;
    private JTable table;
    private CustomTableModelToDoList myCustomTableModel;

    public To_do_list(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponent();
        setOpaque(false);
    }

    private void initComponent() {
        JPanel menuPanel = menuPanel();
        JPanel tabPanel = tabPanel();

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tabPanel)
                        .addComponent(menuPanel, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.PREFERRED_SIZE,
                                Short.MAX_VALUE));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, 80, 80, 80)
                .addGap(10)
                .addComponent(tabPanel));
    }

    private JPanel tabPanel() {
        JPanel tabPanel = new RoundedPanel();
        tabPanel.setBackground(new Color(38,38,38));
        myCustomTableModel = new CustomTableModelToDoList();
        table = new JTable(myCustomTableModel);
        setTableStyle(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBar(new ScrollBarCustom());
        ScrollBarCustom SBC = new ScrollBarCustom();
        SBC.setOrientation(JScrollBar.VERTICAL);
        scrollPane.setVerticalScrollBar(SBC);
        scrollPane.setBackground(new Color(38,38,38));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 0));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        RoundedButton addTaskButton = new RoundedButton("Add Task");
        addTaskButton.setSize(new Dimension(100, 50));
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                new addTask(To_do_list.this);
                mainFrame.setVisible(true);
                if (newTask != null) {
                    laodTask.addTask(newTask);
                    newTask = null;
                    mainFrame.showForm(new To_do_list(mainFrame));
                }
            }
        });

        RoundedButton deleteTaskButton = new RoundedButton("Delete Task");
        deleteTaskButton.setSize(new Dimension(100, 50));
        deleteTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int conteurCheckRow = 0;
                TableModel model = table.getModel();
                for (int i = 0; i < model.getRowCount(); i++) {
                    boolean isChecked = (boolean) model.getValueAt(i, 7);
                    if (isChecked) {
                        Object Description = model.getValueAt(i, 0);
                        laodTask.deleteTask(Description);
                        conteurCheckRow++;
                    }
                }if (conteurCheckRow == 0){
                    new ErrorDialog("Select at least, one task to use the button  'Delete Task' !");
                }
                mainFrame.showForm(new To_do_list(mainFrame));
            }
        });

        RoundedButton modifyTaskButton = new RoundedButton("Modify Task");
        modifyTaskButton.setSize(new Dimension(100, 50));
        modifyTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableModel model = table.getModel();
                int z = 0;
                int x = 0;
                for (int i = 0; i < model.getRowCount(); i++) {
                    boolean isChecked = (boolean) model.getValueAt(i, 7);
                    if (isChecked) {
                        z++;
                        x = i;
                    }
                }
                if (z == 1) {
                    List<Task> taskList = laodTask.getTaskList();
                    boolean condition = false;
                    int indice = 0;
                    while (condition == false) {
                        for (Task task : taskList) {
                            if (task.getDescription().toString().equals(model.getValueAt(x, 0).toString())
                                    && task.getDeadline().toString().equals(model.getValueAt(x, 1).toString())) {
                                condition = true;
                                break;
                            } else {
                                indice++;
                            }
                        }
                    }
                    Task oldTask = taskList.get(indice);
                    mainFrame.setVisible(false);
                    new modifyTask(To_do_list.this, oldTask, mainFrame);
                } else {
                    new ErrorDialog("Select only one task to use the button 'Modify Task' !");
                }
            }
        });

        RoundedButton archiveTaskButton = new RoundedButton("Archive Task");
        archiveTaskButton.setSize(new Dimension(100, 50));
        archiveTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableModel model = table.getModel();
                int counterIsChecked = 0;
                for (int i = 0; i < model.getRowCount(); i++) {
                    boolean isChecked = (boolean) model.getValueAt(i, 7);
                    if (isChecked) {
                        laodTask.archiveTask(model.getValueAt(i, 0));
                        counterIsChecked++;
                    }
                }
                if(counterIsChecked > 0){
                    mainFrame.showForm(new To_do_list(mainFrame));
                }else{
                    new ErrorDialog("You need to select at lest one task to use the 'Archive Button'");
                }
            }
        });
        // #endregion
        GroupLayout layout = new GroupLayout(tabPanel);
        tabPanel.setLayout(layout);
        // #region Layout
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(10)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(scrollPane)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, Short.MAX_VALUE)
                                .addComponent(addTaskButton, 100, 100, 100)
                                .addGap(10)
                                .addComponent(deleteTaskButton, 100, 100, 100)
                                .addGap(10)
                                .addComponent(modifyTaskButton, 100, 100, 100)
                                .addGap(10)
                                .addComponent(archiveTaskButton, 100, 100, 100)))
                .addGap(10));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10)
                .addComponent(scrollPane, 570, 570, 570)
                .addGap(15)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(addTaskButton)
                        .addComponent(deleteTaskButton)
                        .addComponent(modifyTaskButton)
                        .addComponent(archiveTaskButton))
                .addGap(20));
        // #endregion
        return tabPanel;
    }

    private JPanel menuPanel() {
        JPanel menuPanel = new RoundedPanel();
        menuPanel.setBackground(new Color(38,38,38));

        JLabel descriptionLB, assignmentByLB, priorityLB, assignmentForLB, thematicLB, durationLB;
        descriptionLB = new JLabel("Description :");
        assignmentByLB = new JLabel("Assignment by :");
        priorityLB = new JLabel("Priority :");
        assignmentForLB = new JLabel("Assignment for :");
        thematicLB = new JLabel("Thematic :");
        durationLB = new JLabel("Duration :");

        Component[] grpJLB = { descriptionLB, assignmentByLB, priorityLB, assignmentForLB, thematicLB,
                durationLB };
        for (Component c : grpJLB) {
            c.setForeground(new Color(255, 255, 255));
        }

        filtres = new JTextField[6];
        int sizeTextField = 15;
        filtres[0] = new JTextField("", sizeTextField);// deadLineTF
        filtres[1] = new JTextField("", sizeTextField);// assignmentByTF
        filtres[2] = new JTextField("", sizeTextField);// priorityTF -- dur
        filtres[3] = new JTextField("", sizeTextField);// assignmentForTF
        filtres[4] = new JTextField("", sizeTextField);// thematicTF
        filtres[5] = new JTextField("", sizeTextField);// durationTF

        for (JTextField textField : filtres) {
            textField.getDocument().addDocumentListener(this);
        }

        GroupLayout layout = new GroupLayout(menuPanel);
        menuPanel.setLayout(layout);
        // #region Layout
        int HGap = 10;
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(HGap)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGap(HGap)
                        .addComponent(descriptionLB)
                        .addGap(HGap)
                        .addComponent(durationLB))
                .addGap(HGap)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGap(HGap)
                        .addComponent(filtres[0], 100, 100, 100)
                        .addGap(HGap)
                        .addComponent(filtres[3], 100, 100, 100))
                .addGap(HGap)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGap(HGap)
                        .addComponent(priorityLB)
                        .addGap(HGap)
                        .addComponent(thematicLB))
                .addGap(HGap)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGap(HGap)
                        .addComponent(filtres[4], 100, 100, 100)
                        .addGap(HGap)
                        .addComponent(filtres[1], 100, 100, 100))
                .addGap(HGap)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGap(HGap)
                        .addComponent(assignmentByLB)
                        .addGap(HGap)
                        .addComponent(assignmentForLB))
                .addGap(HGap)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGap(HGap)
                        .addComponent(filtres[2], 100, 100, 100)
                        .addGap(HGap)
                        .addComponent(filtres[5], 100, 100, 100))
                .addGap(10, 10, Short.MAX_VALUE)
                .addGap(HGap));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(15)
                .addGroup(layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(descriptionLB)
                        .addGap(10)
                        .addComponent(filtres[0], 20, 20, 20)
                        .addGap(10)
                        .addComponent(priorityLB)
                        .addGap(10)
                        .addComponent(filtres[4], 20, 20, 20)
                        .addGap(10)
                        .addComponent(assignmentByLB)
                        .addGap(10)
                        .addComponent(filtres[2], 20, 20, 20)
                        .addGap(10))
                .addGap(10)
                .addGroup(layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(durationLB)
                        .addGap(10)
                        .addComponent(filtres[3], 20, 20, 20)
                        .addGap(10)
                        .addComponent(thematicLB)
                        .addGap(10)
                        .addComponent(filtres[1], 20, 20, 20)
                        .addGap(10)
                        .addComponent(assignmentForLB)
                        .addGap(10)
                        .addComponent(filtres[5], 20, 20, 20)
                        .addGap(10))
                .addGap(10));
        // #endregion
        return menuPanel;
    }

    public void addNewTask(Task newTask) {
        this.newTask = newTask;
    }

    public void insertUpdate(DocumentEvent e) {
        filtrer();
    }

    public void removeUpdate(DocumentEvent e) {
        filtrer();
    }

    public void changedUpdate(DocumentEvent e) {
        filtrer();
    }

    private void filtrer() {
        String[] filtreValeurs = new String[6];
        for (int i = 0; i < 6; i++) {
            filtreValeurs[i] = filtres[i].getText().toLowerCase();
        }
        myCustomTableModel.filter(filtreValeurs);
        table.setModel(myCustomTableModel);
        setTableStyle(table);
    }

    private void setTableStyle(JTable table) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setBorder(null);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setMinimumSize(new Dimension(600, 240));
        table.setPreferredScrollableViewportSize(new Dimension(800, 240));
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setForeground(Color.white);
        table.setBackground(new Color(38,38,38));

        // Columns Names :
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(38, 38, 38));
        header.setForeground(Color.white);
        header.setFont(new Font("Verdana", Font.PLAIN, 13));
        header.setPreferredSize(new Dimension(0, 35));

        // Column 0
        TableColumn descriptionColumn = table.getColumnModel().getColumn(0);
        descriptionColumn.setMinWidth(260);

        // Column 1
        TableColumn deadlineColumn = table.getColumnModel().getColumn(1);
        deadlineColumn.setPreferredWidth(125);

        // Column 2
        TableColumn durationColumn = table.getColumnModel().getColumn(2);
        durationColumn.setPreferredWidth(40);

        // Column 3
        TableColumn priorityColumn = table.getColumnModel().getColumn(3);
        priorityColumn.setPreferredWidth(20);

        // Column 4
        TableColumn thematicColumn = table.getColumnModel().getColumn(4);
        thematicColumn.setPreferredWidth(40);

        // Column 5
        TableColumn assignementByColumn = table.getColumnModel().getColumn(5);
        assignementByColumn.setPreferredWidth(80);

        // Column 6
        TableColumn assignementForColumn = table.getColumnModel().getColumn(6);
        assignementForColumn.setPreferredWidth(80);

        // Column 7
        TableColumn selectCOlumn = table.getColumnModel().getColumn(7);
        selectCOlumn.setPreferredWidth(20);
        selectCOlumn.setCellRenderer((TableCellRenderer) new TableCellRenderer() {
            final JCheckBox checkbox = new JCheckBox();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                checkbox.setSelected((Boolean) value);
                checkbox.setHorizontalAlignment(JCheckBox.HORIZONTAL);
                checkbox.setVerticalAlignment(JCheckBox.VERTICAL);
                selectCOlumn.setCellEditor(new CheckBox(checkbox));
                return checkbox;
            }
        });

        // Color rows
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            Color evenRow = new Color(20, 20, 20); // Couleur pour les lignes impaires
            Color oddRow = new Color(30, 30, 30); // Couleur pour les lignes paires

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? evenRow : oddRow);

                }
                setFont(new Font("Verdana", Font.PLAIN, 12));
                setBorder(new EmptyBorder(0, 5, 0, 5));
                return c;
            }
        });
    }

}

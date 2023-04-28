package Frame.Form;

import Frame.MainFrame;
import Frame.Component.BackGround.RoundedPanel;
import Frame.Component.Button.*;
import Frame.Component.ScrollBar.*;
import Frame.Constructor.*;
import Frame.Database.laodTask;
import Frame.Event.Archive.CustomTableModelArchive;
import Frame.Event.Archive.addArchiveTask;
import Frame.Event.Archive.modifyArchiveTask;
import Frame.Event.CheckBoxEvent.CheckBox;
import Frame.Event.DialogError.ErrorDialog;

import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.table.*;

public class Archive extends JPanel implements DocumentListener {
    private JTextField[] filtres;
    private MainFrame mainFrame;
    private Task newTask;
    private JTable table;
    private CustomTableModelArchive myCustomTableModel;

    public Archive(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponent();
        setOpaque(false);
    }

    public void initComponent() {
        JPanel menuPanel = menuPanel();
        JPanel tabPanel = tabPanel();
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        // #region Layout
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(tabPanel)
                        .addComponent(menuPanel, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.PREFERRED_SIZE,
                                Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, 80, 80, 80)
                .addGap(10)
                .addComponent(tabPanel)
                
                );
        // #endregion
    }

    public JPanel tabPanel() {
        JPanel backgroundPanel = new RoundedPanel();
        backgroundPanel.setBackground(new Color(38,38,38));
        myCustomTableModel =  new CustomTableModelArchive();
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

        // #region Button Creation

        RoundedButton addTaskButton = new RoundedButton("Add task");
        addTaskButton.setSize(new Dimension(100, 50));
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setVisible(false);
                new addArchiveTask(Archive.this);
                mainFrame.setVisible(true);
                if (newTask != null) {
                    laodTask.addTask(newTask);
                    newTask = null;
                    mainFrame.showForm(new Archive(mainFrame));
                }
            }
        });

        RoundedButton deleteTaskButton = new RoundedButton("Delete tasks");
        deleteTaskButton.setSize(new Dimension(100, 50));
        deleteTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int conteurCheckRow = 0;
                TableModel model = table.getModel();
                for (int i = 0; i < model.getRowCount(); i++) {
                    boolean isChecked = (boolean) model.getValueAt(i, 5);
                    if (isChecked) {
                        Object Description = model.getValueAt(i, 0);
                        laodTask.deleteTask(Description);
                        conteurCheckRow++;
                    }
                }
                if (conteurCheckRow == 0){
                    new ErrorDialog("Select at least, one task to use the button  'Delete tasks' !");
                }
                mainFrame.showForm(new Archive(mainFrame));
            }
        });

        RoundedButton modifyTaskButton = new RoundedButton("Modify task");
        modifyTaskButton.setSize(new Dimension(100, 50));
        modifyTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableModel model = table.getModel();
                int conteurCheckValue = 0;
                int x = 0;

                for (int i = 0; i < model.getRowCount(); i++) {
                    boolean isChecked = (boolean) model.getValueAt(i, 5);
                    if (isChecked) {
                        conteurCheckValue++;
                        x = i;
                    }
                }
                if (conteurCheckValue == 1) {
                    List<Task> taskList = laodTask.getTaskList();
                    boolean condition = false;
                    int indice = 0;
                    while(condition == false){
                        for (Task task : taskList) {
                            if(task.getDescription().toString().equals(model.getValueAt(x, 0).toString()) && task.getDateArchive().toString().equals(model.getValueAt(x, 3).toString())){
                                condition = true;
                                break;
                            }
                            else{
                                indice++;
                            }
                        }
                    }
                    Task oldTask = taskList.get(indice);
                    mainFrame.setVisible(false);
                    new modifyArchiveTask(Archive.this, oldTask, mainFrame);
                } else {
                    new ErrorDialog("Select only one task to use the button 'Modify Task'!");
                }
            }
        });

        RoundedButton unarchiveButton = new RoundedButton("Unarchive");
        unarchiveButton.setSize(new Dimension(100, 50));
        unarchiveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableModel model = table.getModel();
                int conteurCheckValue = 0;
                int x = 0;
                for (int i = 0; i < model.getRowCount(); i++) {
                    boolean isChecked = (boolean) model.getValueAt(i, 5);
                    if (isChecked) {
                        conteurCheckValue++;
                        x = i;
                    }
                }
                if (conteurCheckValue == 1) {
                    List<Task> taskList = laodTask.getTaskList();
                    boolean getTheGoodTask = false;
                    int indice = 0;
                    while(getTheGoodTask == false){
                        for (Task task : taskList) {
                            if(task.getDescription().toString().equals(model.getValueAt(x, 0).toString()) && task.getDateArchive().toString().equals(model.getValueAt(x, 3).toString())){
                                getTheGoodTask = true;
                                break;
                            }
                            else{
                                indice++;
                            }
                        }
                    }
                    Task task = taskList.get(indice);
                    laodTask.unarchiveTask(task);
                    mainFrame.showForm(new Archive(mainFrame));

                } else {
                    new ErrorDialog("Select one task to use the button Unarchive !");
                }

            }
        });
       // #endregion

        GroupLayout layout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(layout);

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
                                .addComponent(unarchiveButton, 100, 100, 100)))
                .addGap(10));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(10)
                .addComponent(scrollPane, 570, 570, 570)
                .addGap(15)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(addTaskButton)
                        .addComponent(deleteTaskButton)
                        .addComponent(modifyTaskButton)
                        .addComponent(unarchiveButton))
                .addGap(20));
        // #endregion
        return backgroundPanel;
    }

    public JPanel menuPanel() {
        JPanel panel = new RoundedPanel();
        panel.setBackground(new Color(38,38,38));
        JLabel descriptionLB, deadlineLB, thematicLB, dateArchiveLB, archiveByLB;
        descriptionLB = new JLabel("Description :");
        deadlineLB = new JLabel("DeadLine :");
        thematicLB = new JLabel("Thematic :");
        dateArchiveLB = new JLabel("Date archive :");
        archiveByLB = new JLabel("Archive by :");

        Component[] grpJLB = { descriptionLB, deadlineLB, thematicLB, dateArchiveLB,
                archiveByLB };
        for (Component c : grpJLB) {
            c.setForeground(Color.WHITE);
        }

        filtres = new JTextField[5];
        int sizeTextField = 15;
        for(int i =0; i <5; i++){
            filtres[i] = new JTextField("", sizeTextField);
        }
        for (JTextField textField : filtres) {
            textField.getDocument().addDocumentListener(this);
        }

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        // #region Layout
        int HGap = 10;
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(HGap)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGap(HGap)
                        .addComponent(descriptionLB)
                        .addGap(HGap)
                        .addComponent(dateArchiveLB))
                .addGap(HGap)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGap(HGap)
                        .addComponent(filtres[0], 100, 100, 100)
                        .addGap(HGap)
                        .addComponent(filtres[3], 100, 100, 100))
                .addGap(HGap)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGap(HGap)
                        .addComponent(deadlineLB)
                        .addGap(HGap)
                        .addComponent(archiveByLB))
                .addGap(HGap)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGap(HGap)
                        .addComponent(filtres[1], 100, 100, 100)
                        .addGap(HGap)
                        .addComponent(filtres[4], 100, 100, 100))
                .addGap(HGap)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGap(HGap)
                        .addComponent(thematicLB))
                .addGap(HGap)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addGap(HGap)
                        .addComponent(filtres[2], 100, 100, 100))
                .addGap(10, 10, Short.MAX_VALUE)
                .addGap(HGap));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(15)
                .addGroup(layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(descriptionLB)
                        .addGap(10)
                        .addComponent(filtres[0], 20, 20, 20)
                        .addGap(10)
                        .addComponent(deadlineLB)
                        .addGap(10)
                        .addComponent(filtres[1], 20, 20, 20)
                        .addGap(10)
                        .addComponent(thematicLB)
                        .addGap(10)
                        .addComponent(filtres[2], 20, 20, 20)
                        .addGap(10))
                .addGap(10)
                .addGroup(layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(dateArchiveLB)
                        .addGap(10)
                        .addComponent(filtres[3], 20, 20, 20)
                        .addGap(10)
                        .addComponent(archiveByLB)
                        .addGap(10)
                        .addComponent(filtres[4], 20, 20, 20)
                        .addGap(10))
                .addGap(10));
        // #endregion
        return panel;
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

    public void filtrer() {
        String[] filtreValeurs = new String[5];
        for (int i = 0; i < 5; i++) {
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
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Verdana", Font.PLAIN, 13));
        header.setPreferredSize(new Dimension(0, 35));

        // Column 0
        TableColumn descriptionColumn = table.getColumnModel().getColumn(0);
        descriptionColumn.setMinWidth(260);

        // Column 1
        TableColumn deadlineColumn = table.getColumnModel().getColumn(1);
        deadlineColumn.setPreferredWidth(100);

        // Column 2
        TableColumn thematicColumn = table.getColumnModel().getColumn(2);
        thematicColumn.setPreferredWidth(30);

        // Column 3
        TableColumn dateArchiveColumn = table.getColumnModel().getColumn(3);
        dateArchiveColumn.setPreferredWidth(100);

        // Column 4
        TableColumn archiveBy = table.getColumnModel().getColumn(4);
        archiveBy.setPreferredWidth(100);

        // Column 5
        TableColumn selectCOlumn = table.getColumnModel().getColumn(5);
        selectCOlumn.setPreferredWidth(15);
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

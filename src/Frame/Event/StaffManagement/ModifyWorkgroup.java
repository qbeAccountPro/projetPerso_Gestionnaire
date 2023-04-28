package Frame.Event.StaffManagement;

import Frame.Component.BackGround.RoundedPanel;
import Frame.Component.Button.RoundedButton;
import Frame.Component.ScrollBar.ScrollBarCustom;
import Frame.Database.loadUser;
import Frame.Event.CheckBoxEvent.CheckBox;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.awt.Component;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import javax.swing.*;

public class ModifyWorkgroup extends RoundedPanel {
    private RoundedPanel centerPanel;
    private JDialog dialog;

    public ModifyWorkgroup(JDialog dialog) {
        this.dialog = dialog;
        initComponents();
        setBackground(new Color(0, 0, 0));

    }

    private void initComponents() {

        // Set up the backgrounds
        centerPanel = new RoundedPanel();

        centerPanel.setBackground(new Color(38, 38, 38));

        // Set up TITLE
        JLabel title = new JLabel("Select the job to be modified :");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Verdana", Font.PLAIN, 14));

        // Set up NAMES COLUMNS
        String[] columnNames = { "Job", "Select :" };

        // Recup user list inside a table
        List<String> jobList = loadUser.getJobEnum();
        Object[][] jobTab = new Object[jobList.size()][2];
        int i = 0;
        for (String job : jobList) {
            jobTab[i][0] = job;
            jobTab[i][1] = false;
            i++;
        }

        // Create the table
        JTable table = new JTable(jobTab, columnNames);
        setTableStyle(table, jobList);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBar(new ScrollBarCustom());
        ScrollBarCustom SBC = new ScrollBarCustom();
        SBC.setOrientation(JScrollBar.VERTICAL);
        scrollPane.setVerticalScrollBar(SBC);
        scrollPane.setBackground(new Color(38, 38, 38));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 0));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
       

        RoundedButton exitButton = new RoundedButton("Exit");
        exitButton.setSize(new Dimension(100, 20));
        exitButton.setBackground(new Color(255, 56, 106));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        GroupLayout Layout = new GroupLayout(centerPanel);
        centerPanel.setLayout(Layout);
        Layout.setHorizontalGroup(Layout.createParallelGroup(Alignment.LEADING)
                .addGroup(Layout.createSequentialGroup()
                        .addGap(20, 20, Short.MAX_VALUE)
                        .addComponent(title)
                        .addGap(20, 20, Short.MAX_VALUE))
                .addGroup(Layout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(scrollPane)
                        .addGap(20))
                .addGroup(Layout.createSequentialGroup()
                        .addGap(20, 20, Short.MAX_VALUE)
                        .addComponent(exitButton)
                        .addGap(20)));
        Layout.setVerticalGroup(Layout.createSequentialGroup()
                .addGap(10)
                .addComponent(title)
                .addGap(10)
                .addComponent(scrollPane)
                .addGap(10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(exitButton))
                .addGap(10));

        GroupLayout Layout_2 = new GroupLayout(this);
        this.setLayout(Layout_2);
        Layout_2.setHorizontalGroup(Layout_2.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(centerPanel)
                .addGap(10, 10, 10));

        Layout_2.setVerticalGroup(Layout_2.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(centerPanel)
                .addGap(10, 10, 10));

    }

    private void setTableStyle(JTable table, List<String> jobList) {
        
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
 
        table.setPreferredScrollableViewportSize(new Dimension(500, 150));
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setForeground(Color.white);
        table.setBackground(new Color(38, 38, 38));

        // Columns Names :
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(38, 38, 38));
        header.setForeground(new Color(250, 250, 250));
        header.setFont(new Font("Verdana", Font.PLAIN, 13));
        header.setPreferredSize(new Dimension(0, 35));

        // Set up column 1
        TableColumn checkColumn = table.getColumnModel().getColumn(1);
        checkColumn.setMaxWidth(70);
        checkColumn.setCellRenderer((TableCellRenderer) new TableCellRenderer() {
            final JCheckBox checkbox = new JCheckBox();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                checkbox.setSelected((Boolean) value);
                checkbox.setHorizontalAlignment(JCheckBox.HORIZONTAL);
                checkbox.setVerticalAlignment(JCheckBox.VERTICAL);
                checkbox.addActionListener(null);
                if ((Boolean) value == true) {
                    String job = jobList.get(row);
                    JDialog dialog1 = new JDialog();
                    dialog1.setModal(true);
                    dialog1.setTitle("Modify workgroup");
                    dialog1.getContentPane().add(new oldNewWorkgroup(dialog1, job));
                    dialog1.setUndecorated(true);
                    dialog1.setBackground(new Color(0, 0, 0, 0));
                    dialog1.pack();
                    dialog1.setLocationRelativeTo(null);
                    dialog.dispose();
                    dialog1.setVisible(true);
                    dialog1.dispose();
                }
                ;
                checkColumn.setCellEditor(new CheckBox(checkbox));
                return checkbox;
            }
        });

        // Color rows
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            Color evenRow = new Color(20, 20, 20); 
            Color oddRow = new Color(30, 30, 30); 

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

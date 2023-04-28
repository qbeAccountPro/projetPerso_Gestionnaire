package Frame.Event.StaffManagement;

import Frame.Component.BackGround.RoundedPanel;
import Frame.Component.Button.RoundedButton;
import Frame.Component.ScrollBar.ScrollBarCustom;
import Frame.Constructor.User;
import Frame.Database.loadUser;
import Frame.Event.CheckBoxEvent.CheckBox;
import Frame.Form.Staff_Management;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Component;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import javax.swing.*;

public class ModifyUser extends RoundedPanel {
    private RoundedPanel centerPanel;
    private JDialog dialog;
    private String condition;


    public ModifyUser(JDialog dialog, Staff_Management staff_Management, String condition) {
        this.dialog = dialog;
        this.condition = condition;
        initComponents();
        setBackground(new Color(0,0,0));

    }

    private void initComponents() {
        centerPanel = new RoundedPanel();
        centerPanel.setBackground(new Color(38,38,38));
        JLabel title = new JLabel("Check the checkbox for the user to be modified :");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Verdana", Font.PLAIN, 14));
        List<String> ColumnNames_List = loadUser.getColumsName();
        ColumnNames_List.remove("pathIcon");
        ColumnNames_List.remove("password");
        int numberColumns = ColumnNames_List.size();
        Object[] columnNames = new Object[numberColumns];
        for (int i = 0; i < numberColumns - 1; i++) {
            if (ColumnNames_List.get(i + 1) != "pathIcon") {
                
                columnNames[i] = ColumnNames_List.get(i + 1);
            }

        }
        columnNames[numberColumns - 1] = "Modify";
        List<User> listUsers = loadUser.getUserList();
        int numberUsers = 0;
        for (User user : listUsers) {
           
            if (user.getJob().equals(condition)) {
                numberUsers++;
            }
        }
        Object[][] userTab = new Object[numberUsers][numberColumns];
        int i = 0;
        List<User> userTabList = new ArrayList<User>();
        for (User user : listUsers) {
            if (user.getJob().equals(condition)) {
            userTab[i][0] = user.getName();
            userTab[i][1] = user.getSurname();
            userTab[i][2] = user.getCompany();
            userTab[i][3] = user.getJob();
            userTab[i][4] = user.getEmail();
            userTab[i][5] = user.getNumberphone();
            userTab[i][6] = user.getSpeciality();
            userTab[i][7] = false;
            userTabList.add(user);
            i++;
        }
        }
        
        JTable table = new JTable(userTab, columnNames);
        setTableStyle(table, userTabList);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBar(new ScrollBarCustom());
        ScrollBarCustom SBC = new ScrollBarCustom();
        SBC.setOrientation(JScrollBar.VERTICAL);
        scrollPane.setVerticalScrollBar(SBC);
        scrollPane.setBackground(new Color(38,38,38));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 0));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        RoundedButton exitButton = new RoundedButton("Exit");
        exitButton.setSize(new Dimension(200, 20));
        exitButton.setBackground(new Color(255, 56, 106));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
     

        GroupLayout Layout = new GroupLayout(centerPanel);
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
                        .addComponent(exitButton, 100, 100, 100)
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

        centerPanel.setLayout(Layout);

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

    private void setTableStyle(JTable table, List<User> userTabList) {
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setMinimumSize(new Dimension(600, 240));
        table.setPreferredScrollableViewportSize(new Dimension(800, 240));
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setForeground(Color.white);
        table.setBackground(new Color(38,38,38));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setBorder(null);

        // Columns Names :
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(38,38,38));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Verdana", Font.PLAIN, 13));
        header.setPreferredSize(new Dimension(0, 35));

        // Column 0
        TableColumn nameColumn = table.getColumnModel().getColumn(0);
        nameColumn.setMinWidth(20);

        // Column 1
        TableColumn surnameColumn = table.getColumnModel().getColumn(1);
        surnameColumn.setPreferredWidth(50);

        // Column 2
        TableColumn companyColumn = table.getColumnModel().getColumn(2);
        companyColumn.setPreferredWidth(50);

        // Column 3
        TableColumn jobColumn = table.getColumnModel().getColumn(3);
        jobColumn.setPreferredWidth(75);

        // Column 4
        TableColumn emailColumn = table.getColumnModel().getColumn(4);
        emailColumn.setPreferredWidth(150);

        // Column 5
        TableColumn numberPhoneColumn = table.getColumnModel().getColumn(5);
        numberPhoneColumn.setPreferredWidth(80);

        // Column 6
        TableColumn specialityColumn = table.getColumnModel().getColumn(6);
        specialityColumn.setPreferredWidth(50);

        // Column 7
        TableColumn ModifyColumn = table.getColumnModel().getColumn(7);
        ModifyColumn.setPreferredWidth(20);
        ModifyColumn.setCellRenderer((TableCellRenderer) new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                JCheckBox checkbox = new JCheckBox();
                checkbox.setSelected((Boolean) value);
                checkbox.setHorizontalAlignment(JCheckBox.HORIZONTAL);
                checkbox.setVerticalAlignment(JCheckBox.VERTICAL);
                checkbox.addActionListener(null);
                if ((Boolean) value == true) {
                    User user = userTabList.get(row);
                    JDialog dialog1 = new JDialog();
                    dialog1.setModal(true);
                    dialog1.setTitle("Modify user");
                    dialog1.getContentPane().add(new oldNewUserData(dialog1, user,condition));
                    dialog1.setUndecorated(true);
                    dialog1.setBackground(new Color(0, 0, 0, 0));
                    dialog1.pack();
                    dialog1.setLocationRelativeTo(null);
                    dialog.dispose();
                    dialog1.setVisible(true);    
                    dialog1.dispose();
                };

                ModifyColumn.setCellEditor(new CheckBox(checkbox));
                return checkbox;
            }
        });

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            Color evenRow = new Color(20,20,20); 
            Color oddRow = new Color(30,30,30); 

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

package Frame.Event.StaffManagement;

import Frame.Component.BackGround.RoundedPanel;
import Frame.Component.Button.RoundedButton;
import Frame.Database.loadUser;
import Frame.Event.DialogError.ErrorDialog;
import Frame.Form.Staff_Management;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.GroupLayout.Alignment;
import javax.swing.*;

public class AddWorkgroup extends JPanel {
    private RoundedPanel centerPanel, backgroundPanel;
    private JDialog dialog;

    public AddWorkgroup(JDialog dialog, Staff_Management staff_Management) {
        this.dialog = dialog;

        initComponents();
        setBackground(new Color(0, 0, 0, 0));
    }

    private void initComponents() {

        centerPanel = new RoundedPanel();
        backgroundPanel = new RoundedPanel();
        centerPanel.setBackground(new Color(38,38,38));
        backgroundPanel.setBackground(new Color(0, 0, 0));

        Color titreColor = Color.white;
        JLabel userName = new JLabel("Name :");
        JLabel pageTitle = new JLabel("Add a new Workgroup !");
        JTextField tfName = new JTextField("New name", 10);

        RoundedButton submiteButton = new RoundedButton("Submit");
        submiteButton.setPreferredSize(new Dimension(100, 50));
        submiteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<String> jobList = loadUser.getJobEnum();
                if (jobList.contains(tfName.getText())) {
                    setVisible(false);
                    new ErrorDialog("There is already a name : " + tfName.getText());
                    setVisible(true);
                } else {
                    loadUser.AddWorkGroup(tfName.getText());
                    dialog.dispose();
                }

            }
        });

        RoundedButton exitButton = new RoundedButton("Exit");
        exitButton.setSize(new Dimension(100, 50));
        exitButton.setBackground(new Color(255, 56, 106));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        userName.setForeground(titreColor);
        pageTitle.setForeground(titreColor);
        tfName.setForeground(Color.BLACK);
        pageTitle.setFont(new Font("Verdana", 1, 15));

        GroupLayout Layout = new GroupLayout(centerPanel);
        centerPanel.setLayout(Layout);
        Layout.setHorizontalGroup(Layout.createParallelGroup(Alignment.LEADING)
                .addGroup(Layout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(pageTitle)
                        .addGap(20))
                .addGroup(Layout.createSequentialGroup()
                        .addGap(10)
                        .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(userName))
                        .addGap(10)
                        .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(tfName, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                        .addGap(10)
                        .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(submiteButton, 80, 80, 80)
                                .addComponent(exitButton, 80, 80, 80))
                        .addGap(10))
                .addGap(10));
        Layout.setVerticalGroup(Layout.createSequentialGroup()
                .addGap(10)
                .addComponent(pageTitle)
                .addGap(10)
                .addGroup(Layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(userName)
                        .addComponent(tfName)
                        .addComponent(submiteButton))
                .addGap(10)
                .addGroup(Layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(exitButton))
                .addGap(10));

        GroupLayout Layout_2 = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(Layout_2);
        Layout_2.setHorizontalGroup(Layout_2.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(centerPanel)
                .addGap(10, 10, 10));
        Layout_2.setVerticalGroup(Layout_2.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(centerPanel)
                .addGap(10, 10, 10));

        this.add(backgroundPanel);
    }
}

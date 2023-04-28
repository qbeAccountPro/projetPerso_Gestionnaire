package Frame.Event.StaffManagement;

import Frame.Component.BackGround.RoundedPanel;
import Frame.Component.Button.RoundedButton;
import Frame.Database.loadUser;
import Frame.Event.DialogError.ErrorDialog;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.awt.Component;

import javax.swing.GroupLayout.Alignment;
import javax.swing.*;

public class oldNewWorkgroup extends JPanel {
    private RoundedPanel centerPanel;
    private JDialog dialog;
    private String jobName;

    public oldNewWorkgroup(JDialog dialog, String jobName) {
        this.dialog = dialog;
        this.jobName = jobName;
        initComponents();
        setBackground(new Color(0, 0, 0));

    }

    private void initComponents() {

        centerPanel = new RoundedPanel();
        centerPanel.setBackground(new Color(38, 38, 38));

        JLabel titleJob, oldPageTitle, newPageTitle;
        titleJob = new JLabel("Workroup :");
        oldPageTitle = new JLabel("Old Workgroup !");
        newPageTitle = new JLabel("New Workgroup !");

        JTextField oldJob, newJob;
        oldJob = new JTextField(jobName, 15);
        newJob = new JTextField(jobName, 15);

        RoundedButton submiteButton = new RoundedButton("Submit");
        submiteButton.setPreferredSize(new Dimension(100, 50));
        submiteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<String> jobList = loadUser.getJobEnum();
                if (jobList.contains(newJob.getText())) {
                    setVisible(false);
                    new ErrorDialog("There is already a name : " + newJob.getText());
                    setVisible(true);
                } else {
                    loadUser.modifyWorkgroup(oldJob.getText(), newJob.getText());
                    dialog.dispose();
                }

            }
        });

        RoundedButton exitButton = new RoundedButton("Exit");
        exitButton.setPreferredSize(new Dimension(100, 50));
        exitButton.setBackground(new Color(255, 56, 106));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        Component[] grpJLB = { titleJob, oldPageTitle, newPageTitle };
        for (Component c : grpJLB) {
            c.setForeground(Color.WHITE);
        }
        Component[] grpTF = { oldJob, newJob };
        for (Component c : grpTF) {
            c.setForeground(Color.BLACK);
        }

        oldJob.setEditable(false);

        oldPageTitle.setFont(new Font("Verdana", 1, 13));
        newPageTitle.setFont(new Font("Verdana", 1, 13));

        GroupLayout Layout = new GroupLayout(centerPanel);
        centerPanel.setLayout(Layout);

        Layout.setHorizontalGroup(Layout.createParallelGroup(Alignment.LEADING)
                .addGroup(Layout.createSequentialGroup()
                        .addGap(10)
                        .addGroup(Layout.createParallelGroup(Alignment.LEADING).addComponent(titleJob))
                        .addGap(10)
                        .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(oldPageTitle)
                                .addComponent(oldJob))
                        .addGap(50)
                        .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(newPageTitle)
                                .addComponent(newJob))
                        .addGap(10))
                .addGroup(Layout.createSequentialGroup()
                        .addGap(20, 20, Short.MAX_VALUE)
                        .addComponent(submiteButton)
                        .addGap(20)
                        .addComponent(exitButton)
                        .addGap(20))
                .addGap(10)
                .addGap(10));

        Layout.setVerticalGroup(Layout.createSequentialGroup()
                .addGap(10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(oldPageTitle)
                        .addComponent(newPageTitle))
                .addGap(10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(titleJob)
                        .addComponent(oldJob)
                        .addComponent(newJob))
                .addGap(10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(submiteButton)
                        .addComponent(exitButton))
                .addGap(10));

        GroupLayout Layout_2 = new GroupLayout(this);
        this.setLayout(Layout_2);

        Layout_2.setHorizontalGroup(Layout_2.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10));

        Layout_2.setVerticalGroup(Layout_2.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(centerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10));


    }

}

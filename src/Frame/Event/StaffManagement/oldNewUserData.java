package Frame.Event.StaffManagement;

import Frame.Component.BackGround.RoundedPanel;
import Frame.Component.Button.RoundedButton;
import Frame.Constructor.User;
import Frame.Database.loadUser;
import Frame.Image.Avatar.FolderTrackerURL;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.awt.Component;
import java.util.List;
import javax.swing.GroupLayout.Alignment;
import javax.swing.*;

public class oldNewUserData extends RoundedPanel {
    private RoundedPanel centerPanel;
    private JDialog dialog;
    private User oldUser;

    public oldNewUserData(JDialog dialog, User oldUser, String condition) {
        this.dialog = dialog;
        this.oldUser = oldUser;
        initComponents();
        setBackground(new Color(0, 0, 0));

    }

    private void initComponents() {

        centerPanel = new RoundedPanel();
        centerPanel.setBackground(new Color(38, 38, 38));

        JLabel userName, userSurname, userJob, userCompany, userEmail, userNumberPhone, userPathIcon, userSpeciality,
                oldPageTitle, newPageTitle;
        userName = new JLabel("Name :");
        userSurname = new JLabel("Surname :");
        userJob = new JLabel("Job :");
        userCompany = new JLabel("Company :");
        userEmail = new JLabel("Email :");
        userNumberPhone = new JLabel("Num. phone :");
        userPathIcon = new JLabel("Icon :");
        userSpeciality = new JLabel("Speciality :");
        oldPageTitle = new JLabel("Old value !");
        newPageTitle = new JLabel("New value !");

        // Recup the end of pathicon
        String[] segments = oldUser.getPathIcon().split("/");
        String filename = segments[segments.length - 1];

        JTextField oldName, oldSurname, oldJob, oldCompany, oldEmail, oldNumberPhone, oldPathIcon,
                oldSpeciality;
        JTextField newName, newSurname, newCompany, newEmail, newNumberPhone, newSpeciality;
        int sizeTextField = 15;
        oldName = new JTextField(oldUser.getName(), sizeTextField);
        oldSurname = new JTextField(oldUser.getSurname(), sizeTextField);
        oldJob = new JTextField(oldUser.getJob(), sizeTextField);
        oldCompany = new JTextField(oldUser.getCompany(), sizeTextField);
        oldEmail = new JTextField(oldUser.getEmail(), sizeTextField);
        oldNumberPhone = new JTextField(oldUser.getNumberphone(), sizeTextField);
        oldPathIcon = new JTextField(filename, sizeTextField);
        oldSpeciality = new JTextField(oldUser.getSpeciality(), sizeTextField);
        //
        newName = new JTextField(oldName.getText(), sizeTextField);
        newSurname = new JTextField(oldSurname.getText(), sizeTextField);
        newCompany = new JTextField(oldCompany.getText(), sizeTextField);
        newEmail = new JTextField(oldEmail.getText(), sizeTextField);
        newNumberPhone = new JTextField(oldNumberPhone.getText(), sizeTextField);
        newSpeciality = new JTextField(oldSpeciality.getText(), sizeTextField);

        File folderAvatar = new File(FolderTrackerURL.folderTrackerURL());
        File[] files = folderAvatar.listFiles();
        JComboBox<String> comboBox_AvatarPath = new JComboBox<>();
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
                    || fileName.endsWith(".gif")) {
                comboBox_AvatarPath.addItem(file.getName());
            }
        }
        comboBox_AvatarPath.setSelectedItem(filename);

        JComboBox<String> comboBox_Job = new JComboBox<>();
        List<String> listjob = loadUser.getJobEnum();
        for (String job : listjob) {
            comboBox_Job.addItem(job);
        }
        comboBox_Job.setSelectedItem(oldJob.getText());

        RoundedButton submiteButton = new RoundedButton("Submit");
        submiteButton.setPreferredSize(new Dimension(100, 50));
        submiteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User newUser = new User(0, newName.getText(), newSurname.getText(),
                        newCompany.getText(), comboBox_Job.getSelectedItem().toString(), newEmail.getText(),
                        newNumberPhone.getText(),
                        "../../Image/Avatar/"
                                + comboBox_AvatarPath.getSelectedItem().toString(),
                        newSpeciality.getText(), null);
                loadUser.modifyUser(oldUser, newUser);
                dialog.dispose();
            }
        });

        RoundedButton exitButton = new RoundedButton("Exit");
        exitButton.setSize(new Dimension(100, 20));
        exitButton.setBackground(new Color(255, 56, 106));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        Component[] grpJLB = { userName, userSurname, userJob, userCompany, userEmail, userNumberPhone, userPathIcon,
                userSpeciality,
                oldPageTitle, newPageTitle };
        for (Component c : grpJLB) {
            c.setForeground( Color.WHITE);
        }
        Component[] grpTF = { oldName, oldSurname, oldJob, oldCompany, oldEmail, oldNumberPhone, oldPathIcon,
                oldSpeciality,
                newName, newSurname, newCompany, newEmail, newNumberPhone, newSpeciality };
        for (Component c : grpTF) {
            c.setForeground(Color.BLACK);
        }

        JTextField[] grpOldTF = { oldName, oldSurname, oldJob, oldCompany, oldEmail, oldNumberPhone, oldPathIcon,
                oldSpeciality };
        for (JTextField c : grpOldTF) {
            c.setEditable(false);
        }
        oldPageTitle.setFont(new Font("Verdana", 1, 15));
        newPageTitle.setFont(new Font("Verdana", 1, 15));

        GroupLayout Layout = new GroupLayout(centerPanel);
        centerPanel.setLayout(Layout);
        Layout.setHorizontalGroup(Layout.createSequentialGroup()
                .addGroup(Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(userName)
                                .addComponent(userSurname)
                                .addComponent(userJob)
                                .addComponent(userCompany)
                                .addComponent(userSpeciality)
                                .addComponent(userEmail)
                                .addComponent(userNumberPhone)
                                .addComponent(userPathIcon))
                        .addGap(10, 10, 10)
                        .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(oldPageTitle)
                                .addComponent(oldName)
                                .addComponent(oldSurname)
                                .addComponent(oldJob)
                                .addComponent(oldCompany)
                                .addComponent(oldSpeciality)
                                .addComponent(oldEmail)
                                .addComponent(oldNumberPhone)
                                .addComponent(oldPathIcon))
                        .addGap(50, 50, 50)
                        .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(newPageTitle)
                                .addComponent(newName)
                                .addComponent(newSurname)
                                .addComponent(comboBox_Job)
                                .addComponent(newCompany)
                                .addComponent(newSpeciality)
                                .addComponent(newEmail)
                                .addComponent(newNumberPhone)
                                .addComponent(comboBox_AvatarPath))
                        .addGap(10, 10, 10)
                        .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(submiteButton, 80, 80, 80)
                                .addComponent(exitButton, 80, 80, 80))
                        .addGap(10, 10, 10))
                .addGap(10, 10, 10));

        Layout.setVerticalGroup(Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(oldPageTitle)
                        .addComponent(newPageTitle))
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(userName)
                        .addComponent(oldName,20,20,20)
                        .addComponent(newName,20,20,20))
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(userSurname)
                        .addComponent(oldSurname,20,20,20)
                        .addComponent(newSurname,20,20,20))
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(userJob)
                        .addComponent(oldJob,20,20,20)
                        .addComponent(comboBox_Job,20,20,20))
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(userCompany)
                        .addComponent(oldCompany,20,20,20)
                        .addComponent(newCompany,20,20,20))
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(userSpeciality)
                        .addComponent(oldSpeciality,20,20,20)
                        .addComponent(newSpeciality,20,20,20))
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(userEmail)
                        .addComponent(oldEmail,20,20,20)
                        .addComponent(newEmail,20,20,20))
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(userNumberPhone)
                        .addComponent(oldNumberPhone,20,20,20)
                        .addComponent(newNumberPhone,20,20,20)
                        .addComponent(submiteButton))
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(userPathIcon)
                        .addComponent(oldPathIcon,20,20,20)
                        .addComponent(comboBox_AvatarPath,20,20,20)
                        .addComponent(exitButton))
                .addGap(10, 10, 10));

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
}

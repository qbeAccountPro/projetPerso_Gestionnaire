package Frame.Event.StaffManagement;

import Frame.Component.BackGround.RoundedPanel;
import Frame.Component.Button.RoundedButton;
import Frame.Constructor.User;
import Frame.Event.DialogError.ErrorDialog;
import Frame.Form.Staff_Management;
import Frame.Image.Avatar.FolderTrackerURL;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.awt.Component;

import javax.swing.GroupLayout.Alignment;
import javax.swing.*;

public class AddUser extends JPanel {
  private RoundedPanel centerPanel, backgroundPanel;
  private JDialog dialog;
  private Staff_Management staff_Management;
  private String job;

  public AddUser(JDialog dialog, Staff_Management staff_Management, String job) {
    this.dialog = dialog;
    this.staff_Management = staff_Management;
    this.job = job;
    initComponents();
    setBackground(new Color(0, 0, 0, 0));
  }

  private void initComponents() {

    centerPanel = new RoundedPanel();
    backgroundPanel = new RoundedPanel();

    centerPanel.setBackground(new Color(38, 38, 38));
    backgroundPanel.setBackground(new Color(0, 0, 0));

    Color titleColor = Color.white;

    JLabel userName, userSurname, userCompany, userEmail, userNumberPhone, userPathIcon, userSpeciality,
        pageTitle;

    userName = new JLabel("Name :");
    userSurname = new JLabel("Surname :");
    userCompany = new JLabel("Company :");
    userEmail = new JLabel("Email :");
    userNumberPhone = new JLabel("Num. phone :");
    userPathIcon = new JLabel("Icon :");
    userSpeciality = new JLabel("Speciality :");

    pageTitle = new JLabel("Add a new user !");

    JTextField tfName, tfSurname, tfCompany, tfEmail, tfNumberPhone, tfSpeciality;
    tfName = new JTextField("", 10);
    tfSurname = new JTextField("", 10);
    tfCompany = new JTextField("", 10);
    tfEmail = new JTextField("", 10);
    tfNumberPhone = new JTextField("", 10);
    tfSpeciality = new JTextField("", 10);

    File folderAvatar = new File(FolderTrackerURL.folderTrackerURL());
    File[] files = folderAvatar.listFiles();
    JComboBox<String> comboBox = new JComboBox<>();
    for (File file : files) {
      String fileName = file.getName();
      if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
          || fileName.endsWith(".gif")) {
        comboBox.addItem(file.getName());
      }
    }

    RoundedButton submiteButton = new RoundedButton("Submit");
    submiteButton.setPreferredSize(new Dimension(100, 50));
    submiteButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        User newUser = new User(0, tfName.getText(), tfSurname.getText(), tfCompany.getText(),
            job, tfEmail.getText(), tfNumberPhone.getText(),
            "../../Image/Avatar/" + comboBox.getSelectedItem().toString(),
            tfSpeciality.getText(), null);
        if (tfName.getText().isEmpty() || tfSurname.getText().isEmpty()) {
          new ErrorDialog("All fields need to be completed  ");
        } else {
          staff_Management.addNewUser(newUser);
          dialog.dispose();
        }

      }
    });

    RoundedButton exitButton = new RoundedButton("Exit");
    exitButton.setSize(new Dimension(100, 50));
    exitButton.setBackground(new Color(255, 56, 106));
    exitButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        User newUser = null;
        staff_Management.addNewUser(newUser);
        dialog.dispose();
      }
    });

    Component[] grpJLB = { userName, userSurname, userCompany, userEmail, userNumberPhone, userPathIcon,
        userSpeciality,
        pageTitle };
    for (Component c : grpJLB) {
      c.setForeground(titleColor);
    }
    Component[] grpTF = { tfName, tfSurname, tfCompany, tfEmail, tfNumberPhone, tfSpeciality };
    for (Component c : grpTF) {
      c.setForeground(Color.BLACK);
    }

    pageTitle.setFont(new Font("Verdana", 1, 15));

    GroupLayout Layout = new GroupLayout(centerPanel);
    centerPanel.setLayout(Layout);
    Layout.setHorizontalGroup(Layout.createParallelGroup(Alignment.LEADING)
        .addGroup(Layout.createSequentialGroup()
            .addGap(20, 20, Short.MAX_VALUE)
            .addComponent(pageTitle)
            .addGap(20, 20, Short.MAX_VALUE))
        .addGroup(Layout.createSequentialGroup()
            .addGap(10, 10, 10)
            .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                .addComponent(userName)
                .addComponent(userSurname)
                .addComponent(userCompany)
                .addComponent(userSpeciality)
                .addComponent(userEmail)
                .addComponent(userNumberPhone)
                .addComponent(userPathIcon))
            .addGap(10, 10, 10)
            .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                .addComponent(tfName, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addComponent(tfSurname, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addComponent(tfCompany, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addComponent(tfSpeciality, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addComponent(tfEmail, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addComponent(tfNumberPhone, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE,
                    GroupLayout.PREFERRED_SIZE))
            .addGap(10, 10, 10)
            .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                .addComponent(submiteButton, 80, 80, 80)
                .addComponent(exitButton, 80, 80, 80))
            .addGap(10, 10, 10))
        .addGap(10, 10, 10));

    Layout.setVerticalGroup(Layout.createSequentialGroup()
        .addGap(10, 10, 10)
        .addComponent(pageTitle)
        .addGap(10, 10, 10)
        .addGroup(Layout.createParallelGroup(Alignment.BASELINE)
            .addComponent(userName)
            .addComponent(tfName))
        .addGap(10, 10, 10)
        .addGroup(Layout.createParallelGroup(Alignment.BASELINE)
            .addComponent(userSurname)
            .addComponent(tfSurname))
        .addGap(10, 10, 10)
        .addGroup(Layout.createParallelGroup(Alignment.BASELINE)
            .addComponent(userCompany)
            .addComponent(tfCompany))
        .addGap(10, 10, 10)
        .addGroup(Layout.createParallelGroup(Alignment.BASELINE)
            .addComponent(userSpeciality)
            .addComponent(tfSpeciality))
        .addGap(10, 10, 10)
        .addGroup(Layout.createParallelGroup(Alignment.BASELINE)
            .addComponent(userEmail)
            .addComponent(tfEmail))
        .addGap(10, 10, 10)
        .addGroup(Layout.createParallelGroup(Alignment.BASELINE)
            .addComponent(userNumberPhone)
            .addComponent(tfNumberPhone)
            .addComponent(submiteButton))
        .addGap(10, 10, 10)
        .addGroup(Layout.createParallelGroup(Alignment.BASELINE)
            .addComponent(userPathIcon)
            .addComponent(comboBox)
            .addComponent(exitButton))
        .addGap(10, 10, 10));


    GroupLayout Layout_2 = new GroupLayout(backgroundPanel);
    Layout_2.setHorizontalGroup(Layout_2.createSequentialGroup()
        .addGap(10, 10, 10)
        .addComponent(centerPanel)
        .addGap(10, 10, 10));

    Layout_2.setVerticalGroup(Layout_2.createSequentialGroup()
        .addGap(10, 10, 10)
        .addComponent(centerPanel)
        .addGap(10, 10, 10));

    backgroundPanel.setLayout(Layout_2);

    this.add(backgroundPanel);

  }

}

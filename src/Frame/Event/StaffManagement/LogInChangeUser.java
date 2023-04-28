package Frame.Event.StaffManagement;

import Frame.MainFrame;
import Frame.Component.BackGround.RoundedPanel;
import Frame.Component.Button.RoundedButton;
import Frame.Constructor.User;
import Frame.Database.loadUser;
import Frame.Event.DialogError.ErrorDialog;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class LogInChangeUser extends JFrame {
    private RoundedPanel centerPanel;
    private JFrame mainFrame;

    public LogInChangeUser(JFrame mainFrame, User currentUser) {
        this.mainFrame = mainFrame;

        setUndecorated(true);
        getContentPane().add(initComponents());
        setBackground(new Color(0, 0, 0,0));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    
    private RoundedPanel initComponents() {
        RoundedPanel backgroundPanel = new RoundedPanel();
        backgroundPanel.setBackground(new Color(0,0,0));
        centerPanel = new RoundedPanel();
        centerPanel.setBackground(new Color(38,38,38));
        Color titreColor = Color.white;
        JLabel userName, userPassword, connexionTitle;
        userName = new JLabel("Name :");
        userName.setForeground(titreColor);
        userPassword = new JLabel("Password :");
        userPassword.setForeground(titreColor);
        connexionTitle = new JLabel("Change user on User Manager :");
        connexionTitle.setForeground(titreColor);

        JTextField tfName, tfPassword;
        tfName = new JTextField("", 15);
        tfPassword = new JTextField("", 15);

        RoundedButton submiteButton = new RoundedButton("Submit");
        submiteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User newCurrentUser = loadUser.connection(tfName.getText(), tfPassword.getText());
                if (newCurrentUser != null) {
                    mainFrame.dispose();
                    dispose();
                    new MainFrame(newCurrentUser);
                    
                } else {
                    setVisible(false);
                    new ErrorDialog("Wrong username and password combination");
                    setVisible(true);
                }
            }
        });

        RoundedButton exitButton = new RoundedButton("Exit");
        exitButton.setBackground(new Color(255, 56, 106));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                mainFrame.setVisible(true);
            }
        });

        GroupLayout Layout = new GroupLayout(centerPanel);
        centerPanel.setLayout(Layout);
        Layout.setHorizontalGroup(Layout.createSequentialGroup()
                .addGap(10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Layout.createSequentialGroup()
                                .addGap(20, 20, Short.MAX_VALUE)
                                .addComponent(connexionTitle)
                                .addGap(20, 20, Short.MAX_VALUE))
                        .addGroup(Layout.createSequentialGroup()
                                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(userName)
                                        .addComponent(userPassword))
                                .addGap(10)
                                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(tfName, 100, 100, 100)
                                        .addComponent(tfPassword, 100, 100,
                                                100))
                                .addGap(10)
                                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(submiteButton, 100, 100, 100)
                                        .addComponent(exitButton, 100, 100, 100))
                                .addGap(10)))
                .addGap(10));

        Layout.setVerticalGroup(Layout.createSequentialGroup()
                .addGap(10)
                .addComponent(connexionTitle)
                .addGap(10)
                .addGroup(Layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(userName)
                        .addComponent(tfName)
                        .addComponent(submiteButton, 20, 20, 20))
                .addGap(10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(userPassword)
                        .addComponent(tfPassword, 20, 20, 20)
                        .addComponent(exitButton, 20, 20, 20))
                .addGap(10));

        GroupLayout Layout2 = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(Layout2);
        Layout2.setHorizontalGroup(Layout2.createSequentialGroup()
                .addGap(10)
                .addComponent(centerPanel)
                .addGap(10));
        Layout2.setVerticalGroup(Layout2.createSequentialGroup()
                .addGap(10)
                .addComponent(centerPanel)
                .addGap(10));


                return backgroundPanel;

    }
}

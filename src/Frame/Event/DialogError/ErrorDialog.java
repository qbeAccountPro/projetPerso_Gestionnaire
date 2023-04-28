package Frame.Event.DialogError;

import Frame.Component.BackGround.RoundedPanel;
import Frame.Component.Button.RoundedButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.GroupLayout.Alignment;
import javax.swing.*;

public class ErrorDialog extends JDialog {
    private RoundedPanel centerPanel, backgroundPanel;
    private String Error;

    public ErrorDialog(String Error) {
        this.Error = Error;
        initComponents();
        setModal(true);
        setTitle("Error name of workgroup");
        setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
    }

    private void initComponents() {

        centerPanel = new RoundedPanel();
        backgroundPanel = new RoundedPanel();
        centerPanel.setBackground(new Color(38,38,38));
        backgroundPanel.setBackground(new Color(0,0,0));

        JLabel ErrorText = new JLabel(Error);
        ErrorText.setForeground( Color.white);
        ErrorText.setFont(new Font("Verdana", 1, 13));



        RoundedButton exitButton = new RoundedButton("Exit");
        exitButton.setSize(new Dimension(100, 50));
        exitButton.setBackground(new Color(255, 56, 106));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        GroupLayout Layout = new GroupLayout(centerPanel);
        centerPanel.setLayout(Layout);
        Layout.setHorizontalGroup(Layout.createSequentialGroup()
                .addGap(20)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(ErrorText)
                        .addGroup(Layout.createSequentialGroup()
                                .addGap(20, 20, Short.MAX_VALUE)
                                .addComponent(exitButton)
                                .addGap(20)))
                .addGap(20));
        Layout.setVerticalGroup(Layout.createSequentialGroup()
                .addGap(10)
                .addComponent(ErrorText)
                .addGap(10)
                .addComponent(exitButton)
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

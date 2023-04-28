package Frame.Event.Archive;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import javax.swing.text.PlainDocument;

import Frame.Component.BackGround.RoundedPanel;
import Frame.Component.Button.RoundedButton;
import Frame.Component.Spinner.DateTimeSpinner;
import Frame.Constructor.Task;
import Frame.Constructor.User;
import Frame.Database.laodTask;
import Frame.Database.loadUser;
import Frame.Event.DialogError.ErrorDialog;
import Frame.Event.InputRestriction.InputRestriction;
import Frame.Form.Archive;

public class addArchiveTask extends JDialog {
    private Archive archive;

    public addArchiveTask(Archive archive) {
        this.archive = archive;
        setModal(true);
        setTitle("add archive task");
        getContentPane().add(initComponents());
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public RoundedPanel initComponents() {
        RoundedPanel backgroundPanel = new RoundedPanel();
        backgroundPanel.setBackground(new Color(0,0,0));

        RoundedPanel centerPanel = new RoundedPanel();
        centerPanel.setBackground(new Color(38,38,38));

        JLabel pageTitleLb = new JLabel("Add a new task :");
        pageTitleLb.setFont(new Font("Verdana", 0, 15));
        pageTitleLb.setForeground(Color.white);

        JLabel descriptionLb = new JLabel("Description :");
        JLabel deadlineLb = new JLabel("Deadline :");
        JLabel thematicLb = new JLabel("Thematic :");
        JLabel archiveDateLb = new JLabel("Archive date :");
        JLabel archivedByLb = new JLabel("Archive by :");

        Component[] grpJLB = { descriptionLb, deadlineLb, archiveDateLb, thematicLb, archivedByLb };
        for (Component c : grpJLB) {
            c.setForeground(Color.white);
            c.setFont(new Font("Verdana", 0, 12));
        }

        JTextField descriptionTf = new JTextField();
        JSpinner deadlineSpinner = new DateTimeSpinner();
        JSpinner archiveDateSpinner = new DateTimeSpinner();
        JTextField thematicTf = new JTextField("");

        PlainDocument descriptionDoc = (PlainDocument) descriptionTf.getDocument();
        descriptionDoc.setDocumentFilter(InputRestriction.LengthFilter(descriptionTf, 40));
        PlainDocument thematicDoc = (PlainDocument) thematicTf.getDocument();
        thematicDoc.setDocumentFilter(InputRestriction.LengthFilter(thematicTf, 10));

        Component[] grpTF = { descriptionTf, thematicTf };
        for (Component c : grpTF) {
            c.setForeground(Color.black);
            c.setFont(new Font("Verdana", 0, 12));
            c.setPreferredSize(new Dimension(300, 30));
        }

        List<User> userList = loadUser.getUserList();
        JComboBox<String> archivedByComboBox = new JComboBox<>();
        archivedByComboBox.addItem("NOT archived");
        for (User user : userList) {
            archivedByComboBox.addItem(user.getName() + " " + user.getSurname());
        }

        RoundedButton submiteButton = new RoundedButton("Submit");
        submiteButton.setPreferredSize(new Dimension(100, 50));
        submiteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat formatterDeadline = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDeadline = formatterDeadline.format((Date) deadlineSpinner.getValue());

                SimpleDateFormat formatterArchiveDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedArchiveDate = formatterArchiveDate
                        .format((Date) archiveDateSpinner.getValue());
       
                List<Task> taskList = laodTask.getTaskList();
                boolean condition = false;
                int indice = 0;
                int taskListSize = taskList.size();
                while (condition == false && taskListSize != indice) {
                    for (Task task : taskList) {
                        if (task.getDescription().toString().toLowerCase()
                                .equals(descriptionTf.getText().toLowerCase())) {
                            condition = true;
                        } else {
                            indice++;
                        }
                    }
                }
                if (condition == true) {
                    new ErrorDialog("The description : " + descriptionTf.getText() + " already exist!");
                } else {
                    Task newArchivedTask = new Task(0, descriptionTf.getText(), formattedDeadline, "0", "0",
                            thematicTf.getText(), "NOT assignment", "NOT assignment", true,
                            formattedArchiveDate, archivedByComboBox.getSelectedItem().toString());
                    archive.addNewTask(newArchivedTask);
                    dispose();

                }
            }
        });

        RoundedButton exitButton = new RoundedButton("Exit");
        exitButton.setSize(new Dimension(100, 50));
        exitButton.setBackground(new Color(255, 56, 106));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.out.println(deadlineSpinner.getValue());
                dispose();
            }
        });

        GroupLayout Layout = new GroupLayout(centerPanel);
        centerPanel.setLayout(Layout);

        Layout.setHorizontalGroup(Layout.createSequentialGroup()
                .addGap(10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Layout.createSequentialGroup()
                                .addGap(20, 20, Short.MAX_VALUE)
                                .addComponent(pageTitleLb)
                                .addGap(20, 20, Short.MAX_VALUE))
                        .addGroup(Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(descriptionLb)
                                        .addComponent(deadlineLb)
                                        .addComponent(archivedByLb)
                                        .addComponent(thematicLb)
                                        .addComponent(archiveDateLb))
                                .addGap(10, 10, 10)
                                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(descriptionTf)
                                        .addGroup(Layout.createSequentialGroup()
                                                .addGroup(Layout.createParallelGroup(
                                                        Alignment.LEADING)
                                                        .addComponent(deadlineSpinner, 140, 140, 140)
                                                        .addComponent(archivedByComboBox, 140, 140, 140)
                                                        .addComponent(thematicTf, 140, 140, 140)
                                                        .addComponent(archiveDateSpinner, 140, 140, 140))
                                                .addGap(20, 20, Short.MAX_VALUE)
                                                .addGroup(Layout.createParallelGroup(
                                                        Alignment.LEADING)
                                                        .addComponent(submiteButton, 80, 80, 80)
                                                        .addComponent(exitButton, 80, 80, 80))))))
                .addGap(10));

        Layout.setVerticalGroup(Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(pageTitleLb)
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(descriptionLb)
                        .addComponent(descriptionTf))
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(thematicLb)
                        .addComponent(thematicTf))
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(deadlineLb)
                        .addComponent(deadlineSpinner))
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(archiveDateLb)
                        .addComponent(archiveDateSpinner))
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(archivedByLb)
                        .addComponent(archivedByComboBox)
                        .addComponent(submiteButton))
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(exitButton))
                .addGap(10, 10, 10));

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

        return backgroundPanel;
    }

}

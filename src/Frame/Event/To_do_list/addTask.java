package Frame.Event.To_do_list;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.text.PlainDocument;

import Frame.Component.BackGround.RoundedPanel;
import Frame.Component.Button.RoundedButton;
import Frame.Component.Spinner.*;
import Frame.Constructor.*;
import Frame.Database.*;
import Frame.Event.DialogError.ErrorDialog;
import Frame.Event.InputRestriction.InputRestriction;
import Frame.Form.To_do_list;

public class addTask extends JDialog {
    private To_do_list to_do_list;
    private RoundedPanel centerPanel;

    public addTask(To_do_list to_do_list) {
        this.to_do_list = to_do_list;
        setModal(true);
        setTitle("add task");
        getContentPane().add(initComponents());
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private RoundedPanel initComponents() {
        RoundedPanel backgroundPanel = new RoundedPanel();
        backgroundPanel.setBackground(new Color(0, 0, 0));
        centerPanel = new RoundedPanel();
        centerPanel.setBackground(new Color(38, 38, 38));

        JLabel pageTitleLb = new JLabel("Add a new task :");
        pageTitleLb.setFont(new Font("Verdana", 0, 15));
        pageTitleLb.setForeground(Color.white);

        JLabel descriptionLb = new JLabel("Description :");
        JLabel deadlineLb = new JLabel("Deadline :");
        JLabel durationLb = new JLabel("Duration :");
        JLabel priorityLb = new JLabel("Priority :");
        JLabel thematicLb = new JLabel("Thematic :");
        JLabel assignmentByLb = new JLabel("Assignement by :");
        JLabel assignmentForLb = new JLabel("Assignement For :");

        Component[] grpJLB = { descriptionLb, deadlineLb, durationLb, priorityLb, thematicLb, assignmentByLb,
                assignmentForLb };
        for (Component c : grpJLB) {
            c.setForeground(Color.white);
            c.setFont(new Font("Verdana", 0, 12));
        }

        JTextField descriptionTf = new JTextField();
        JSpinner deadlineSpinner = new DateTimeSpinner();
        JSpinner durationSpinner = new HourSpinner();
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
        JComboBox<String> AssignmentByComboBox = new JComboBox<>();
        AssignmentByComboBox.addItem("NOT assignment");
        for (User user : userList) {
            AssignmentByComboBox.addItem(user.getName() + " " + user.getSurname());
        }

        JComboBox<String> AssignmentForComboBox = new JComboBox<>();
        AssignmentForComboBox.addItem("NOT assignment");
        for (User user : userList) {
            AssignmentForComboBox.addItem(user.getName() + " " + user.getSurname());
        }

        JComboBox<Integer> priorityComboBox = new JComboBox<>();
        for (int i = 0; i < 4; i++) {
            priorityComboBox.addItem(i);
        }

        RoundedButton submitButton = new RoundedButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 50));
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat formatterDeadline = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDeadline = formatterDeadline.format((Date) deadlineSpinner.getValue());

                SimpleDateFormat formatterDuration = new SimpleDateFormat("HH:mm:ss");
                String formattedDuration = formatterDuration.format((Date) durationSpinner.getValue());
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
                    Task newTask = new Task(0, descriptionTf.getText(), formattedDeadline,
                            formattedDuration, priorityComboBox.getSelectedItem().toString(),
                            thematicTf.getText(), AssignmentByComboBox.getSelectedItem().toString(),
                            AssignmentForComboBox.getSelectedItem().toString(), false, null, "NOT archived");
                    to_do_list.addNewTask(newTask);
                    dispose();

                }
            }
        });

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
                                        .addComponent(assignmentByLb)
                                        .addComponent(assignmentForLb)
                                        .addComponent(thematicLb)
                                        .addComponent(durationLb)
                                        .addComponent(priorityLb))
                                .addGap(10, 10, 10)
                                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(descriptionTf)
                                        .addGroup(Layout.createSequentialGroup()
                                                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(deadlineSpinner, 140, 140, 140)
                                                        .addComponent(AssignmentByComboBox, 140, 140, 140)
                                                        .addComponent(AssignmentForComboBox, 140, 140, 140)
                                                        .addComponent(thematicTf, 140, 140, 140)
                                                        .addComponent(durationSpinner, 60, 60, 60)
                                                        .addComponent(priorityComboBox, 40, 40, 40))
                                                .addGap(20, 20, Short.MAX_VALUE)
                                                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(submitButton, 80, 80, 80)
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
                        .addComponent(durationLb)
                        .addComponent(durationSpinner))
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(priorityLb)
                        .addComponent(priorityComboBox))
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(assignmentByLb)
                        .addComponent(AssignmentByComboBox)
                        .addComponent(submitButton))
                .addGap(10, 10, 10)
                .addGroup(Layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(assignmentForLb)
                        .addComponent(AssignmentForComboBox)
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

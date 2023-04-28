package Frame.Form;

import Frame.MainFrame;
import Frame.Component.BackGround.RoundedPanel;
import Frame.Component.Button.*;
import Frame.Component.CardUser.addCardUser;
import Frame.Component.ScrollBar.*;
import Frame.Constructor.*;
import Frame.Database.loadUser;
import Frame.Event.StaffManagement.*;

import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;

public class Staff_Management extends JPanel {

    private User newUser, currentUser;
    private MainFrame mainFrame;

    public Staff_Management(MainFrame mainFrame, User currentUser) {
        this.mainFrame = mainFrame;
        this.currentUser = currentUser;
        loadUser.getJobEnum();
        initComponent(loadUser.getUserList());
        setOpaque(false);
    }

    public void initComponent(List<User> userList) {
        JPanel menuPanel = menuPanel();
        JScrollPane mainPanelCard = mainPanelCard(userList);
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanelCard)
                        .addComponent(menuPanel, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.PREFERRED_SIZE,
                                Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, 80, 80, 80)
                .addGap(10)
                .addComponent(mainPanelCard));
    }

    private JScrollPane mainPanelCard(List<User> userList) {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0,0,0));
        mainPanel.setSize(getPreferredSize());
        List<String> listjob = loadUser.getJobEnum();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        int i = 1;
        for (String job : listjob) {
            JPanel panel = cardPanel(job, job, userList);
            panel.setPreferredSize(new Dimension(1, 220));
            mainPanel.add(panel);
            if (i < listjob.size()) {
                mainPanel.add(Box.createVerticalStrut(10));
                i++;
            }
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBar(new ScrollBarCustom());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScrollBarCustom sp = new ScrollBarCustom();
        sp.setOrientation(JScrollBar.VERTICAL);
        scrollPane.setVerticalScrollBar(sp);

        return scrollPane;
    }

    private JPanel menuPanel() {
        JPanel backgroundPanel = new RoundedPanel();
        backgroundPanel.setBackground(new Color(38,38,38));
      
        RoundedButton addWorkgroup = new RoundedButton("Add Workgroup");
        addWorkgroup.setSize(new Dimension(100, 50));
               addWorkgroup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setTitle("add workgroup");
                dialog.getContentPane().add(new AddWorkgroup(dialog, Staff_Management.this));
                dialog.setUndecorated(true);
                dialog.setBackground(new Color(0, 0, 0, 0));
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                mainFrame.dispose();
                dialog.setVisible(true);
                mainFrame.revalidate();
                mainFrame.repaint();
                mainFrame.showForm(new Staff_Management(mainFrame, currentUser));
                mainFrame.setVisible(true);
            }
        });

        RoundedButton deleteWorkgroup = new RoundedButton("Delete Workgroup");
        deleteWorkgroup.setSize(new Dimension(100, 50));
        deleteWorkgroup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setTitle("add workgroup");
                dialog.getContentPane().add(new DeleteWorkgroup(dialog));
                dialog.setUndecorated(true);
                dialog.setBackground(new Color(0, 0, 0, 0));
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                mainFrame.dispose();
                dialog.setVisible(true);
                mainFrame.showForm(new Staff_Management(mainFrame, currentUser));
                mainFrame.setVisible(true);
            }
        });

        RoundedButton modifyWorkgroup = new RoundedButton("Modify Workgroup");
        modifyWorkgroup.setSize(new Dimension(100, 50));
        modifyWorkgroup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setTitle("add workgroup");
                dialog.getContentPane().add(new ModifyWorkgroup(dialog));
                dialog.setUndecorated(true);
                dialog.setBackground(new Color(0, 0, 0, 0));
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                mainFrame.dispose();
                dialog.setVisible(true);
                mainFrame.showForm(new Staff_Management(mainFrame, currentUser));
                mainFrame.setVisible(true);     
            }
        });


        GroupLayout layout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(layout);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(10)
                .addComponent(addWorkgroup)
                .addGap(10)
                .addComponent(modifyWorkgroup)
                .addGap(10)
                .addComponent(deleteWorkgroup));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addContainerGap(50, 50)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(addWorkgroup)
                        .addGap(10)
                        .addComponent(modifyWorkgroup)
                        .addGap(10)
                        .addComponent(deleteWorkgroup))
                .addContainerGap(50, 50));
        return backgroundPanel;
    }

    public RoundedPanel cardPanel(String Title, String condition, List<User> userList) {
        Color color38 = new Color(38,38,38);
        Font titleFont = new Font("Verdana", 0, 15);

        RoundedPanel backgroundPanel = new RoundedPanel();

        RoundedPanel titlePanel = new RoundedPanel();
        titlePanel.setBackground(color38);
        JPanel listCardUser = new JPanel();
        backgroundPanel.setBackground(color38);
        listCardUser.setBackground(color38);
        listCardUser.setLayout(new FlowLayout(FlowLayout.LEFT));
        listCardUser.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JLabel title_LbL = new JLabel(Title + " : ");
        title_LbL.setFont(titleFont);
        title_LbL.setForeground(Color.white);
        titlePanel.add(title_LbL);
        

        ButtonIcon iconAddUser = new ButtonIcon();
        iconAddUser.setBackground(color38);
        iconAddUser.setIcon(new ImageIcon(getClass().getResource("../Image/Icon/addUser.png")));
        iconAddUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setTitle("add user");
                dialog.getContentPane().add(new AddUser(dialog, Staff_Management.this, condition));
                dialog.setUndecorated(true);
                dialog.setBackground(new Color(0, 0, 0, 0));
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                mainFrame.setVisible(false);
                dialog.setVisible(true);
                mainFrame.setVisible(true);

                if (newUser != null) {
                    loadUser.addUser(newUser);
                    listCardUser.add(new addCardUser(newUser.getPathIcon(),
                            newUser.getName(), newUser.getSurname(),
                            newUser.getJob(), newUser.getCompany(), newUser.getEmail(),
                            newUser.getNumberphone(),
                            newUser.getSpeciality()));
                    listCardUser.revalidate();
                    listCardUser.repaint();
                    newUser = null;
                }
            }
        });

        ButtonIcon iconSuprUser = new ButtonIcon();
        iconSuprUser.setBackground(color38);
        iconSuprUser.setIcon(new ImageIcon(getClass().getResource("../Image/Icon/suprUser.png")));
        iconSuprUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setTitle("Delete user");
                dialog.getContentPane().add(new DeleteUser(dialog, condition));
                dialog.setUndecorated(true);
                dialog.setBackground(new Color(0, 0, 0, 0));
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                mainFrame.setVisible(false);
                mainFrame.dispose();
                dialog.setVisible(true);
               mainFrame.showForm(new Staff_Management(mainFrame, currentUser));
               mainFrame.setVisible(true);
            }
        });

        ButtonIcon modifyUser = new ButtonIcon();
        modifyUser.setBackground(color38);
        modifyUser.setIcon(new ImageIcon(getClass().getResource("../Image/Icon/modifyUser.png")));
        modifyUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setTitle("Modify user");
                dialog.getContentPane().add(new ModifyUser(dialog, Staff_Management.this, condition));
                dialog.setUndecorated(true);
                dialog.setBackground(new Color(0, 0, 0, 0));
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                mainFrame.setVisible(false);
                mainFrame.dispose();
                dialog.setVisible(true);
                mainFrame.showForm(new Staff_Management(mainFrame, currentUser));
                mainFrame.setVisible(true);
            }
        });

        GroupLayout Layout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(Layout);

        JScrollPane scrollPane = new JScrollPane(listCardUser);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBar(new ScrollBarCustom());
        ScrollBarCustom SBC = new ScrollBarCustom();
        SBC.setOrientation(JScrollBar.HORIZONTAL);
        scrollPane.setHorizontalScrollBar(SBC);
        listCardUser.setMinimumSize(new Dimension(100, 200));

        Layout.setHorizontalGroup(Layout.createSequentialGroup()
                .addGap(10)
                .addGroup(Layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Layout.createSequentialGroup()
                                .addGap(10)
                                .addComponent(titlePanel,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addComponent(iconAddUser)
                                .addGap(5)
                                .addComponent(iconSuprUser)
                                .addGap(5)
                                .addComponent(modifyUser))
                        .addComponent(scrollPane))
                .addGap(10));

        Layout.setVerticalGroup(Layout.createSequentialGroup()
                .addGap(5)
                .addGroup(Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGroup(Layout.createSequentialGroup()
                                .addContainerGap(1, 1)
                                .addComponent(titlePanel,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                        .addComponent(iconAddUser)
                        .addComponent(iconSuprUser)
                        .addComponent(modifyUser))
                .addGap(5)
                .addComponent(scrollPane));

     
        loadUserCard(listCardUser, userList, condition);
        return backgroundPanel;
    }

    private void loadUserCard(JPanel listCardUser, List<User> userList, String conditionJob) {
        for (User user : userList) {
            String job = user.getJob();
            if (conditionJob.equals(job)) {
                listCardUser.add(new addCardUser(user.getPathIcon(), user.getName(), user.getSurname(),
                        user.getJob(), user.getCompany(), user.getEmail(),
                        user.getNumberphone(), user.getSpeciality()));
                listCardUser.revalidate();
                listCardUser.repaint();
            }
        }
    }

    public void addNewUser(User newUser) {
        this.newUser = newUser;
    }
    

}

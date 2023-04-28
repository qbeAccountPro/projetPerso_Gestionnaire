package Frame.MainInterface;

import Frame.Component.Avatar.ImageAvatar;
import Frame.Component.BackGround.RoundedPanel;
import Frame.Component.Button.ButtonNavigation;
import Frame.Component.ScrollBar.Navigation_ScrollBarCustom;
import Frame.Constructor.User;
import Frame.Event.Menu.EventMenu;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Navigation extends RoundedPanel {

  private EventMenu eventMenu;
  private JPanel panelMenu;
  private RoundedPanel backGroundUser;
  private RoundedPanel backGroundMenu;
  private ImageAvatar userAvatar;
  private JLabel userName, userJob;
  private JScrollPane jScrollPane1;
  private User currentUser;

  public Navigation(User currentUser) {
    this.currentUser =currentUser;
    initComponent();
    setOpaque(false);
    Navigation_ScrollBarCustom sb = new Navigation_ScrollBarCustom();
    sb.setForeground(new Color(130, 130, 130, 100));
    panelMenu.setLayout(new MigLayout("wrap, fillx, inset 3", "[fill]", "[]0[]"));

  }

  public void initMenu(EventMenu eventMenu) {
    this.eventMenu = eventMenu;
    addMenu(new ImageIcon(getClass().getResource("../Image/Icon/staff.png")), "Staff Management", 0);
    addMenu(new ImageIcon(getClass().getResource("../Image/Icon/toDoList.png")), "To do list", 1);
    addMenu(new ImageIcon(getClass().getResource("../Image/Icon/archive.png")), "Archive", 2);
    addMenu(new ImageIcon(getClass().getResource("../Image/Icon/planning.png")), "Planning", 3);
    addEmpty();
    addMenu(new ImageIcon(getClass().getResource("../Image/Icon/help.png")), "Help", 7);
  }

  private void addEmpty() {
    panelMenu.add(new JLabel(), "push");
  }

  private void addMenu(Icon icon, String text, int index) {
    ButtonNavigation menu = new ButtonNavigation();
    menu.setIcon(icon);
    menu.setText("  " + text);
    panelMenu.add(menu);
    menu.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        eventMenu.selected(index);
        setSelected(menu);
      }
    });
  }

  private void setSelected(ButtonNavigation menu) {
    for (Component com : panelMenu.getComponents()) {
      if (com instanceof ButtonNavigation) {
        ButtonNavigation b = (ButtonNavigation) com;
        b.setSelected(false);
      }
    }
    menu.setSelected(true);
  }

  private void initComponent() {
    // Create Variable //
    backGroundUser = new RoundedPanel();
    backGroundMenu = new RoundedPanel();
    userAvatar = new ImageAvatar();
    userName = new JLabel();
    userJob = new JLabel();
    jScrollPane1 = new JScrollPane();
    panelMenu = new JPanel();

    // Set up Menu Variable //
    backGroundUser.setBackground(new Color(38, 38, 38));
    userAvatar.setForeground(new Color(231, 231, 231));
    userAvatar.setBorderSize(2);
    userAvatar.setIcon(new ImageIcon(getClass().getResource(currentUser.getPathIcon().replace("../../", "../"))));
    userName.setFont(new Font("sansserif", 1, 14));
    userName.setForeground(new Color(224, 224, 224));
    userName.setText(currentUser.getName().toString());
    userJob.setForeground(new Color(203, 203, 203));
    userJob.setText(currentUser.getJob().toString());

    GroupLayout BGUserLayout = new GroupLayout(backGroundUser);
    backGroundUser.setLayout(BGUserLayout);
    // HORIZONTAL
    BGUserLayout.setHorizontalGroup(BGUserLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(BGUserLayout.createSequentialGroup()
            .addGap(10, 10, 10)
            .addComponent(userAvatar, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
            .addGap(10, 10, 10)
            .addGroup(BGUserLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(userName)
                .addComponent(userJob))
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    // VERTICAL
    BGUserLayout.setVerticalGroup(
        BGUserLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(BGUserLayout.createSequentialGroup()
                .addGroup(BGUserLayout
                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(BGUserLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(userName)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userJob))
                    .addGroup(BGUserLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(userAvatar, GroupLayout.PREFERRED_SIZE, 60,
                            GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)));
    backGroundMenu.setBackground(new Color(38,38,38));

    jScrollPane1.setBorder(null);
    jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    panelMenu.setBackground(new Color(38,38,38));
    GroupLayout panelMenuLayout = new GroupLayout(panelMenu);
    panelMenuLayout.setHorizontalGroup(
        panelMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE));
    panelMenuLayout.setVerticalGroup(
        panelMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE));

    jScrollPane1.setViewportView(panelMenu);

    GroupLayout BGMenuLayout = new GroupLayout(backGroundMenu);
    backGroundMenu.setLayout(BGMenuLayout);
    BGMenuLayout.setHorizontalGroup(
        BGMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(BGMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap()));

    BGMenuLayout.setVerticalGroup(
        BGMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(BGMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap()));

    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(backGroundUser, GroupLayout.DEFAULT_SIZE,
                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(backGroundMenu, GroupLayout.DEFAULT_SIZE,
                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backGroundUser, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(backGroundMenu, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
  }
}

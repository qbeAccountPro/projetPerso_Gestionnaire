package Frame;

import Frame.Component.BackGround.RoundedPanel;
import Frame.Constructor.User;
import Frame.Event.Header.EventHeader;
import Frame.Event.Menu.*;
import Frame.Event.StaffManagement.*;
import Frame.Form.*;
import Frame.MainInterface.*;

import java.awt.*;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class MainFrame extends JFrame {
  private Navigation NavigationPanel;
  private JPanel bodyPanel;
  private Header headerPanel;
  private RoundedPanel backGroundPanel;
  private User currentUser;

  public MainFrame(User currentUser) {
    this.currentUser = currentUser;
    initComponents();
    setBackground(new Color(0, 0, 0, 0));

    EventMenu eventMenu = new EventMenu() {
      @Override
      public void selected(int index) {
        if (index == 0) {
          showForm(new Staff_Management(MainFrame.this, currentUser));
        } else if (index ==1){
          showForm(new To_do_list(MainFrame.this));
        } else if (index ==2){
          showForm(new Archive(MainFrame.this));
        }else if (index ==3){
          showForm(new Planning(MainFrame.this,currentUser));
        }
        else if (index ==7){
          showForm(new Help());
        }                  
        else {
          showForm(new FormPicker(index));
        }
      }
    };

    EventHeader eventHeader = new EventHeader() {
      @Override
      public void selected(int index) {
        Desktop d = Desktop.getDesktop();
        if (index == 0) {
          System.out.println("Log IN");
          setVisible(false);
          new LogInChangeUser(MainFrame.this,currentUser);
                 
        } else if (index == 1) {
          System.out.println("Connexion E-mail");
          try {
            d.browse(new URI("https://www.google.com/intl/fr/gmail/about/"));
          } catch (IOException e) {
          } catch (URISyntaxException e) {
          }
        } else if (index == 2) {
          System.out.println("Connexion twitter");
          try {
            d.browse(new URI("https://twitter.com/home"));
          } catch (IOException e) {
          } catch (URISyntaxException e) {
          }
        } else if (index == 3) {
          System.out.println("Connexion instagram");
          try {
            d.browse(new URI("https://www.instagram.com/"));
          } catch (IOException e) {
          } catch (URISyntaxException e) {
          }
        } else if (index == 4) {
          System.exit(0);
        } else {
          System.out.println("Erreur index inconnue");
        }
      }
    };

    NavigationPanel.initMenu(eventMenu);
    headerPanel.initHeader(eventHeader);
    showForm(new Staff_Management(MainFrame.this,currentUser));
    setSize(1200, 800);
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public void showForm(Component com) {
    bodyPanel.removeAll();
    bodyPanel.add(com);
    bodyPanel.revalidate();
    bodyPanel.repaint();
  }

  private void initComponents() {
    headerPanel = new Header();
    backGroundPanel = new RoundedPanel();
    NavigationPanel = new Navigation(currentUser);
    bodyPanel = new JPanel();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setUndecorated(true);

    backGroundPanel.setBackground(new Color(0, 0, 0));

    bodyPanel.setOpaque(false);
    bodyPanel.setLayout(new BorderLayout());

    GroupLayout backGroundLayout = new GroupLayout(backGroundPanel);
    backGroundPanel.setLayout(backGroundLayout);

    backGroundLayout.setHorizontalGroup(backGroundLayout.createParallelGroup(Alignment.LEADING)
        .addComponent(headerPanel, GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
        .addGroup(backGroundLayout.createSequentialGroup()
            .addGap(10, 10, 10)
            .addComponent(NavigationPanel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
            .addGap(10, 10, 10)
            .addComponent(bodyPanel)
            .addGap(10, 10, 10)));

    backGroundLayout.setVerticalGroup(backGroundLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(backGroundLayout.createSequentialGroup()
            .addComponent(headerPanel)
            .addGap(10, 10, 10)
            .addGroup(backGroundLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(NavigationPanel, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                .addComponent(bodyPanel))
            .addGap(10, 10, 10)));

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(Alignment.LEADING)
            .addComponent(backGroundPanel));
    layout.setVerticalGroup(
        layout.createParallelGroup(Alignment.LEADING)
            .addComponent(backGroundPanel));

    pack();
  }
}

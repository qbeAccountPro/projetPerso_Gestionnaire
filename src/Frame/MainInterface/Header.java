package Frame.MainInterface;

import Frame.Component.BackGround.RoundedPanel;
import Frame.Component.Button.ButtonIcon;
import Frame.Event.Header.EventHeader;

import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;


public class Header extends RoundedPanel {
    private EventHeader eventHeader;
    private RoundedPanel panelHeader;


    public Header() {
        initComponents();
        setOpaque(false);
        setBackground(new Color(38,38,38));
        panelHeader.setLayout(new MigLayout(" right, inset 3", "[1]", "[]"));
    }

    public void initHeader(EventHeader eventHeader) {
        this.eventHeader = eventHeader;
        addHeader(new ImageIcon(getClass().getResource("../Image/Icon/LogIn.png")), 0);
        addEmpty();
        addHeader(new ImageIcon(getClass().getResource("../Image/Icon/email.png")), 1);
        addHeader(new ImageIcon(getClass().getResource("../Image/Icon/twitter.png")), 2);
        addHeader(new ImageIcon(getClass().getResource("../Image/Icon/instagram.png")), 3);
        addHeader(new ImageIcon(getClass().getResource("../Image/Icon/logout.png")), 4);

    }

    private void addEmpty() {
        panelHeader.add(new JLabel(), "push");
    }

    private void addHeader(Icon icon, int index) {
        ButtonIcon buttonIcon = new ButtonIcon();
        buttonIcon.setBackground(new Color(38,38,38));
        buttonIcon.setIcon(icon);
        panelHeader.add(buttonIcon );
        buttonIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                eventHeader.selected(index);
                setSelected(buttonIcon);
            }
        });

    }

    private void setSelected(ButtonIcon buttonIcon) {
        for (Component com : panelHeader.getComponents()) {
            if (com instanceof ButtonIcon) {
                ButtonIcon b = (ButtonIcon) com;
                b.setSelected(false);
            }
        }
        buttonIcon.setSelected(true);
    }

    private void initComponents() {
        
        panelHeader = new RoundedPanel();
        panelHeader.setBackground(new Color(38,38,38));
        GroupLayout panelHeaderLayout = new GroupLayout(panelHeader);
        panelHeaderLayout.setHorizontalGroup(
                panelHeaderLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 60, Short.MAX_VALUE));
        panelHeaderLayout.setVerticalGroup(
                panelHeaderLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addGap(0, 1200, Short.MAX_VALUE));
        this.setLayout(new BorderLayout());

        this.add(panelHeader);

    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        Area area = new Area(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        area.add(new Area(new Rectangle2D.Double(0, 20, getWidth(), getHeight())));
        g2.fill(area);
        g2.dispose();
        super.paint(grphcs);
    }
}

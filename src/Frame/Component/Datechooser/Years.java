package Frame.Component.Datechooser;

import java.awt.*;

import javax.swing.JButton;

import Frame.Component.BackGround.RoundedPanel;

public final class Years extends RoundedPanel {

    private Event event;
    private int startYear;

    private Button cmd1, cmd2, cmd3, cmd4, cmd5, cmd6, cmd7, cmd8, cmd9, cmd10,
            cmd11, cmd12, cmd13, cmd14, cmd15, cmd16, cmd17, cmd18, cmd19, cmd20;

    public Years() {
        initComponents();
    }

    public int showYear(int year) {
        year = calculateYear(year);
        for (int i = 0; i < getComponentCount(); i++) {
            JButton cmd = (JButton) getComponent(i);
            cmd.setText(year + "");
            year++;
        }
        return startYear;
    }

    private int calculateYear(int year) {
        year -= year % 10;
        startYear = year;
        return year;
    }

    private void addEvent() {
        for (int i = 0; i < getComponentCount(); i++) {
            ((Button) getComponent(i)).setEvent(event);
        }
    }

    private void initComponents() {
        Color bck38 = new Color(38, 38, 38);

        setBackground(bck38);
        setLayout(new java.awt.GridLayout(5, 4));

        cmd1 = new Button();
        cmd2 = new Button();
        cmd3 = new Button();
        cmd4 = new Button();
        cmd5 = new Button();
        cmd6 = new Button();
        cmd7 = new Button();
        cmd8 = new Button();
        cmd9 = new Button();
        cmd10 = new Button();
        cmd11 = new Button();
        cmd12 = new Button();
        cmd13 = new Button();
        cmd14 = new Button();
        cmd15 = new Button();
        cmd16 = new Button();
        cmd17 = new Button();
        cmd18 = new Button();
        cmd19 = new Button();
        cmd20 = new Button();

        Button[] yearListButtons = { cmd1, cmd2, cmd3, cmd4, cmd5, cmd6, cmd7, cmd8, cmd9, cmd10,
                cmd11, cmd12, cmd13, cmd14, cmd15, cmd16, cmd17, cmd18, cmd19, cmd20 };
        int incrementYear = 2010;
        
        for (Button button : yearListButtons) {
            button.setBackground(bck38);
            button.setForeground(new java.awt.Color(250, 250, 250));
            button.setText(String.valueOf(incrementYear));
            button.setName("year");
            button.setOpaque(true);
            add(button);
            incrementYear++;
        }

    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }

    public int next(int year) {
        showYear(year + 20);
        return startYear;
    }

    public int back(int year) {
        showYear(year - 20);
        return startYear;
    }

}

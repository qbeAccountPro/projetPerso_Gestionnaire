package Frame.Component.Datechooser;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

import Frame.Component.BackGround.RoundedPanel;

public final class Dates extends RoundedPanel {

    private Event event;
    private final int MONTH;
    private final int YEAR;
    private final int DAY;
    private int m;
    private int y;
    private int selectDay = 0;
    private int startDate;
    private int max_of_month;

    public Dates() {
        initComponents();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String toDay = df.format(date);
        DAY = Integer.valueOf(toDay.split("-")[0]);
        MONTH = Integer.valueOf(toDay.split("-")[1]);
        YEAR = Integer.valueOf(toDay.split("-")[2]);
    }

    public void showDate(int month, int year, SelectedDate select) {
        m = month;
        y = year;
        Calendar cd = Calendar.getInstance();
        cd.set(year, month - 1, 1);
        int start = cd.get(Calendar.DAY_OF_WEEK);
        max_of_month = cd.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (start == 1) {
            start += 7;
        }
        clear();
        start += 5;
        startDate = start;
        for (int i = 1; i <= max_of_month; i++) {
            Button cmd = (Button) getComponent(start);
            cmd.setColorSelected(getForeground());
            cmd.setText(i + "");
            if (i == DAY && month == MONTH && year == YEAR) {
                cmd.setBackground(new Color(38, 38, 38));
            } else {
                cmd.setBackground(new Color(38, 38, 38));
            }
            if (i == select.getDay() && month == select.getMonth() && year == select.getYear()) {
                cmd.setBackground(new Color(0, 120, 215));
                cmd.setForeground(new Color(255, 255, 255));
            }
            start++;
        }
    }

    private void clear() {
        for (int i = 7; i < getComponentCount(); i++) {
            ((JButton) getComponent(i)).setText("");
        }
    }

    public void clearSelected() {
        for (int i = 7; i < getComponentCount(); i++) {
            JButton cmd = (JButton) getComponent(i);
            if (MONTH == m && y == YEAR && !cmd.getText().equals("") && Integer.valueOf(cmd.getText()) == DAY) {
                cmd.setBackground(new Color(224, 214, 229));
                cmd.setForeground(new Color(75, 75, 75));
            } else {
                cmd.setBackground(new Color(38, 38, 38));

            }
        }
        selectDay = 0;
    }

    private void addEvent() {
        for (int i = 7; i < getComponentCount(); i++) {
            ((Button) getComponent(i)).setEvent(event);
        }
    }

    public void setSelected(int index) {
        selectDay = index;
    }

    private void initComponents() {
        setBackground(new Color(38, 38, 38));
        setForeground(new Color(250, 250, 250));
        setLayout(new GridLayout(7, 7));

        cmdMo = new Button();
        cmdTu = new Button();
        cmdWe = new Button();
        cmdTh = new Button();
        cmdFr = new Button();
        cmdSa = new Button();
        cmdSu = new Button();
        Button[] DayListButtons = { cmdMo, cmdTu, cmdWe, cmdTh, cmdFr, cmdSa, cmdSu };
        for (Button Button : DayListButtons) {
            Button.setBorder(BorderFactory.createEmptyBorder(1, 1, 5, 1));
            Button.setForeground(new Color(255, 255, 255));
        }

        cmdMo.setText("Mo");
        add(cmdMo);
        cmdTu.setText("Tu");
        add(cmdTu);
        cmdWe.setText("We");
        add(cmdWe);
        cmdTh.setText("Th");
        add(cmdTh);
        cmdFr.setText("Fr");
        add(cmdFr);
        cmdSa.setText("Sa");
        add(cmdSa);
        cmdSu.setText("Su");
        add(cmdSu);

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
        cmd21 = new Button();
        cmd22 = new Button();
        cmd23 = new Button();
        cmd24 = new Button();
        cmd25 = new Button();
        cmd26 = new Button();
        cmd27 = new Button();
        cmd28 = new Button();
        cmd29 = new Button();
        cmd30 = new Button();
        cmd31 = new Button();
        cmd32 = new Button();
        cmd33 = new Button();
        cmd34 = new Button();
        cmd35 = new Button();
        cmd36 = new Button();
        cmd37 = new Button();
        cmd38 = new Button();
        cmd39 = new Button();
        cmd40 = new Button();
        cmd41 = new Button();
        cmd42 = new Button();
        Button[] NumberOfdaysListButtons = { cmd1, cmd2, cmd3, cmd4, cmd5, cmd6, cmd7,
                cmd8, cmd9, cmd10, cmd11, cmd12, cmd13, cmd14, cmd15, cmd16, cmd17, cmd18, cmd19, cmd20,
                cmd21, cmd22, cmd23, cmd24, cmd25, cmd26, cmd27, cmd28, cmd29, cmd30,
                cmd31, cmd32, cmd33, cmd34, cmd35, cmd36, cmd37, cmd38, cmd39, cmd40,
                cmd41, cmd42 };

        int dayIncrement = 1;
        int buttonsConteur = 1;

        for (Button button : NumberOfdaysListButtons) {
            button.setName("day");
            button.setForeground(Color.white);
            button.setBackground(new Color(38, 38, 38));
            button.setFont(new Font("Verdana", 0, 12));
            if (buttonsConteur < 3) {
                buttonsConteur++;
            } else if (dayIncrement < 31) {
                button.setText(String.valueOf(dayIncrement));
                dayIncrement++;
                add(button);
            } else {
                add(button);
            }
        }
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }

    private Button cmd1, cmd2, cmd3, cmd4, cmd5, cmd6, cmd7,
            cmd8, cmd9, cmd10, cmd11, cmd12, cmd13, cmd14, cmd15, cmd16, cmd17, cmd18, cmd19, cmd20,
            cmd21, cmd22, cmd23, cmd24, cmd25, cmd26, cmd27, cmd28, cmd29, cmd30,
            cmd31, cmd32, cmd33, cmd34, cmd35, cmd36, cmd37, cmd38, cmd39, cmd40,
            cmd41, cmd42;
    private Button cmdMo, cmdTu, cmdWe, cmdTh, cmdFr, cmdSa, cmdSu;

    public void next() {
        if (selectDay == max_of_month) {
            selectDay = 0;
        }
        JButton cmd = (JButton) getComponent(startDate - 1 + selectDay + 1);
        String n = cmd.getText();
        if (!n.equals("") && Integer.valueOf(n) <= max_of_month) {
            selectDay++;
            event.execute(null, selectDay);
            cmd.setBackground(new Color(206, 110, 245));
        }
    }

    public void back() {
        if (selectDay <= 1) {
            selectDay = max_of_month + 1;
        }
        JButton cmd = (JButton) getComponent(startDate - 1 + selectDay - 1);
        String n = cmd.getText();
        if (!n.equals("") && cmd.getName() != null) {
            selectDay--;
            event.execute(null, selectDay);
            cmd.setBackground(new Color(206, 110, 245));
        }
    }

    public void up() {
        JButton cmd = (JButton) getComponent(startDate - 1 + selectDay - 7);
        String n = cmd.getText();
        if (!n.equals("") && cmd.getName() != null) {
            selectDay -= 7;
            event.execute(null, selectDay);
            cmd.setBackground(new Color(206, 110, 245));
        }
    }

    public void down() {
        if (getComponents().length > startDate - 1 + selectDay + 7) {
            JButton cmd = (JButton) getComponent(startDate - 1 + selectDay + 7);
            String n = cmd.getText();
            if (!n.equals("") && cmd.getName() != null) {
                selectDay += 7;
                event.execute(null, selectDay);
                cmd.setBackground(new Color(206, 110, 245));
            }
        }
    }

}

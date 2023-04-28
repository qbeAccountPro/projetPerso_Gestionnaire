package Frame.Component.Datechooser;


import java.awt.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import java.awt.event.*;
import Frame.Component.BackGround.RoundedPanel;
import Frame.Form.Planning;

public final class dateChooser extends RoundedPanel {

    public JTextField getTextRefernce() {
        return textRefernce;
    }

    public void addEventDateChooser(EventDateChooser event) {
        events.add(event);
    }

    private JTextField textRefernce;
    private final String MONTH_ENGLISH[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private String dateFormat = "dd-MM-yyyy";
    private int MONTH = 1;
    private int YEAR = 2021;
    private int DAY = 1;
    private int STATUS = 1;   //  1 is day 2 is month  3 is year
    private int startYear;
    private static SelectedDate selectedDate = new SelectedDate();
    private List<EventDateChooser> events;

    public dateChooser() {
        initComponents();
        execute();
    }

    private void execute() {
        setForeground(new Color(38, 38, 38));
        events = new ArrayList<>();
        popup.add(this);
        toDay(false);
    }

    private void setText(boolean runEvent, int act) {
        if (textRefernce != null) {
            try {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                Date date = df.parse(DAY + "-" + MONTH + "-" + YEAR);
                textRefernce.setText(new SimpleDateFormat(dateFormat).format(date));
            } catch (ParseException e) {
                System.err.println(e);
            }
        }
        if (runEvent) {
            runEvent(act);
        }
    }

    private void runEvent(int act) {
        SelectedAction action = new SelectedAction() {
            @Override
            public int getAction() {
                return act;
            }
        };
        for (EventDateChooser event : events) {
            event.dateSelected(action, selectedDate);
        }
    }

    private Event getEventDay(Dates dates) {
        return (MouseEvent evt, int num) -> {
            dates.clearSelected();
            dates.setSelected(num);
            DAY = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 1);
            Planning.setNewDay();
        };
    }

    private Event getEventMonth() {
        return (MouseEvent evt, int num) -> {
            MONTH = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 2);
            Dates d = new Dates();
            d.setForeground(getForeground());
            d.setEvent(getEventDay(d));
            d.showDate(MONTH, YEAR, selectedDate);
            if (slide.slideToDown(d)) {
                cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
                cmdYear.setText(YEAR + "");
                STATUS = 1;
            }
        };
    }

    private Event getEventYear() {
        return (MouseEvent evt, int num) -> {
            YEAR = num;
            selectedDate.setDay(DAY);
            selectedDate.setMonth(MONTH);
            selectedDate.setYear(YEAR);
            setText(true, 3);
            Months d = new Months();
            d.setEvent(getEventMonth());
            if (slide.slideToDown(d)) {
                cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
                cmdYear.setText(YEAR + "");
                STATUS = 2;
            }
        };
    }

    private void toDay(boolean runEvent) {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String toDay = df.format(date);
        DAY = Integer.valueOf(toDay.split("-")[0]);
        MONTH = Integer.valueOf(toDay.split("-")[1]);
        YEAR = Integer.valueOf(toDay.split("-")[2]);
        selectedDate.setDay(DAY);
        selectedDate.setMonth(MONTH);
        selectedDate.setYear(YEAR);
        dates.showDate(MONTH, YEAR, selectedDate);
        slide.slideNon(dates);
        cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
        cmdYear.setText(YEAR + "");
        setText(runEvent, 0);
    }

    public void toDay() {
        toDay(true);
    }

    private void setDateNext() {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.showDate(MONTH, YEAR, selectedDate);
        if (slide.slideToLeft(dates)) {
            cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
            cmdYear.setText(YEAR + "");
        }
    }

    private void setDateBack() {
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.showDate(MONTH, YEAR, selectedDate);
        if (slide.slideToRight(dates)) {
            cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
            cmdYear.setText(YEAR + "");
        }
    }

    private void setYearNext() {
        Years years = new Years();
        years.setEvent(getEventYear());
        startYear = years.next(startYear);
        slide.slideToLeft(years);
    }

    private void setYearBack() {
        if (startYear >= 1000) {
            Years years = new Years();
            years.setEvent(getEventYear());
            startYear = years.back(startYear);
            slide.slideToLeft(years);
        }
    }


    private void initComponents() {

        popup = new JPopupMenu(){
            @Override
            protected void paintComponent(Graphics grphcs) {
                grphcs.setColor(new Color(200, 200, 200));
                grphcs.fillRect(0, 0, getWidth(), getHeight());
                grphcs.setColor(Color.WHITE);
                grphcs.fillRect(1, 1, getWidth() - 2, getHeight() - 2);
            }
        };
        header = new RoundedPanel();
        cmdForward = new Button();
        MY = new JLayeredPane();
        cmdMonth = new Button();
        lb = new JLabel();
        cmdYear = new Button();
        cmdPrevious = new Button();
        slide = new Slider();

        setBackground(new Color(0,0,0));

        header.setBackground(new Color(0,0,0));
        header.setMaximumSize(new Dimension(262, 40));

        cmdForward.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdForward.setIcon(new ImageIcon(getClass().getResource("forward.png"))); 
        cmdForward.setFocusable(true);
        cmdForward.setPaintBackground(false);
        cmdForward.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cmdForwardActionPerformed(evt);
            }
        });

        FlowLayout flowLayout1 = new FlowLayout(FlowLayout.CENTER, 5, 0);
        flowLayout1.setAlignOnBaseline(true);
        MY.setLayout(flowLayout1);

        cmdMonth.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdMonth.setForeground(new Color(255, 255, 255));
        cmdMonth.setText("January");
        cmdMonth.setFocusPainted(false);
        cmdMonth.setFont(new Font("Kh Content", 0, 14)); 
        cmdMonth.setPaintBackground(false);
        cmdMonth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cmdMonthActionPerformed(evt);
            }
        });
        MY.add(cmdMonth);

        lb.setForeground(new Color(255, 255, 255));
        lb.setHorizontalAlignment(SwingConstants.CENTER);
        lb.setText(" ");
        MY.add(lb);

        cmdYear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdYear.setForeground(new Color(255, 255, 255));
        cmdYear.setText("2018");
        cmdYear.setFocusPainted(false);
        cmdYear.setFont(new Font("Kh Content", 0, 14)); 
        cmdYear.setPaintBackground(false);
        cmdYear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cmdYearActionPerformed(evt);
            }
        });
        MY.add(cmdYear);

        cmdPrevious.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdPrevious.setIcon(new ImageIcon(getClass().getResource("previous.png"))); 
        cmdPrevious.setFocusable(true);
        cmdPrevious.setPaintBackground(false);
        cmdPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cmdPreviousActionPerformed(evt);
            }
        });
        cmdPrevious.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                cmdPreviousKeyPressed(evt);
            }
        });

        GroupLayout headerLayout = new GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdPrevious, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MY, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdForward, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(20, Short.MAX_VALUE, Short.MAX_VALUE)
                .addGroup(headerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cmdPrevious, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdForward, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(20, Short.MAX_VALUE, Short.MAX_VALUE)
                    )
        );

        slide.setLayout(new BoxLayout(slide, BoxLayout.LINE_AXIS));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(slide, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(header, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(header, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10,  10)
                .addComponent(slide, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
               )
        );
    }

    private void cmdPreviousActionPerformed(ActionEvent evt) {
        if (STATUS == 1) {   //  Date
            if (MONTH == 1) {
                MONTH = 12;
                YEAR--;
            } else {
                MONTH--;
            }
            setDateBack();
        } else if (STATUS == 3) {    //  Year
            setYearBack();
        } else {
            if (YEAR >= 1000) {
                YEAR--;
                Months months = new Months();
                months.setEvent(getEventMonth());
                slide.slideToLeft(months);
                cmdYear.setText(YEAR + "");
            }
        }
    }

    private void cmdForwardActionPerformed(ActionEvent evt) {
        if (STATUS == 1) {   //  Date
            if (MONTH == 12) {
                MONTH = 1;
                YEAR++;
            } else {
                MONTH++;
            }
            setDateNext();
        } else if (STATUS == 3) {    //  Year
            setYearNext();
        } else {
            YEAR++;
            Months months = new Months();
            months.setEvent(getEventMonth());
            slide.slideToLeft(months);
            cmdYear.setText(YEAR + "");
        }
    }

    private void cmdMonthActionPerformed(ActionEvent evt) {
        if (STATUS != 2) {
            STATUS = 2;
            Months months = new Months();
            months.setEvent(getEventMonth());
            slide.slideToDown(months);
        } else {
            Dates dates = new Dates();
            dates.setForeground(getForeground());
            dates.setEvent(getEventDay(dates));
            dates.showDate(MONTH, YEAR, selectedDate);
            slide.slideToDown(dates);
            STATUS = 1;
        }
    }

    private void cmdYearActionPerformed(ActionEvent evt) {
        if (STATUS != 3) {
            STATUS = 3;
            Years years = new Years();
            years.setEvent(getEventYear());
            startYear = years.showYear(YEAR);
            slide.slideToDown(years);
        } else {
            Dates dates = new Dates();
            dates.setForeground(getForeground());
            dates.setEvent(getEventDay(dates));
            dates.showDate(MONTH, YEAR, selectedDate);
            slide.slideToDown(dates);
            STATUS = 1;
        }
    }

    private void cmdPreviousKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            Component com = slide.getComponent(0);
            if (com instanceof Dates) {
                Dates d = (Dates) com;
                d.up();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            Component com = slide.getComponent(0);
            if (com instanceof Dates) {
                Dates d = (Dates) com;
                d.down();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            Component com = slide.getComponent(0);
            if (com instanceof Dates) {
                Dates d = (Dates) com;
                d.back();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            Component com = slide.getComponent(0);
            if (com instanceof Dates) {
                Dates d = (Dates) com;
                d.next();
            }
        }
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setSelectedDate(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String d = df.format(date);
        DAY = Integer.valueOf(d.split("-")[0]);
        MONTH = Integer.valueOf(d.split("-")[1]);
        YEAR = Integer.valueOf(d.split("-")[2]);
        selectedDate.setDay(DAY);
        selectedDate.setMonth(MONTH);
        selectedDate.setYear(YEAR);
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.setSelected(DAY);
        dates.showDate(MONTH, YEAR, selectedDate);
        slide.slideNon(dates);
        cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
        cmdYear.setText(YEAR + "");
        setText(true, 0);
        STATUS = 1;
    }

    private JLayeredPane MY;
    private Button cmdForward;
    private Button cmdMonth;
    private Button cmdPrevious;
    private Button cmdYear;
    private RoundedPanel header;
    private JLabel lb;
    private JPopupMenu popup;
    private Slider slide;


    public static SelectedDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(SelectedDate selectedDate) {
        dateChooser.selectedDate = selectedDate;
        DAY = selectedDate.getDay();
        MONTH = selectedDate.getMonth();
        YEAR = selectedDate.getYear();
        Dates dates = new Dates();
        dates.setForeground(getForeground());
        dates.setEvent(getEventDay(dates));
        dates.setSelected(DAY);
        dates.showDate(MONTH, YEAR, selectedDate);
        slide.slideNon(dates);
        cmdMonth.setText(MONTH_ENGLISH[MONTH - 1]);
        cmdYear.setText(YEAR + "");
        setText(true, 0);
        STATUS = 1;
    }

    @Override
    public void setForeground(Color color) {
        super.setForeground(color);
        if (header != null) {
            header.setBackground(color);
            toDay(false);
        }
    }
}

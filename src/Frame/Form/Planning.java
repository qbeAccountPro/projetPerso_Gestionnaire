package Frame.Form;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import javax.swing.table.TableColumnModel;
import javax.swing.text.PlainDocument;

import Frame.MainFrame;
import Frame.Component.BackGround.RoundedPanel;
import Frame.Component.Button.RoundedButton;
import Frame.Component.Datechooser.*;
import Frame.Component.ScrollBar.ScrollBarCustom;
import Frame.Component.Spinner.HourSpinner;
import Frame.Constructor.TimeSlot;
import Frame.Constructor.User;
import Frame.Database.loadPlanning;
import Frame.Event.DialogError.ErrorDialog;
import Frame.Event.InputRestriction.InputRestriction;
import Frame.Event.Planning.*;

public class Planning extends JPanel {
    private User currentUser;
    private Color colorBackground = new Color(38, 38, 38);
    private dateChooser dateChooserPanel;
    private static JTable table;
    private static CustomTableModelPlanning myCustomTableModel;

    public Planning(MainFrame mainFrame, User currentUser) {
        this.currentUser = currentUser;
        initComponent();
        setOpaque(false);
    }

    private void initComponent() {
        RoundedPanel backgroundPanel = new RoundedPanel();

        RoundedPanel eventBackGround = eventPanel();
        dateChooserPanel = new dateChooser();
        RoundedPanel planning = planningPanel();
        backgroundPanel.setBackground(new Color(0, 0, 0));
        GroupLayout layoutMainBackground = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(layoutMainBackground);

        layoutMainBackground.setHorizontalGroup(layoutMainBackground.createSequentialGroup()
                .addGroup(layoutMainBackground.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layoutMainBackground.createSequentialGroup()
                                .addComponent(dateChooserPanel, 400, 480, 480)
                                .addGap(10)
                                .addComponent(eventBackGround, 400, 480, 480))
                        .addComponent(planning, 950, Short.MAX_VALUE, Short.MAX_VALUE)));

        layoutMainBackground.setVerticalGroup(layoutMainBackground.createSequentialGroup()
                .addGroup(layoutMainBackground.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(dateChooserPanel, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addComponent(eventBackGround, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE))
                .addGap(10)
                .addComponent(planning, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE, Short.MAX_VALUE));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundPanel, 970, 970, 970));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(1)
                .addComponent(backgroundPanel, 730, 730, 730)
                .addGap(1));
    }

    private RoundedPanel eventPanel() {
        RoundedPanel eventBackGroundPanel = new RoundedPanel();
        eventBackGroundPanel.setBackground(colorBackground);

        JSpinner hourStart = new HourSpinner();
        JSpinner hourEnd = new HourSpinner();
        hourStart.getEditor().getComponent(0).setBackground(colorBackground);
        hourStart.getEditor().getComponent(0).setForeground(Color.white);
        hourStart.setBackground(colorBackground);
        hourStart.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));

        hourEnd.getEditor().getComponent(0).setBackground(colorBackground);
        hourEnd.getEditor().getComponent(0).setForeground(Color.white);
        hourEnd.setBackground(colorBackground);
        hourEnd.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));

        JLabel hourLb = new JLabel("To");
        hourLb.setForeground(Color.white);

       
        String textLocation = new String("Location");
        JTextField location = new JTextField(textLocation);
        PlainDocument locationDoc = (PlainDocument) location.getDocument();
        locationDoc.setDocumentFilter(InputRestriction.LengthFilter(location, 80));
        location.setBackground(colorBackground);
        location.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
        location.setForeground(Color.GRAY);
        location.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (location.getText().equals(textLocation)) {
                    location.setText("");
                    location.setForeground(Color.white);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (location.getText().isEmpty()) {
                    location.setForeground(Color.GRAY);
                    location.setText(textLocation);
                }
            }
        });

        String textEventName = new String("Name of event");
        JTextField eventName = new JTextField(textEventName);
        PlainDocument eventNameDoc = (PlainDocument) eventName.getDocument();
        eventNameDoc.setDocumentFilter(InputRestriction.LengthFilter(eventName, 80));
        eventName.setBackground(colorBackground);
        eventName.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
        eventName.setForeground(Color.GRAY);
        eventName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (eventName.getText().equals(textEventName)) {
                    eventName.setText("");
                    eventName.setForeground(Color.white);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (eventName.getText().isEmpty()) {
                    eventName.setForeground(Color.GRAY);
                    eventName.setText(textEventName);
                }
            }
        });

        RoundedButton today = new RoundedButton("Today");
        today.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dateChooserPanel.toDay();
                setNewDay();

            }

        });

        RoundedButton delete = new RoundedButton("Delete");
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean databaseFound = false;
                SimpleDateFormat formatterHour = new SimpleDateFormat("HH:mm");
                String formattedHourStart = formatterHour.format((Date) hourStart.getValue());
                String formattedhourEnd = formatterHour.format((Date) hourEnd.getValue());
                SelectedDate d = dateChooser.getSelectedDate();
                String dateString = d.getDay() + "-" + d.getMonth() + "-" + d.getYear();
                SimpleDateFormat formatterDate = new SimpleDateFormat("dd-MM-yyyy");
                Date date;
                try {
                    date = formatterDate.parse(dateString);
                } catch (ParseException e1) {
                    date = null;}
                formatterDate = new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = formatterDate.format(date);
                List<TimeSlot> planningList = loadPlanning.getPlanningList();
                for (TimeSlot drafting : planningList) {
                    if (drafting.getDate().equals(currentDate)) {
                        int checkStartHour = formattedHourStart.compareTo(drafting.getHourStart());
                        int CheckEndHour = formattedhourEnd.compareTo(drafting.getHourEnd());
                        if (checkStartHour == 0 && CheckEndHour == 0 & drafting.getDescription().equals(eventName.getText())) {
                            loadPlanning.deletePlanning(drafting);
                            setNewDay();
                            databaseFound = true;
                            break;
                        } 
                    }
                }
                if(!databaseFound){
                    new ErrorDialog("The event to delete, doesn't exist!\n You need the same Hour and Event name");
                }
            }
        });

        RoundedButton save = new RoundedButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean condition = true;
                if (hourStart.getValue().equals(hourEnd.getValue())) {
                    new ErrorDialog("The Start and End hours can be the same !");
                    condition = false;
                } else if (((Date) hourEnd.getValue()).compareTo((Date) hourStart.getValue()) < 0) {
                    new ErrorDialog("The Start hour can't be greather than End hour  !");
                    condition = false;
                } else if (eventName.getText().contains(textEventName)) {
                    new ErrorDialog("You need a name for your event !");
                    condition = false;
                } else {
                    SimpleDateFormat formatterHourStart = new SimpleDateFormat("HH:mm");
                    String formattedHourStart = formatterHourStart.format((Date) hourStart.getValue());
                    SimpleDateFormat formatterhourEnd = new SimpleDateFormat("HH:mm");
                    String formattedhourEnd = formatterhourEnd.format((Date) hourEnd.getValue());
                    SelectedDate d = dateChooser.getSelectedDate();
                    String dateString = d.getDay() + "-" + d.getMonth() + "-" + d.getYear();
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    Date date;
                    try {
                        date = format.parse(dateString);
                    } catch (ParseException e1) {
                        date = null;
                        condition = false;
                    }
                    format = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDate = format.format(date);
                    List<TimeSlot> planningList = loadPlanning.getPlanningList();
                    for (TimeSlot drafting : planningList) {
                        if (drafting.getDate().equals(currentDate)) {
                            int comparaisonHourEnd_NewHourStart = formattedHourStart.compareTo(drafting.getHourEnd());
                            int comparaisonHourStart_NewHourStart = formattedHourStart
                                    .compareTo(drafting.getHourStart());
                            int comparaisonHourStart_NewHourEnd = formattedhourEnd.compareTo(drafting.getHourStart());
                            if (comparaisonHourStart_NewHourStart >= 0 && comparaisonHourEnd_NewHourStart <= 0) {
                                new ErrorDialog("Hour of start isn't possible, already exist event at this time : "
                                        + drafting.getDescription());
                                condition = false;
                                break;
                            } else if (comparaisonHourStart_NewHourStart < 0 && comparaisonHourStart_NewHourEnd > 0) {
                                new ErrorDialog("Hour of start isn't possible, already exist event at this time : "
                                        + drafting.getDescription());
                                condition = false;
                                break;
                            }
                        }
                    }
                    String verifLocation = location.getText();
                    if(verifLocation.equals("Location")){
                        verifLocation = "";
                    }
                    if (condition == true) {
                        TimeSlot draft = new TimeSlot(eventName.getText(), verifLocation,
                                currentUser.getName() + " " + currentUser.getSurname(), currentDate, formattedHourStart,
                                formattedhourEnd);
                        loadPlanning.addPlanning(draft);
                        setNewDay();
                    }
                }
            }

        });
        GroupLayout layout = new GroupLayout(eventBackGroundPanel);
        eventBackGroundPanel.setLayout(layout);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(eventName))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(hourStart)
                                .addGap(10)
                                .addComponent(hourLb)
                                .addGap(10)
                                .addComponent(hourEnd))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(location))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(today)
                                .addGap(20, Short.MAX_VALUE, Short.MAX_VALUE)
                                .addComponent(delete)
                                .addGap(20)
                                .addComponent(save)))
                .addGap(10));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(eventName, 30, 30, 30))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(hourStart, 30, 30, 30)
                        .addComponent(hourLb, 30, 30, 30)
                        .addComponent(hourEnd, 30, 30, 30))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(location, 30, 30, 30))
                .addGap(40)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(today, 30, 30, 30)
                        .addComponent(delete, 30, 30, 30)
                        .addComponent(save, 30, 30, 30)));
        return eventBackGroundPanel;
    }

    private RoundedPanel planningPanel() {
        RoundedPanel planning = new RoundedPanel();
        planning.setBackground(colorBackground);
        myCustomTableModel = new CustomTableModelPlanning();
        table = new JTable(myCustomTableModel);
        setTableStyle(table, myCustomTableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBar(new ScrollBarCustom());
        ScrollBarCustom SBC = new ScrollBarCustom();
        SBC.setOrientation(JScrollBar.VERTICAL);
        scrollPane.setVerticalScrollBar(SBC);
        scrollPane.setBackground(colorBackground);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 0));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        GroupLayout Calendarlayout = new GroupLayout(planning);
        planning.setLayout(Calendarlayout);

        Calendarlayout.setHorizontalGroup(Calendarlayout.createSequentialGroup()
                .addGap(5)
                .addComponent(scrollPane)
                .addGap(5));
        Calendarlayout.setVerticalGroup(Calendarlayout.createSequentialGroup()
                .addGap(5)
                .addComponent(scrollPane)
                .addGap(5));
        return planning;
    }

    public static void setNewDay() {
        int rowCount = myCustomTableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            myCustomTableModel.setValueAt("", i, 1);
            myCustomTableModel.setValueAt("", i, 2);
        }
        setTableStyle(table, myCustomTableModel);
    }

    public static void setTableStyle(JTable table, CustomTableModelPlanning myCustomTableModel) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setRowHeight(30);
        TableColumnModel headerColumnModel = table.getTableHeader().getColumnModel();
        CustomHeaderRenderer rendererHeader = new CustomHeaderRenderer();
        for (int i = 0; i < headerColumnModel.getColumnCount(); i++) {
            headerColumnModel.getColumn(i).setHeaderRenderer(rendererHeader);
        }

        TableColumnModel contentColumnModel = table.getColumnModel();
        table.getColumnModel().getColumn(0).setMinWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(450);
        table.getColumnModel().getColumn(2).setPreferredWidth(450);

        List<TimeSlot> planningList = loadPlanning.getPlanningList();
        SelectedDate d = dateChooser.getSelectedDate();
        String dateString = d.getDay() + "-" + d.getMonth() + "-" + d.getYear();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = format.parse(dateString);
        } catch (ParseException e1) {
            date = null;
        }
        int conteurEvent = 0;
        format = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = format.format(date);
        for (TimeSlot drafting : planningList) {
            if (drafting.getDate().equals(formattedDate)) {
                conteurEvent++;
                for (int row = 0; row < myCustomTableModel.getRowCount(); row++) {
                    if (myCustomTableModel.getValueAt(row, 0).equals(drafting.getHourStart())) {
                        myCustomTableModel.setValueAt(drafting.getDescription(), row, 1);
                        myCustomTableModel.setValueAt(drafting.getLocalisation(), row, 2);
                        break;
                    }
                }
            }
        }

        String[][] hour = new String[conteurEvent][2];
        int x = 0;
        for (TimeSlot drafting : planningList) {
            if (drafting.getDate().equals(formattedDate)) {
                hour[x][0] = drafting.getHourStart();
                hour[x][1] = drafting.getHourEnd();
                x++;
            }
        }

        CustomCellRenderer rendererCell = new CustomCellRenderer(hour);
        for (int i = 0; i < contentColumnModel.getColumnCount(); i++) {
            contentColumnModel.getColumn(i).setCellRenderer(rendererCell);
        }

    }

}

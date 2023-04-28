package Frame.Component.Datechooser;

import java.awt.*;

import Frame.Component.BackGround.RoundedPanel;

public final class Months extends RoundedPanel {

    private Event event;
    private Button cmd1,cmd2, cmd3, cmd4, cmd5, cmd6, cmd7, cmd8, cmd9, cmd10, cmd11, cmd12 ;


    public Months() {
        initComponents();
    }

    private void addEvent() {
        for (int i = 0; i < getComponentCount(); i++) {
            ((Button) getComponent(i)).setEvent(event);
        }
    }

    private void initComponents() {

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

        Color bck38 = new Color(38, 38, 38);

        setBackground(bck38);
        setLayout(new GridLayout(4, 4));

        cmd1.setText("January");
        cmd1.setName("1");

        cmd2.setText("February");
        cmd2.setName("2");

        cmd3.setText("March");
        cmd3.setName("3");

        cmd4.setText("April");
        cmd4.setName("4");

        cmd5.setText("May");
        cmd5.setName("5");

        cmd6.setText("June");
        cmd6.setName("6");

        cmd7.setText("July");
        cmd7.setName("7");

        cmd8.setText("August");
        cmd8.setName("8");

        cmd9.setText("September");
        cmd9.setName("9");

        cmd10.setText("October");
        cmd10.setName("10");

        cmd11.setText("November");
        cmd11.setName("11");

        cmd12.setText("December");
        cmd12.setName("12");

        Button[] monthListButoons ={cmd1,cmd2, cmd3, cmd4, cmd5, cmd6, cmd7, cmd8, cmd9, cmd10, cmd11, cmd12};
        for (Button button : monthListButoons) {
            button.setBackground(bck38);
            button.setForeground(new Color(250, 250, 250));
            button.setOpaque(true);
            add(button);

        }
      
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
        addEvent();
    }


}

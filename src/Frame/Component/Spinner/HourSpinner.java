package Frame.Component.Spinner;

import javax.swing.*;

import java.util.Calendar;
import java.util.Date;

public class HourSpinner extends JSpinner {
    public HourSpinner() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 0);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date initialDate = calendar.getTime();

        SpinnerModel spinnerModel = new SpinnerDateModel(initialDate, null, null, Calendar.SECOND);
        setModel(spinnerModel);
        JSpinner timeSpinner = new JSpinner(spinnerModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        setEditor(timeEditor); 
        }

    public HourSpinner(String stringHour) {
        String[] parties = stringHour.split(":");
        int heureInt = Integer.parseInt(parties[0]);
        int minuteInt = Integer.parseInt(parties[1]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 0);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, heureInt);
        calendar.set(Calendar.MINUTE, minuteInt);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date initialDate = calendar.getTime();

        SpinnerModel spinnerModel = new SpinnerDateModel(initialDate, null, null, Calendar.SECOND);
        setModel(spinnerModel);
        JSpinner timeSpinner = new JSpinner(spinnerModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
        setEditor(timeEditor);
    }

}
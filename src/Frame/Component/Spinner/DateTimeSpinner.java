package Frame.Component.Spinner;

import javax.swing.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeSpinner extends JSpinner {
    public DateTimeSpinner() {
        Date now = new Date();
        SpinnerModel spinnerModel = new SpinnerDateModel(now, null, null, Calendar.HOUR_OF_DAY);
        setModel(spinnerModel);
        JSpinner timeSpinner = new JSpinner(spinnerModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "yyyy-MM-dd HH:mm:ss");
        setEditor(timeEditor);

    }

    public DateTimeSpinner(String stringDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = dateFormat.parse(stringDate);
        } catch (ParseException e) {
            date = null;
        }
        SpinnerModel spinnerModel = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        setModel(spinnerModel);
        JSpinner timeSpinner = new JSpinner(spinnerModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "yyyy-MM-dd HH:mm:ss");
        setEditor(timeEditor);

    }

}
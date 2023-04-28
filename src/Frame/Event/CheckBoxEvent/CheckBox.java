package Frame.Event.CheckBoxEvent;

import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import java.awt.event.MouseEvent;

public class CheckBox extends DefaultCellEditor {
    public CheckBox(JCheckBox checkBox) {
        super(checkBox);
    }

    @Override
    public boolean isCellEditable(EventObject event) {
        if (event instanceof MouseEvent) {
            return ((MouseEvent) event).getClickCount() >= 1;
        }
        return false;
    }
}

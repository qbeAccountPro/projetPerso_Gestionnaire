package Frame.Event.InputRestriction;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;


import Frame.Constructor.User;

import java.awt.event.*;

public class InputRestriction implements ActionListener {

    private JTextField tfNumberPhone;
    private JFrame frame;

    public InputRestriction(JTextField tfNumberPhone, JFrame frame, User newUser) {
        this.tfNumberPhone = tfNumberPhone;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String phone = tfNumberPhone.getText();
        if (isValidPhoneNumber(phone)) {
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a valid phone number in the format 06 00 00 00 00", "Invalid Phone Number", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isValidPhoneNumber(String phone) {
        String PHONE_REGEX = "^06\\s\\d{2}\\s\\d{2}\\s\\d{2}\\s\\d{2}$";
        return phone.matches(PHONE_REGEX);
    }

    public static DocumentFilter LengthFilter(JTextField textField, int i) {
        DocumentFilter descriptionFilter = new DocumentFilter() {
            public void insertString(FilterBypass fb, int offset,
                    String text, AttributeSet attr)
                    throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length()) <= i) {
                    super.insertString(fb, offset, text, attr);
                }
            }
            public void replace(FilterBypass fb, int offset, int length,
                    String text, AttributeSet attrs)
                    throws BadLocationException {
                if ((fb.getDocument().getLength() - length + text.length()) <= i) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };
        return descriptionFilter;
    }
}

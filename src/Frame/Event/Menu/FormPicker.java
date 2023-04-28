package Frame.Event.Menu;

import javax.swing.*;

public class FormPicker extends JPanel {
    private JLabel jLabel1;

    public FormPicker(int index) {
        initComponents();
        setOpaque(false);
        switch(index){
            case 0 :
            jLabel1.setText("Form " + index + " StaffManagement");
            break;

            case 1 :
            jLabel1.setText("Form " + index + " TodoList");
            break;

            case 2 : 
            jLabel1.setText("Form " + index + " Archive");
            break;

            case 3 :
            jLabel1.setText("Form " + index + " Planning");
            break;

            case 4 :
            jLabel1.setText("Form " + index + " Projet");
            break;

            case 5 : 
            jLabel1.setText("Form " + index + " Contact");
            break;

            case 6 : 
            jLabel1.setText("Form " + index + " Progress Report");
            break;

            case 7 :
            jLabel1.setText("Form " + index + " Help");
            break;

            default : 
            jLabel1.setText("Incorrect index : " + index );
            break;

        }
              
    }

    private void initComponents() {

        jLabel1 = new JLabel();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(137, 137, 137));
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Form");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );
    }
 
}

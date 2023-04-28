package Frame.Form;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import Frame.Component.BackGround.RoundedPanel;

public class Help extends RoundedPanel{
    
    public Help(){
        setBackground(new Color(38, 38, 38));
        ImageIcon icone = new ImageIcon(getClass().getResource("../Image/Error404.png"));
        JLabel imageLabel = new JLabel(icone);
        this.setLayout(new BorderLayout());
        add(imageLabel, BorderLayout.CENTER);
       
    }
}

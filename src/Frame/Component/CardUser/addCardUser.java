package Frame.Component.CardUser;

import Frame.Component.Avatar.ImageAvatar;
import Frame.Component.BackGround.RoundedPanel;

import java.awt.*;
import javax.swing.*;

public class addCardUser extends RoundedPanel {
    public addCardUser(String pathIcon, String name, String surname, String job, String company, String email,
            String phoneNumber, String speciality) {
        // Color
        Color backGroundColor = new Color(255, 255, 255);
        Color foreGroundAvatar = new Color(231, 231, 231);

        // Patch Icon
        String telPath = "../../Image/Icon/telephone.png";
        String mailPatch = "../../Image/Icon/emailUser.png";
        String companyPath = "../../Image/Icon/company.png";

        ImageIcon telIcon = new ImageIcon(getClass().getResource(telPath));
        ImageIcon mailIcon = new ImageIcon(getClass().getResource(mailPatch));
        ImageIcon companyIcon = new ImageIcon(getClass().getResource(companyPath));

        // Label Title
        JLabel email_Lb = new JLabel(mailIcon);
        JLabel phoneNumber_Lb = new JLabel(telIcon);
        JLabel company_Lb = new JLabel(companyIcon);

        // Label User
        JLabel userName_Lb = new JLabel(name);
        JLabel userJob_Lb = new JLabel(job);
        JLabel userEmail_Lb = new JLabel(email);
        JLabel userPhoneNumber_Lb = new JLabel(phoneNumber);
        JLabel userSurname_Lb = new JLabel(surname);
        JLabel userSpeciality_Lb = new JLabel(speciality);
        JLabel userCompany_Lb = new JLabel(company);

        // Set up the FONT:
        Font hightFont = new Font("Verdana", Font.BOLD, 14);
        Font midleFont = new Font("Verdana", 1, 12);
        Font lowFont = new Font("Verdana", 0, 10);

        // Affect Font :
        userName_Lb.setFont(hightFont);
        userSurname_Lb.setFont(hightFont);
        //
        userJob_Lb.setFont(midleFont);
        //
        userSpeciality_Lb.setFont(lowFont);
        userEmail_Lb.setFont(lowFont);
        userPhoneNumber_Lb.setFont(lowFont);
        userCompany_Lb.setFont(lowFont);

        // Avatar
        ImageAvatar avatarUser = new ImageAvatar();
        avatarUser.setForeground(foreGroundAvatar);
        avatarUser.setBorderSize(2);
        avatarUser.setIcon(new javax.swing.ImageIcon(getClass().getResource(pathIcon)));

        // Layout and add
        GroupLayout Layout = new GroupLayout(this);
        this.setBackground(backGroundColor);
        this.setLayout(Layout);

        Layout.setHorizontalGroup(Layout.createSequentialGroup()
                .addGap(10)
                .addComponent(avatarUser, 80, 80, 80)
                .addGap(10)
                .addGroup(Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(Layout.createSequentialGroup()
                                .addComponent(userName_Lb)
                                .addGap(5)
                                .addComponent(userSurname_Lb))
                        .addGroup(Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userJob_Lb)
                                .addGap(5)
                                .addComponent(userSpeciality_Lb))
                        .addGroup(Layout.createSequentialGroup()
                                .addGroup(Layout.createParallelGroup(
                                        GroupLayout.Alignment.LEADING)
                                        .addComponent(email_Lb)
                                        .addComponent(phoneNumber_Lb)
                                        .addComponent(company_Lb))
                                .addGap(10)
                                .addGroup(Layout.createParallelGroup(
                                        GroupLayout.Alignment.LEADING)
                                        .addComponent(userEmail_Lb)
                                        .addComponent(userPhoneNumber_Lb)
                                        .addGap(10)
                                        .addComponent(userCompany_Lb))))
                .addGap(10));

        Layout.setVerticalGroup(Layout.createSequentialGroup()
                .addGap(10)
                .addGroup(Layout.createParallelGroup(
                        GroupLayout.Alignment.BASELINE)
                        .addGroup(Layout.createSequentialGroup()
                                .addGap(20)
                                .addComponent(avatarUser, 80, 80, 80))
                        .addGroup(Layout.createSequentialGroup()
                                .addGroup(Layout.createParallelGroup(
                                        GroupLayout.Alignment.LEADING)
                                        .addComponent(userName_Lb)
                                        .addComponent(userSurname_Lb))
                                .addGap(5)
                                .addGroup(Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(Layout.createSequentialGroup()
                                                .addComponent(userJob_Lb)
                                                .addGap(5)
                                                .addComponent(userSpeciality_Lb)))
                                .addGap(10)
                                .addGroup(Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(email_Lb)
                                        .addComponent(userEmail_Lb))
                                .addGap(5)
                                .addGroup(Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(phoneNumber_Lb)
                                        .addComponent(userPhoneNumber_Lb))
                                .addGap(5)
                                .addGroup(Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(company_Lb)
                                        .addComponent(userCompany_Lb))))
                .addGap(10));

    }
}

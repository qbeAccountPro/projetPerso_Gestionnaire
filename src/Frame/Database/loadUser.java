package Frame.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Frame.Constructor.*;

public class loadUser {
    private final static String TABLE = "user";


    public static List<User> getUserList() {
        List<User> userList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE);
            while (resultSet.next()) {

                userList.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getString(9),null));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return userList;
    }

    public static List<String> getJobEnum() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT COLUMN_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '" + TABLE
                            + "' AND COLUMN_NAME = 'job';");
            resultSet.next();
            String enumJob = resultSet.getString("COLUMN_TYPE");
            ;
            String[] enumJobTab = enumJob.substring(6, enumJob.length() - 2).split("','");
            List<String> jobList = new ArrayList<>();
            for (String c : enumJobTab) {
               
                jobList.add(c);
            }
            connection.close();
            return jobList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static void addUser(User user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");

            String name = "'" + user.getName() + "'";
            String surname = "'" + user.getSurname() + "'";
            String company = "'" + user.getCompany() + "'";
            String job = "'" + user.getJob() + "'";
            String speciality = "'" + user.getSpeciality() + "'";
            String email = "'" + user.getEmail() + "'";
            String phoneNumber = "'" + user.getNumberphone() + "'";
            String pathIcon = "'" + user.getPathIcon() + "'";

            String SQL2 = "INSERT INTO " + TABLE
                    + "(name, surname, company, job, speciality, email, numberPhone, pathIcon) VALUES (" + name + ", "
                    + surname + ", " + company + ", " + job + ", " + speciality + ", " + email + ", " + phoneNumber
                    + ", " + pathIcon + " );";


            PreparedStatement statement = connection.prepareStatement(SQL2);
            statement.executeUpdate();

            connection.close();
            laodTask.alterEnumTaskAssignment();
            loadPlanning.addUserEnumPlanning();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static List<String> getColumsName() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            String sql = "DESCRIBE " + TABLE;
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<String> columnName_List = new ArrayList<String>();
            while (rs.next()) {
                String columnName = rs.getString("Field");
                columnName_List.add(columnName);
            }
            rs.close();
            stmt.close();
            conn.close();
            conn.close();
            return columnName_List;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void deleteUser(Object name, Object surname) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            String SQL2 = "DELETE FROM " + TABLE + " WHERE name = '" + name + "' AND surname = '" + surname
                    + "'";
      
            PreparedStatement statement = connection.prepareStatement(SQL2);
            statement.executeUpdate();
            connection.close();
            laodTask.alterEnumTaskAssignment(name,surname);
            loadPlanning.deleteUserEnumPlanning(name, surname);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void modifyUser(User oldUser, User newUser) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");

            String oldName = oldUser.getName();
            String oldsurname = oldUser.getSurname();
            String oldCompany = oldUser.getCompany();
            String oldJob = oldUser.getJob();
            String oldSpeciality = oldUser.getSpeciality();
            String oldEmail = oldUser.getEmail();
            String oldPhoneNumber = oldUser.getNumberphone();
            String oldPathIcon = oldUser.getPathIcon();

            String newName = newUser.getName();
            String newsurname = newUser.getSurname();
            String newCompany = newUser.getCompany();
            String newJob = newUser.getJob();
            String newSpeciality = newUser.getSpeciality();
            String newEmail = newUser.getEmail();
            String newPhoneNumber = newUser.getNumberphone();
            String newPathIcon = newUser.getPathIcon();

            String SQL2 = "UPDATE " + TABLE + " SET name = '" + newName + "', surname = '" + newsurname
                    + "', company = '" + newCompany + "', job = '" + newJob + "', speciality = '" + newSpeciality
                    + "', email = '" + newEmail + "', numberPhone = '" + newPhoneNumber + "', pathIcon = '"
                    + newPathIcon + "' WHERE name = '" + oldName + "' AND surname = '" + oldsurname
                    + "' AND company = '" + oldCompany + "' AND job = '" + oldJob + "' AND speciality = '"
                    + oldSpeciality + "' AND email = '" + oldEmail + "' AND numberPhone = '" + oldPhoneNumber
                    + "' AND pathIcon = '" + oldPathIcon + "';";

           
            PreparedStatement statement = connection.prepareStatement(SQL2);
            statement.executeUpdate();

            connection.close();
            if(!oldName.equals(newName) && !oldsurname.equals(newsurname)){
                laodTask.alterEnumTaskAssignment(oldName,oldsurname,newName,newsurname);
                loadPlanning.modifyUserEnumPlanning(oldName,oldsurname,newName,newsurname);
            }
           } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void AddWorkGroup(String Workgroup ) {
       
        String test = new String();
        List<String> jobList = loadUser.getJobEnum();
        for (String string : jobList) {
            test = test + "'" + string + "', ";
        }
        test = test + "'" + Workgroup + "'";
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");

            String SQL2 = " ALTER TABLE " + TABLE + " MODIFY COLUMN job ENUM(" + test + ") DEFAULT 'developer';";
            

            PreparedStatement statement = connection.prepareStatement(SQL2);
            statement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteWorkgroup(String Workgroup) {
        List<String> jobList = getJobEnum();
        String test = new String();
        jobList.remove(Workgroup);
        int i = 1;
        jobList.size();
        for (String string : jobList) {
            if (i < jobList.size()) {
                test = test + "'" + string + "', ";
                i++;
            } else {
                test = test + "'" + string + "'";
            }
        }
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");

            String SQL2 = " ALTER TABLE " + TABLE + " MODIFY COLUMN job ENUM(" + test + ") DEFAULT 'developer';";
            

            PreparedStatement statement = connection.prepareStatement(SQL2);
            statement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void modifyWorkgroup(String oldWorkgroup, String newWorkgroup) {
        
        AddWorkGroup(newWorkgroup);
        modify_OnColumn_OldValue_By_NewValue(oldWorkgroup, newWorkgroup, "job");
        deleteWorkgroup(oldWorkgroup);

    }

    private static void modify_OnColumn_OldValue_By_NewValue(String oldWorkgroup, String newWorkgroup, String column) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");

            String SQL2 = " UPDATE " + TABLE + " SET " + column + " = '" + newWorkgroup + "' WHERE " + column
                    + " = '" + oldWorkgroup + "';";

            PreparedStatement statement = connection.prepareStatement(SQL2);
            statement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static User connection(String Name, String Password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE);

            while (resultSet.next()) {

                if(resultSet.getString(2).equals(Name) && resultSet.getString(10).equals(Password)){
                    User currentUser = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                    resultSet.getString(7), resultSet.getString(8), resultSet.getString(9),null);
                   return currentUser;
                }  
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return null;
    }


}

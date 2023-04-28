package Frame.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Frame.Constructor.*;

public class loadPlanning {
    private final static String TABLE = "planning";

    public static List<TimeSlot> getPlanningList() {
        List<TimeSlot> planningList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE);
            while (resultSet.next()) {

                planningList.add(new TimeSlot(resultSet.getString(2), resultSet.getString(4),
                        resultSet.getString(3), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7)));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return planningList;
    }

    public static void addPlanning(TimeSlot draft) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");

            String description = "'" + draft.getDescription() + "'";
            String user = "'" + draft.getUser() + "'";
            String location = "'" + draft.getLocalisation() + "'";
            String date = "'" + draft.getDate() + "'";
            String hourStart = "'" + draft.getHourStart() + "'";
            String hourEnd = "'" + draft.getHourEnd() + "'";

            String SQL2 = "INSERT INTO " + TABLE
                    + "(description, user, location, date, hourStart, hourEnd) VALUES ("
                    + description + ", "
                    + user + ", " + location + ", " + date + ", " + hourStart + ", " + hourEnd + " );";
            //
            System.out.println(SQL2);
            PreparedStatement statement = connection.prepareStatement(SQL2);
            statement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deletePlanning(TimeSlot drafting) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            String description = drafting.getDescription();
            String date = drafting.getDate();
            String hourStart = drafting.getHourStart();
            String hourEnd = drafting.getHourEnd();

            String SQL2 = "DELETE FROM " + TABLE + " WHERE description = '" + description + "' AND date = '" + date
                    + "' AND hourStart = '" + hourStart + "' AND hourEnd = '" + hourEnd+ "'";

            PreparedStatement statement = connection.prepareStatement(SQL2);
            statement.executeUpdate();
            connection.close();
           
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void modifyPlanning() {
    }

    public static void addUserEnumPlanning() {
        List<String> nameSurnameList = new ArrayList<String>();
        List<User> userList = loadUser.getUserList();
        for (User user : userList) {
            nameSurnameList.add(user.getName() + " " + user.getSurname());
        }
        int i = 1;
        String newEnumAssignmentBy = "ALTER TABLE planning MODIFY COLUMN user ENUM(";
        for (String nameSurname : nameSurnameList) {
            if (nameSurnameList.size() > i) {
                newEnumAssignmentBy = newEnumAssignmentBy + "'" + nameSurname + "',";
            } else {
                newEnumAssignmentBy = newEnumAssignmentBy + "'" + nameSurname + "'";
            }
            i++;
        }
        newEnumAssignmentBy = newEnumAssignmentBy + ");";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            PreparedStatement statementAssignmentBy = connection.prepareStatement(newEnumAssignmentBy);
            statementAssignmentBy.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteUserEnumPlanning(Object name, Object surname) {
        List<String> nameSurnameList = new ArrayList<String>();
        List<User> userList = loadUser.getUserList();
        for (User user : userList) {
            nameSurnameList.add(user.getName() + " " + user.getSurname());
        }

        String OldEnumAssignmentBy = "ALTER TABLE planning MODIFY COLUMN assignmentBy ENUM(";
        int i = 1;
        for (String nameSurname : nameSurnameList) {
            if (nameSurnameList.size() > i) {
                OldEnumAssignmentBy = OldEnumAssignmentBy + "'" + nameSurname + "',";
            } else {
                OldEnumAssignmentBy = OldEnumAssignmentBy + "'" + nameSurname + "'";
            }
            i++;
        }
        OldEnumAssignmentBy = OldEnumAssignmentBy + ");";
        String SQL2 = "DELETE FROM planning WHERE user = '" + name + " " + surname + "';";

        try {// SQL2
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            PreparedStatement statement2 = connection.prepareStatement(SQL2);
            statement2.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");

            PreparedStatement oldStatementBy = connection.prepareStatement(OldEnumAssignmentBy);
            oldStatementBy.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void modifyUserEnumPlanning(String oldName, String oldsurname, String newName, String newsurname) {
        List<String> nameSurnameList = new ArrayList<String>();
        List<User> userList = loadUser.getUserList();
        for (User user : userList) {
            nameSurnameList.add(user.getName() + " " + user.getSurname());
        }

        String OldEnumPlanning = "ALTER TABLE planning MODIFY COLUMN user ENUM(";
        for (String nameSurname : nameSurnameList) {
            OldEnumPlanning = OldEnumPlanning + "'" + nameSurname + "',";
        }
        OldEnumPlanning = OldEnumPlanning + "'" + oldName + " " + oldsurname + "'";
        OldEnumPlanning = OldEnumPlanning + ");";

        String SQL2 = "UPDATE planning SET user = '" + newName + " " + newsurname
                + "' WHERE user = '" + oldName + " " + oldsurname + "';";

        String newEmunPlanning = OldEnumPlanning.replace(",'" + oldName + " " + oldsurname + "'", "");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            PreparedStatement oldStatementBy = connection.prepareStatement(OldEnumPlanning);
            oldStatementBy.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            PreparedStatement statement2 = connection.prepareStatement(SQL2);
            statement2.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");

            PreparedStatement newStatementBy = connection.prepareStatement(newEmunPlanning);
            newStatementBy.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

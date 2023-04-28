package Frame.Database;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Frame.Constructor.*;

public class laodTask {
    private final static String TABLE = "task";

    public static List<Task> getTaskList() {
        List<Task> taskList = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE);
            while (resultSet.next()) {

                taskList.add(new Task(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getBoolean(9),
                        resultSet.getString(10), resultSet.getString(11)));
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return taskList;
    }

    public static void addTask(Task task) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");

            String description = "'" + task.getDescription() + "'";
            String deadline = "'" + task.getDeadline() + "'";
            String duration = "'" + task.getDuration() + "'";
            String priority = "'" + task.getPriority() + "'";
            String thematic = "'" + task.getThematic() + "'";
            String assignementBy = "'" + task.getAssignementBy() + "'";
            String assignementFor = "'" + task.getAssignementFor() + "'";
            Boolean archive = task.getArchive();
            String dateArchive = "'" + task.getDateArchive() + "'";
            String archiveBy = "'" + task.getArchiveBy() + "'";

            String SQL = "INSERT INTO " + TABLE
                    + "(description, deadline, duration, priority, thematic, assignmentBy, assignmentFor, archive,dateArchive,archiveBy) VALUES ("
                    + description + ", "
                    + deadline + ", " + duration + ", " + priority + ", " + thematic + ", " + assignementBy + ", "
                    + assignementFor
                    + ", " + archive + ", " + dateArchive + ", " + archiveBy + " );";

            System.out.println(SQL);
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.executeUpdate();
            connection.close();
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

    public static void deleteTask(Object description) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            String SQL2 = "DELETE FROM " + TABLE + " WHERE description = '" + description + "'";

            PreparedStatement statement = connection.prepareStatement(SQL2);
            statement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void unarchiveTask(Task oldTask, Task newTask) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");

            String oldDescription = oldTask.getDescription();
            String oldDeadline = oldTask.getDeadline();
            String oldDuration = oldTask.getDuration();
            String oldPriority = oldTask.getPriority();
            String oldThematic = oldTask.getThematic();
            String oldAssignementBy = oldTask.getAssignementBy();
            String oldAssignementFor = oldTask.getAssignementFor();
            Boolean oldArchive = oldTask.getArchive();
            String oldDateArchive = oldTask.getDateArchive();
            String oldArchiveBy = oldTask.getArchiveBy();

            String newDescription = newTask.getDescription();
            String newDeadline = newTask.getDeadline();
            String newDuration = newTask.getDuration();
            String newPriority = newTask.getPriority();
            String newThematic = newTask.getThematic();
            String newAssignementBy = newTask.getAssignementBy();
            String newAssignementFor = newTask.getAssignementFor();
            Boolean newArchive = newTask.getArchive();
            String newDateArchive = newTask.getDateArchive();
            String newArchiveBy = newTask.getArchiveBy();

            String SQL2 = "UPDATE " + TABLE + " SET description = '" + newDescription + "', deadline = '" + newDeadline
                    + "', duration = '" + newDuration + "', priority = '" + newPriority + "', thematic = '"
                    + newThematic
                    + "', assignmentBy = '" + newAssignementBy + "', assignmentFor = '" + newAssignementFor
                    + "', archive = "
                    + newArchive + ", dateArchive = '" + newDateArchive + "', archiveBy = '" + newArchiveBy
                    + "' WHERE description = '" + oldDescription + "' AND deadline = '" + oldDeadline
                    + "' AND duration = '" + oldDuration + "' AND priority = '" + oldPriority + "' AND thematic = '"
                    + oldThematic + "' AND assignmentBy = '" + oldAssignementBy + "' AND assignmentFor = '"
                    + oldAssignementFor
                    + "' AND archive = " + oldArchive + " AND dateArchive = '" + oldDateArchive + "' AND archiveBy = '"
                    + oldArchiveBy + "';";

            System.out.println(SQL2);
            PreparedStatement statement = connection.prepareStatement(SQL2);
            statement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void archiveTask(Object description) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            String SQL2 = "UPDATE  " + TABLE + " SET archive = " + true + ", dateArchive = '" + formattedDateTime + "' WHERE description = '" + description + "'";

            PreparedStatement statement = connection.prepareStatement(SQL2);
            statement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void unarchiveTask(Task task) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");

            String Description = task.getDescription();
            String Deadline = task.getDeadline();
            String Duration = task.getDuration();
            String Priority = task.getPriority();
            String Thematic = task.getThematic();
            String AssignementBy = task.getAssignementBy();
            String AssignementFor = task.getAssignementFor();
            Boolean Archive = false;
            String DateArchive = null;
            String ArchiveBy = "NOT archived";

            String SQL2 = "UPDATE " + TABLE + " SET archive = "
                    + Archive + ", dateArchive = '" + DateArchive + "', archiveBy = '" + ArchiveBy
                    + "' WHERE description = '" + Description + "' AND deadline = '" + Deadline
                    + "' AND duration = '" + Duration + "' AND priority = '" + Priority + "' AND thematic = '"
                    + Thematic + "' AND assignmentBy = '" + AssignementBy + "' AND assignmentFor = '"
                    + AssignementFor+ "';";

            System.out.println(SQL2);
            PreparedStatement statement = connection.prepareStatement(SQL2);
            statement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        
    }

    public static void alterEnumTaskAssignment() {
        List<String> nameSurnameList = new ArrayList<String>();
        List<User> userList = loadUser.getUserList();
        for (User user : userList) {
            nameSurnameList.add(user.getName() + " " + user.getSurname());
        }
        int i = 1;
        String assignmentByColumn = "assignmentBy";
        String assignmentForColumn = "assignmentFor";
        String archivedByColumn = "archiveBy";
        String notAssignment = "NOT assignment";
        String notArchived = "NOT archived";

        String newEnumAssignmentBy = "ALTER TABLE " + TABLE + " MODIFY COLUMN assignmentBy ENUM('NOT assignment',";
        for (String nameSurname : nameSurnameList) {
            if (nameSurnameList.size() > i) {
                newEnumAssignmentBy = newEnumAssignmentBy + "'" + nameSurname + "',";
            } else {
                newEnumAssignmentBy = newEnumAssignmentBy + "'" + nameSurname + "'";
            }
            i++;
        }
        newEnumAssignmentBy = newEnumAssignmentBy + ") DEFAULT 'NOT assignment';";
        String newEmunAssignmentFor = newEnumAssignmentBy.replace(assignmentByColumn, assignmentForColumn);
        String newEmunArchivedBy = newEnumAssignmentBy.replace(assignmentByColumn, archivedByColumn);
        newEmunArchivedBy = newEmunArchivedBy.replace(notAssignment, notArchived);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            PreparedStatement statementAssignmentBy = connection.prepareStatement(newEnumAssignmentBy);
            PreparedStatement statementAssignmentFor = connection.prepareStatement(newEmunAssignmentFor);
            PreparedStatement statementarchivedBy = connection.prepareStatement(newEmunArchivedBy);
            System.out.println(newEmunArchivedBy);
            statementAssignmentBy.executeUpdate();
            statementAssignmentFor.executeUpdate();
            statementarchivedBy.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void alterEnumTaskAssignment(String oldName, String oldsurname, String newName, String newsurname) {
        List<String> nameSurnameList = new ArrayList<String>();
        List<User> userList = loadUser.getUserList();
        for (User user : userList) {
            nameSurnameList.add(user.getName() + " " + user.getSurname());
        }
        String assignmentByColumn = "assignmentBy";
        String assignmentForColumn = "assignmentFor";
    

        String OldEnumAssignmentBy = "ALTER TABLE " + TABLE + " MODIFY COLUMN assignmentBy ENUM('NOT assignment',";
        for (String nameSurname : nameSurnameList) {
            OldEnumAssignmentBy = OldEnumAssignmentBy + "'" + nameSurname + "',";
        }
        OldEnumAssignmentBy = OldEnumAssignmentBy + "'" + oldName + " " + oldsurname + "'";
        OldEnumAssignmentBy = OldEnumAssignmentBy + ") DEFAULT 'NOT assignment';";
        String OldEmunAssignmentFor = OldEnumAssignmentBy.replace(assignmentByColumn, assignmentForColumn);

        String SQL2 = "UPDATE " + TABLE + " SET assignmentBy = '" + newName + " " + newsurname
                + "' WHERE assignmentBy = '" + oldName + " " + oldsurname + "';";
        String SQL3 = "UPDATE " + TABLE + " SET assignmentFor = '" + newName + " " + newsurname
                + "' WHERE assignmentFor = '" + oldName + " " + oldsurname + "';";

        String newEmunAssignmentBy = OldEnumAssignmentBy.replace(",'" + oldName + " " + oldsurname + "'", "");
        String newEmunAssignmentFor = OldEmunAssignmentFor.replace(",'" + oldName + " " + oldsurname + "'", "");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");

            PreparedStatement oldStatementBy = connection.prepareStatement(OldEnumAssignmentBy);
            oldStatementBy.executeUpdate();

            PreparedStatement oldStatementFor = connection.prepareStatement(OldEmunAssignmentFor);
            oldStatementFor.executeUpdate();

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

            PreparedStatement statement3 = connection.prepareStatement(SQL3);
            statement3.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");

            PreparedStatement newStatementBy = connection.prepareStatement(newEmunAssignmentBy);
            newStatementBy.executeUpdate();

            PreparedStatement newStatementFor = connection.prepareStatement(newEmunAssignmentFor);
            newStatementFor.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void alterEnumTaskAssignment(Object name, Object surname) {
        List<String> nameSurnameList = new ArrayList<String>();
        List<User> userList = loadUser.getUserList();
        for (User user : userList) {
            nameSurnameList.add(user.getName() + " " + user.getSurname());
        }
        String assignmentByColumn = "assignmentBy";
        String assignmentForColumn = "assignmentFor";
        String archivedByColumn = "archiveBy";
        String notAssignment = "NOT assignment";
        String notArchived = "NOT archived";

        String OldEnumAssignmentBy = "ALTER TABLE " + TABLE + " MODIFY COLUMN assignmentBy ENUM('NOT assignment',";
        int i = 1;
        for (String nameSurname : nameSurnameList) {
            if (nameSurnameList.size() > i) {
                OldEnumAssignmentBy = OldEnumAssignmentBy + "'" + nameSurname + "',";
            } else {
                OldEnumAssignmentBy = OldEnumAssignmentBy + "'" + nameSurname + "'";
            }
            i++;
        }
        OldEnumAssignmentBy = OldEnumAssignmentBy + ") DEFAULT 'NOT assignment';";
        String OldEmunAssignmentFor = OldEnumAssignmentBy.replace(assignmentByColumn, assignmentForColumn);
        String OldEmunArchivedBy = OldEnumAssignmentBy.replace(assignmentByColumn, archivedByColumn);
        OldEmunArchivedBy = OldEmunArchivedBy.replace(notAssignment, notArchived);

        String SQL2 = "UPDATE " + TABLE + " SET " + assignmentByColumn + " = '" + notAssignment + "' WHERE "
                + assignmentByColumn + " = '" + name + " " + surname + "';";
        String SQL3 = "UPDATE " + TABLE + " SET " + assignmentForColumn + " = '" + notAssignment + "' WHERE "
                + assignmentForColumn + " = '" + name + " " + surname + "';";
        String SQL4 = "UPDATE " + TABLE + " SET " + archivedByColumn + " = '" + notArchived + "' WHERE "
                + archivedByColumn + " = '" + name + " " + surname + "';";

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

        try {// SQL3
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            PreparedStatement statement3 = connection.prepareStatement(SQL3);
            statement3.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {// SQL4
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tuto", "root", "root");
            PreparedStatement statement3 = connection.prepareStatement(SQL4);
            statement3.executeUpdate();
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

            PreparedStatement oldStatementFor = connection.prepareStatement(OldEmunAssignmentFor);
            oldStatementFor.executeUpdate();

            PreparedStatement oldStatementArchived = connection.prepareStatement(OldEmunArchivedBy);
            oldStatementArchived.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static List<TimeSlot> getPlanningList() {
        return null;
    }

    

}

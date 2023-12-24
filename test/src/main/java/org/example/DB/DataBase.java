package org.example.DB;


import org.example.CountGoodStudentDZ;
import org.example.Student;
import org.example.Tasks;

import java.sql.*;
import java.util.List;

public class DataBase {
    private static Connection conn = null;
    private static final String Url = "jdbc:sqlite:D:/db/Analiz.db";

    public static void connect(){
        try{
            conn = DriverManager.getConnection(Url);
            System.out.println("+");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null){
                    conn.close();
                }
            } catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void CreateTableStudent(){
        String sql = "CREATE TABLE IF NOT EXISTS student (\n"
                + "id_stud integer PRIMARY KEY, \n"
                + "name text NOT NULL, \n"
                + "Team text NOT NULL, \n"
                + "City text NULL, \n"
                + "id_vk integer NOT NULL \n"
                + ");";

        try (Connection conn = DriverManager.getConnection(Url);  Statement stat = conn.createStatement()){
            stat.execute(sql);
            System.out.println("+");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveStudent(List<Student> students){
        String sql = "INSERT INTO student(id_stud, name, Team, City, id_vk) VALUES(?, ?, ?, ?, ?)";

        for (var student : students){
            try(Connection conn = DriverManager.getConnection(Url);
                PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, student.getId_stud());
                ps.setString(2, student.human().getName());
                ps.setString(3, student.getTeam());
                ps.setString(4,student.getPlace());
                ps.setInt(5, student.getId_vk());
                ps.executeUpdate();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void CreateTableBallTasks(){
        String sql = "CREATE TABLE IF NOT EXISTS ballStud (\n"
                + "id_stud integer NOT NULL, \n"
                + "ballStud integer NOT NULL, \n"
                + "id_zd integer NOT NULL \n"
                + ");";

        try (Connection conn = DriverManager.getConnection(Url);  Statement stat = conn.createStatement()){
            stat.execute(sql);
            System.out.println("+");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveBallStud(List<Student> ballStud){
        String sql = "INSERT INTO ballStud(id_stud, ballStud, id_zd) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(Url);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (Student ball : ballStud) {
                List<Integer> idTasks = ball.getIdTasks();
                List<Integer> ballList = ball.tasks().getBallStudent();

                for (int i = 0; i < ballList.size(); i++) {
                    ps.setInt(1, ball.getId_stud());
                    ps.setInt(2, ballList.get(i));
                    ps.setInt(3, idTasks.get(i));

                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public static void CreateTableMaxBall(){
        String sql = "CREATE TABLE IF NOT EXISTS maxBall (\n"
                + "id_zd integer PRIMARY KEY, \n"
                + "taskName text NOT NULL, \n"
                + "ballZd integer NOT NULL \n"
                + ");";

        try (Connection conn = DriverManager.getConnection(Url);  Statement stat = conn.createStatement()){
            stat.execute(sql);
            System.out.println("+");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveMaxBall(List<Tasks> tasks){
        String sql = "INSERT INTO maxBall(id_zd, taskName, ballZd) VALUES(?, ?, ?)";

        for (var task : tasks){
            try(Connection conn = DriverManager.getConnection(Url);
                PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, task.getId_Tasks());
                ps.setString(2, task.getTasksName());
                ps.setInt(3, task.getMaxBall());
                ps.executeUpdate();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void CreateCountStud(){
        String sql = "CREATE TABLE IF NOT EXISTS countStud (\n"
                + "taskName text PRIMARY KEY, \n"
                + "count integer NOT NULL \n"
                + ");";

        try (Connection conn = DriverManager.getConnection(Url);  Statement stat = conn.createStatement()){
            stat.execute(sql);
            System.out.println("+");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveCountStud(List<CountGoodStudentDZ> count){
        String sql = "INSERT INTO countStud(taskName, count) VALUES(?, ?)";

        for (var task : count){
            try(Connection conn = DriverManager.getConnection(Url);
                PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, task.getName());
                ps.setInt(2, task.getCountStudent());
                ps.executeUpdate();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
}

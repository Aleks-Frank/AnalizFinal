package org.example;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CSVConverter {
    private static final String Url = "jdbc:sqlite:D:/db/Analiz.db";

    public static void main(String[] args) {
        convertTableToCSV();
        convertTableToCSVBallStud();
        convertTableToCSVMaxBall();
        convertTableToCSVCountStud();
    }

    public static void convertTableToCSV() {
        String sql = "SELECT * FROM student";

        try (Connection conn = DriverManager.getConnection(Url);
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery(sql);
             CSVWriter writer = new CSVWriter(new FileWriter("student.csv"))) {

            writer.writeNext(new String[]{"id_stud", "name", "Team", "City", "id_vk"});

            while (rs.next()) {
                String id_stud = rs.getString("id_stud");
                String name = rs.getString("name");
                String team = rs.getString("Team");
                String city = rs.getString("City");
                String id_vk = rs.getString("id_vk");

                writer.writeNext(new String[]{id_stud, name, team, city, id_vk});
            }

            System.out.println("Таблица успешно экспортирована в CSV-файл.");

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void convertTableToCSVBallStud() {
        String sql = "SELECT * FROM ballStud";

        try (Connection conn = DriverManager.getConnection(Url);
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery(sql);
             CSVWriter writer = new CSVWriter(new FileWriter("ballStud.csv"))) {

            writer.writeNext(new String[]{"id_stud", "ballStud", "id_zd"});

            while (rs.next()) {
                String id_stud = rs.getString("id_stud");
                String ballStud = rs.getString("ballStud");
                String id_zd = rs.getString("id_zd");

                writer.writeNext(new String[]{id_stud, ballStud, id_zd});
            }

            System.out.println("Таблица успешно экспортирована в CSV-файл.");

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void convertTableToCSVMaxBall() {
        String sql = "SELECT * FROM maxBall";

        try (Connection conn = DriverManager.getConnection(Url);
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery(sql);
             CSVWriter writer = new CSVWriter(new FileWriter("maxBall.csv"))) {

            writer.writeNext(new String[]{"id_zd", "taskName", "ballZd"});

            while (rs.next()) {
                String id_zd = rs.getString("id_zd");
                String taskName = rs.getString("taskName");
                String ballZd = rs.getString("ballZd");

                writer.writeNext(new String[]{id_zd, taskName, ballZd});
            }

            System.out.println("Таблица успешно экспортирована в CSV-файл.");

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void convertTableToCSVCountStud() {
        String sql = "SELECT * FROM countStud";

        try (Connection conn = DriverManager.getConnection(Url);
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery(sql);
             CSVWriter writer = new CSVWriter(new FileWriter("countStud.csv"))) {

            writer.writeNext(new String[]{"taskName", "count"});

            while (rs.next()) {
                String taskName = rs.getString("taskName");
                String count = rs.getString("count");

                writer.writeNext(new String[]{taskName, count});
            }
            System.out.println("Таблица успешно экспортирована в CSV-файл.");

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


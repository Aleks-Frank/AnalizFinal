package org.example;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartExample extends JFrame {
    private static final String Url = "jdbc:sqlite:D:/db/Analiz.db";

    public PieChartExample() {
        DefaultPieDataset dataset = loadDataFromDatabase();

        JFreeChart chart = ChartFactory.createPieChart(
                "Города по количеству студентов",
                dataset,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 400));

        add(chartPanel);

        setTitle("Города по количеству студентов");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private DefaultPieDataset loadDataFromDatabase() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        try (Connection conn = DriverManager.getConnection(Url);
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery("SELECT City, COUNT(*) AS Count FROM student GROUP BY City")) {

            while (rs.next()) {
                String city = rs.getString("City");
                int count = rs.getInt("Count");
                if (city != null) { // Check for null key
                    dataset.setValue(city, count);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }
}

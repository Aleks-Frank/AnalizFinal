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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class HistogramExample extends JFrame {
    private static final String Url = "jdbc:sqlite:D:/db/Analiz.db";
    public HistogramExample() {
        // данные из базы данных
        DefaultCategoryDataset dataset = loadDataFromDatabase();

        // горизонтальная гистограмма
        JFreeChart chart = ChartFactory.createBarChart(
                "Количество студентов, справившихся с заданием", // Заголовок графика
                "Задание", // Метка оси X
                "Количество студентов", // Метка оси Y
                dataset, // Данные для графика
                PlotOrientation.HORIZONTAL, // Ориентация графика
                true, // Отображение легенды
                true, // Включение подсказок
                false // Включение URL-ссылок
        );

        // панель для отображения графика
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 400));

        // связыываем панель с графиком на форму
        add(chartPanel);

        // Настройки окна
        setTitle("Горизонтальная гистограмма");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private DefaultCategoryDataset loadDataFromDatabase() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try (Connection conn = DriverManager.getConnection(Url);
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery("SELECT taskName, count FROM countStud")) {

            while (rs.next()) {
                String taskName = rs.getString("taskName");
                int count = rs.getInt("count");
                dataset.addValue(count, "Студенты", taskName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataset;
    }
}

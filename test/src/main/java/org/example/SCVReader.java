package org.example;

import org.example.DB.DataBase;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SCVReader {
    public static void main(String[] arg){
        String filePath = new File("").getAbsolutePath() + File.separator + "/src/file/file.csv";
        List<Integer> id_Zd = new ArrayList<>();
        //VkApiRepor vkTest = new VkApiRepor();

        try (CSVParser parser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withDelimiter(';'))) {
            List<StudentsPrivateTasks> balls = new ArrayList<>();
            List<Human> names = new ArrayList<>();
            List<Student> students = new ArrayList<>();
            List<Tasks> tasks = new ArrayList<>();
            List<String> nameString = new ArrayList<>();

            int lineCount = 0;
            for (CSVRecord record : parser) {
                if (lineCount == 0) {
                    CSVRecord taskNamesRecord = parser.iterator().next();
                    CSVRecord scoresRecord = parser.iterator().next();

                    for (int i = 2; i < taskNamesRecord.size(); i++) {
                        String taskName = taskNamesRecord.get(i);
                        int maxBall = Integer.parseInt(scoresRecord.get(i));

                        Tasks task = new Tasks(taskName, maxBall);
                        task.setId_Tasks(i - 2);
                        tasks.add(task);
                        id_Zd.add(i - 2);
                    }
                    lineCount ++;
                } else {
                    lineCount ++;
                    String name = record.get(0);
                    nameString.add(name);
                    String group = record.get(1);
                    List<Integer> scores = new ArrayList<>();

                    for (int i = 2; i < record.size(); i++) {
                        String scoreString = record.get(i);
                        int score = Integer.parseInt(scoreString);
                        scores.add(score);
                    }

                    Human nameStud = new Human(name);
                    names.add(nameStud);
                    StudentsPrivateTasks ballTasks = new StudentsPrivateTasks(scores, new ArrayList<>());
                    ballTasks.setIdTasks(id_Zd);
                    balls.add(ballTasks);


                    Student student = new Student(nameStud, ballTasks, group);
                    student.setId_stud(lineCount - 2);
                    //int IDStud = vkTest.getUserIdByName(name);
                    //student.setId_vk(IDStud);
                    //Thread.sleep(1000);
                    //String Place = vkTest.getUserCity(IDStud);
                    //student.setPlace(Place);
                    students.add(student);
                }
            }

            for (Student studentI : students) {
                System.out.println("Name: " + studentI.human().getName());
                System.out.println("Scores: " + studentI.tasks().getBallStudent());
                System.out.println("Place: " + studentI.getPlace());
                System.out.println("---------------------");
            }
            for (Tasks task : tasks) {
                System.out.println("Id: " + task.getId_Tasks() + " Task: " + task.getTasksName() + " Max Score: " + task.getMaxBall());
            }

            List<CountGoodStudentDZ> countGoodStudents = new ArrayList<>();

            for (Tasks task : tasks) {
                if (task.getTasksName().startsWith("ДЗ:")) {
                    int maxBall = task.getMaxBall();
                    int countStud = 0;

                    for (Student student : students) {
                        StudentsPrivateTasks studentTasks = student.tasks();
                        List<Integer> ballStudent = studentTasks.getBallStudent();

                        if (ballStudent.size() > task.getId_Tasks()) {
                            int studentBall = ballStudent.get(task.getId_Tasks());

                            if (studentBall > maxBall / 2) {
                                countStud++;
                            }
                        }
                    }
                    CountGoodStudentDZ countGoodStudentDZ = new CountGoodStudentDZ(task.getTasksName(), countStud);
                    countGoodStudents.add(countGoodStudentDZ);
                }
            }

            for (CountGoodStudentDZ countGoodStudentDZ : countGoodStudents) {
                System.out.println("Задание: " + countGoodStudentDZ.getName() + ", Количество студентов: " + countGoodStudentDZ.getCountStudent());
            }

            DataBase.connect();
            //DataBase.CreateTableStudent();
            //DataBase.saveStudent(students);
            //DataBase.CreateTableBallTasks();
            //DataBase.saveBallStud(students);
            //DataBase.CreateTableMaxBall();
            //DataBase.saveMaxBall(tasks);
            //DataBase.CreateCountStud();
            //DataBase.saveCountStud(countGoodStudents);

            HistogramExample example = new HistogramExample();
            example.setVisible(true);
            PieChartExample exampleOne = new PieChartExample();
            exampleOne.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
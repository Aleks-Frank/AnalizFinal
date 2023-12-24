package org.example;

import java.util.List;

public class Student extends Human{
    private String trainingCourse;
    private String team;
    private int id_stud;
    private String solvedTasks;
    private StudentsPrivateTasks tasks;
    private Human human;
    private List<Integer> id_Zd;
    private int id_vk;

    public Student() {
    }

    public Student(Human human, StudentsPrivateTasks tasks, String team) {
        this.human = human;
        this.tasks = tasks;
        this.team = team;
        this.id_stud = tasks.getId_stud();
        this.solvedTasks = tasks.getNameTasks();
        this.setIdTasks(tasks.getId_Zd());
    }
    public int getId_stud() {
        return id_stud;
    }

    public void setId_stud(int id_stud) {
        this.id_stud = id_stud;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    public void setTasks(StudentsPrivateTasks tasks) {
        this.tasks = tasks;
    }

    public void setTrainingCourse(String newTrainingCourse) {
        this.trainingCourse = newTrainingCourse;
    }

    public void setTeam(String newTeam) {
        this.team = newTeam;
    }

    public void replaceTeam(String newTeam) {
        this.team = newTeam;
    }

    public void setSolvedTasks(String newSolvedTasks) {
        this.solvedTasks = newSolvedTasks;
    }

    public String getTrainingCourse() {
        return trainingCourse;
    }

    public String getTeam() {
        return team;
    }

    public String getSolvedTasks() {
        return solvedTasks;
    }

    public Human human() {
        return human;
    }

    public StudentsPrivateTasks tasks() {
        return tasks;
    }

    @Override
    public String toString() {
        return human + " Ball:" + tasks.getBallStudent();
    }

    public List<Integer> getTasks() {
        return id_Zd;
    }

    public void setIdTasks(List<Integer> idTasks) {
        this.id_Zd = idTasks;
    }

    public List<Integer> getIdTasks() {
        return tasks.getIdTasks();
    }

    public int getId_vk() {
        return id_vk;
    }

    public void setId_vk(int id_vk) {
        this.id_vk = id_vk;
    }
}

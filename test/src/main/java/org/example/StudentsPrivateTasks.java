package org.example;

import java.util.List;

public class StudentsPrivateTasks extends Student{
    private String nameTasks;
    private List<Integer> ballStudent;
    private List<Integer> id_Zd;


    public StudentsPrivateTasks() {
    }

    public StudentsPrivateTasks(List<Integer> ballStudent, List<Integer> id_Zd) {
        this.ballStudent = ballStudent;
        this.id_Zd = id_Zd;
    }

    public void setNameTasks(String newNameTasks) {
        this.nameTasks = newNameTasks;
    }

    public void setBallStudent(List<Integer> newBallStudent) {
        this.ballStudent = newBallStudent;
    }

    public String getNameTasks() {
        return nameTasks;
    }

    public List<Integer> getBallStudent() {
        return ballStudent;
    }

    public List<Integer> getId_Zd() {
        return id_Zd;
    }

    public void setIdTasks(List<Integer> idTasks) {
        this.id_Zd = idTasks;
    }

    public List<Integer> getIdTasks() {
        return id_Zd;
    }

}

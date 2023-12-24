package org.example;

public class Tasks{
    private String TaskName;
    private int maxBall;
    private int id_Tasks;

    public Tasks() {
    }

    public Tasks(String TaskName, int maxBall) {
        this.TaskName = TaskName;
        this.maxBall = maxBall;
    }

    public int getMaxBall() {
        return maxBall;
    }

    public String getTasksName() {
        return TaskName;
    }
    public int getId_Tasks() {return id_Tasks;}
    public void setId_Tasks(int id_Tasks){
        this.id_Tasks = id_Tasks;
    }
}
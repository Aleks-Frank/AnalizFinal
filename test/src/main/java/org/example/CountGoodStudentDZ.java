package org.example;

public class CountGoodStudentDZ {
    private String name;
    private int CountStudent;

    public CountGoodStudentDZ(){
    }
    public CountGoodStudentDZ(String name, int CountStudent){
        this.name = name;
        this.CountStudent = CountStudent;
    }
    public int getCountStudent() {
        return CountStudent;
    }

    public void setCountStudent(int countStudent) {
        CountStudent = countStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

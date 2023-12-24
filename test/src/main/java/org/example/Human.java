package org.example;

import java.util.List;

public class Human {
    private String name;
    public List<Human> namesList;
    private String place;
    public Human(){}
    public Human(List<Human> namesList) {
        this.namesList = namesList;
    }
    public void setNames(List<Human> names){this.namesList = namesList;}
    public List<Human> getNames() {
        return namesList;
    }
    public Human(String newName) {
        this.name = newName;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public void setPlace(String place) {this.place = place;}

    public String getPlace() {return place;}

    @Override
    public String toString() {
        return "Name=" + name;
    }
}
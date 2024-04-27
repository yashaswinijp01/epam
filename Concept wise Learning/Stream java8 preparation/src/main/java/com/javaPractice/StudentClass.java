package com.javaPractice;

import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
public class StudentClass implements Comparable<StudentClass>{
    private int id;
    private String name;


    public StudentClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentClass{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentClass that = (StudentClass) o;
        return id == that.id && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }




    @Override
    public int compareTo(StudentClass o) {
        if(this.id ==o.getId()){
            return 0;
        }
        else if(this.id>o.getId()){
            return 1;
        }
        else
            return -1;
    }


    public static void main(String[] args) {

        List s = new ArrayList<>();
        StudentClass s1 = new StudentClass(205, "Ravi");
        StudentClass s2 = new StudentClass(207, "Baskar");
        StudentClass s3 = new StudentClass(202, "Ashok");
        StudentClass s4 = new StudentClass(202, "Ashok");
        StudentClass s5 = new StudentClass(205, "Ashok");
        s.add(s1);
        s.add(s2);
        s.add(s3);
        s.add(s4);
        s.add(s5);

        Collections.sort(s);
        System.out.println(s);
    }

}

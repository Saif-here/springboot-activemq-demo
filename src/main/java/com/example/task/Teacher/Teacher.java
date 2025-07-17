package com.example.task.Teacher;

import com.example.task.Student.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TchrId")
    private int tchrId;

    @Column(name = "TchrName")
    private String tchrName;

    @Column(name = "TchrSubj")
    private String tchrSubj;

    @ManyToMany(mappedBy = "teachers")
    @JsonBackReference
    @JsonIgnore
    private List<Student> students = new ArrayList<>();
}

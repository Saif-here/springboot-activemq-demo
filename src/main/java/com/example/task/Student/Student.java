package com.example.task.Student;

import com.example.task.Teacher.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Std_ID")
    private int stdId;

    @Column(name = "StdName")
    private String stdName;

    @Column(name = "StdAge")
    private int stdAge;

    @Column(name = "StdCourse")
    private String stdCourse;

    @ManyToMany
    @JoinTable(
            name = "student_teacher",
            joinColumns = @JoinColumn(name = "Std_ID"),
            inverseJoinColumns = @JoinColumn(name = "TchrId")
    )
    private List<Teacher> teachers = new ArrayList<>();
}

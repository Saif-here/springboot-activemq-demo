package com.example.task.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentWithTeachersDTO {
    private String stdName;
    private List<String> teacherNames;

    public StudentWithTeachersDTO(String stdName, List<String> teacherNames) {
        this.stdName = stdName;
        this.teacherNames = teacherNames;
    }

}

package com.example.task.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TeacherWithStudentsDTO {
    private String tchrName;
    private List<String> studentNames;

    public TeacherWithStudentsDTO(String tchrName, List<String> studentNames) {
        this.tchrName = tchrName;
        this.studentNames = studentNames;
    }

}

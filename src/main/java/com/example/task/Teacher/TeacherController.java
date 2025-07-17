package com.example.task.Teacher;

import com.example.task.Student.Student;
import com.example.task.Repositoy.TeacherRepository;
import com.example.task.DTO.TeacherWithStudentsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/page")
    public Page<Teacher> getPagedTeachers(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }


    @GetMapping("/grouped")
    public List<TeacherWithStudentsDTO> getGroupedTeachersWithStudents() {
        List<Teacher> allTeachers = teacherRepository.findAll();

        return allTeachers.stream()
                .map(teacher -> new TeacherWithStudentsDTO(
                        teacher.getTchrName(),
                        teacher.getStudents().stream()
                                .map(Student::getStdName)
                                .toList()
                ))
                .toList();
    }


    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable int id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @PostMapping
    public String addTeacher(@RequestBody Teacher teacher) {
        teacherRepository.save(teacher);
        return "Teacher added successfully!";
    }

    @PutMapping("/{id}")
    public String updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        if (teacherRepository.existsById(id)) {
            teacher.setTchrId(id);
            teacherRepository.save(teacher);
            return "Teacher updated successfully!";
        }
        return "Teacher not found.";
    }

    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable int id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return "Teacher deleted successfully!";
        }
        return "Teacher not found.";
    }
}

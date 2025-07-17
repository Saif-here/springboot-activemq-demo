package com.example.task.Student;

import com.example.task.DTO.StudentWithTeachersDTO;
import com.example.task.Repositoy.StudentRepository;
import com.example.task.Teacher.Teacher;
import com.example.task.Repositoy.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public StudentController(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/page")
    public Page<Student> getPagedStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }


    @GetMapping("/grouped")
    public List<StudentWithTeachersDTO> getGroupedStudentsWithTeachers() {
        List<Student> allStudents = studentRepository.findAll();

        return allStudents.stream()
                .map(student -> new StudentWithTeachersDTO(
                        student.getStdName(),
                        student.getTeachers().stream()
                                .map(Teacher::getTchrName)
                                .toList()
                ))
                .toList();
    }


    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @PostMapping
    public String addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return "Student added successfully!";
    }

    @PutMapping("/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody Student student) {
        if (studentRepository.existsById(id)) {
            student.setStdId(id);
            studentRepository.save(student);
            return "Student updated successfully!";
        }
        return "Student not found.";
    }

    @PutMapping("/{studentId}/assign-teacher/{teacherId}")
    public String assignTeacherToStudent(@PathVariable int studentId, @PathVariable int teacherId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);

        if (student == null || teacher == null) {
            return "Student or Teacher not found.";
        }

        // Prevent duplicates
        if (!student.getTeachers().contains(teacher)) {
            student.getTeachers().add(teacher);
            studentRepository.save(student);
            return "Teacher assigned to student successfully!";
        } else {
            return "Teacher is already assigned to this student.";
        }
    }
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        Logger logger = LoggerFactory.getLogger(StudentController.class);
        logger.trace("Entered deleteStudent() method with ID: {}", id);
        try {
            logger.debug("Checking existence of student with ID: {}", id);

            if (studentRepository.existsById(id)) {
                logger.debug("Student exists. Proceeding with deletion...");
                studentRepository.deleteById(id);
                logger.info("Student with ID {} deleted successfully.", id);
                return "Student deleted successfully!";
            } else {
                logger.warn("Student with ID {} not found.", id);
                return "Student not found.";
            }
        } catch (Exception e) {
            logger.error("Error occurred while deleting student with ID {}: {}", id, e.getMessage());
            return "An error occurred while trying to delete the student.";
        }
    }


}

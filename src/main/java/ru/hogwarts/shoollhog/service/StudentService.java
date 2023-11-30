package ru.hogwarts.shoollhog.service;

import ru.hogwarts.shoollhog.model.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    Student getStudent(Long id);
    Student updateStudent(Long id, Student student);
    void  removeStudent(Long id);
    List<Student> getStudentsByAge(int age);
}
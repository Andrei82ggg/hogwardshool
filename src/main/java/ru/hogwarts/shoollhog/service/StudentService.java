package ru.hogwarts.shoollhog.service;

import ru.hogwarts.shoollhog.model.Student;

public interface StudentService {
    Student addStudent(Student student);
    Student getStudent(Long id);
    Student updateStudent(Long id, Student student);
    void  removeStudent(Long id);
}

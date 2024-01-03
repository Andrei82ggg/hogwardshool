package ru.hogwarts.shoollhog.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.shoollhog.model.Student;
import ru.hogwarts.shoollhog.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(String name, Integer age) {
        Student newStudent = new Student(name, age);
        newStudent = studentRepository.save(newStudent);
        return newStudent;
    }

    public Student get(long id) {
        return studentRepository.findById(id).get();
    }

    public Student update(long id, String name, Integer age) {
        Student studentForUpdate = get(id);
        studentForUpdate.setName(name);
        studentForUpdate.setAge(age);
        return studentRepository.save(studentForUpdate);
    }

    public Student delete(long id) {
        Student studentForDelete = get(id);
        studentRepository.deleteById(id);
        return studentForDelete;
    }

    public List<Student> getWhenAgeBetween(Integer min, Integer max) {
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public int getCount() {
        return studentRepository.getCount();
    }
    public double getAvgAge() {
        return studentRepository.getAvgAge();
    }
    public List<Student> getLastFive() {
        return studentRepository.getLastFiveOrderByIdDesc();
    }
}

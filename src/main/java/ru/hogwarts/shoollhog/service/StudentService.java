package ru.hogwarts.shoollhog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.shoollhog.model.Student;
import ru.hogwarts.shoollhog.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(String name, Integer age) {
        logger.info("Was invoked method add");
        Student newStudent = new Student(name, age);
        newStudent = studentRepository.save(newStudent);
        return newStudent;
    }

    public Student get(long id) {
        logger.info("Was invoked method get");
        return studentRepository.findById(id).get();
    }

    public Student update(long id, String name, Integer age) {
        logger.info("Was invoked method update");
        Student studentForUpdate = get(id);
        studentForUpdate.setName(name);
        studentForUpdate.setAge(age);
        return studentRepository.save(studentForUpdate);
    }

    public Student delete(long id) {
        logger.info("Was invoked method delete");
        Student studentForDelete = get(id);
        studentRepository.deleteById(id);
        return studentForDelete;
    }

    public List<Student> getWhenAgeBetween(Integer min, Integer max) {
        logger.info("Was invoked method getWhenAgeBetween");
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public int getCount() {
        logger.info("Was invoked method getCount");
        return studentRepository.getCount();
    }
    public double getAvgAge() {
        logger.info("Was invoked method getAvgAge");
        return studentRepository.getAvgAge();
    }
    public List<Student> getLastFive() {
        logger.info("Was invoked method getLastFive");
        return studentRepository.getLastFiveOrderByIdDesc();
    }
}

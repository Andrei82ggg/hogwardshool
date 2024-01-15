package ru.hogwarts.shoollhog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.shoollhog.model.Student;
import ru.hogwarts.shoollhog.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<String> getAllNamesStartWithA(){
        String firstSymbol = "A";
        return studentRepository.findAll().stream()
                .map(Student:: getName)
                .map(String:: toUpperCase)
                .filter(name -> name.startsWith(firstSymbol))
                .sorted()
                .collect(Collectors.toList());
    }

    public double getAvgAgeWithStream(){
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(-1);
    }

    public void printStudents(){
        List<Student> students = studentRepository.findAll();
        printStudent(students.get(0));
        printStudent(students.get(1));
         Thread thread1 = new Thread(() ->{
             printStudent(students.get(2));
             printStudent(students.get(3));
         });
         thread1.start();
        Thread thread2 = new Thread(() ->{
            printStudent(students.get(4));
            printStudent(students.get(5));
        });
        thread2.start();
    }

    public void printStudentsSync(){
        List<Student> students = studentRepository.findAll();
        printStudentSync(students.get(0));
        printStudentSync(students.get(1));
        Thread thread1 = new Thread(() ->{
            printStudentSync(students.get(2));
            printStudentSync(students.get(3));
        });
        thread1.start();
        Thread thread2 = new Thread(() ->{
            printStudentSync(students.get(4));
            printStudentSync(students.get(5));
        });
        thread2.start();
    }
    private void printStudent(Student student){
        logger.info("Thread: {}. Student: {]", Thread.currentThread(), student);
    }
    private synchronized void printStudentSync(Student student){
        printStudent(student);
    }
}

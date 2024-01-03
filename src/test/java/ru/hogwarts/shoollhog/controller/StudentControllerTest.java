package ru.hogwarts.shoollhog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.shoollhog.model.Student;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    void create_success() {
        Student studentForCreate = new Student("Gary", 20);
        Student expectedStudent = new Student("Gary", 20);
        expectedStudent.setId(1L);


        Student actualStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", studentForCreate, Student.class);
        assertNotNull(actualStudent);
        expectedStudent.setId(actualStudent.getId());
    }

    @Test
    void get() {
        Student studentForCreate = new Student("Gary", 20);
        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", studentForCreate, Student.class);
        long id = postedStudent.getId();
        Student actualStudent = this.restTemplate.getForObject("http://localhost:" + port + "/student?id=" + id, Student.class);
        assertNotNull(actualStudent);
        assertEquals(postedStudent, actualStudent);
    }

    @Test
    void update_success() {
        Student studentForCreate = new Student("Gary", 20);
        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", studentForCreate, Student.class);
        long id = postedStudent.getId();
        Student studentForUpdate = new Student("Gary", 20);
        studentForUpdate.setId(id);
        this.restTemplate.put("http://localhost:" + port + "/student", studentForUpdate);
        Student actualStudent = this.restTemplate.getForObject("http://localhost:" + port + "/student?id=" + id, Student.class);
        assertNotNull(actualStudent);
        assertEquals(studentForUpdate, actualStudent);
    }

    @Test
    void delete_success() {
        Student studenForGreate = new Student("Gary", 20);
        Student postedStudent = this.restTemplate.postForObject("http://localhost:" + port + "/student", studenForGreate, Student.class);
        long id = postedStudent.getId();
        Student studentForDelete = this.restTemplate.getForObject("http://localhost:" + port + "/student?id=" + id, Student.class);
        assertNotNull(studentForDelete);
        assertEquals(postedStudent, studentForDelete);
        this.restTemplate.delete("http://localhost:" + port + "/student?id=" + id);
        Student studentAfterDelete = this.restTemplate.getForObject("http://localhost:" + port + "/student" + id, Student.class);
        assertNull(studentAfterDelete.getId());
        assertNull(studentAfterDelete.getName());
    }
}

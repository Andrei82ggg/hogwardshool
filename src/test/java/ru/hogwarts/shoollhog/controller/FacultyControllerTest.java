package ru.hogwarts.shoollhog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import ru.hogwarts.shoollhog.model.Faculty;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerRestTemplateTest {
    @LocalServerPort
    private int port;
    @Autowired
    private FacultyController facultyController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void create_success() {
        Faculty facultyForCreate = new Faculty("Gryffindor", "red");
        Faculty expectedFaculty = new Faculty("Gryffindor", "red");
        expectedFaculty.setId(1L);


        Faculty actualFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", facultyForCreate, Faculty.class);
        assertNotNull(actualFaculty);
        expectedFaculty.setId(actualFaculty.getId());
        assertEquals(expectedFaculty, actualFaculty);
        }

    @Test
    void get() {
        Faculty facultyForCreate = new Faculty("Gryffindor", "red");
        Faculty postedFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", facultyForCreate, Faculty.class);
        long id = postedFaculty.getId();
        Faculty actualFaculty = this.restTemplate.getForObject("http://localhost:" + port + "/faculty?id=" + id, Faculty.class);
        assertNotNull(actualFaculty);
        assertEquals(postedFaculty, actualFaculty);
    }

    @Test
    void update_success() {
        Faculty facultyForCreate = new Faculty("Gryffindor", "red");
        Faculty postedFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", facultyForCreate, Faculty.class);
        long id = postedFaculty.getId();
        Faculty facultyForUpdate = new Faculty("Gryffindor", "green");
        facultyForUpdate.setId(id);
        this.restTemplate.put("http://localhost:" + port + "/faculty", facultyForUpdate);
        Faculty actualFaculty = this.restTemplate.getForObject("http://localhost:" + port + "/faculty?id=" + id, Faculty.class);
        assertNotNull(actualFaculty);
        assertEquals(facultyForUpdate, actualFaculty);
    }

    @Test
    void delete_success() {
        Faculty facultyForCreate = new Faculty("Gryffindor", "red");
        Faculty postedFaculty = this.restTemplate.postForObject("http://localhost:" + port + "/faculty", facultyForCreate, Faculty.class);
        long id = postedFaculty.getId();
        Faculty facultyForDelete = this.restTemplate.getForObject("http://localhost:" + port + "/faculty?id=" + id, Faculty.class);
        assertNotNull(facultyForDelete);
        assertEquals(postedFaculty, facultyForDelete);
        this.restTemplate.delete("http://localhost:" + port + "/faculty?id=" + id);
        Faculty facultyAfterDelete = this.restTemplate.getForObject("http://localhost:" + port + "/faculty" + id, Faculty.class);
        assertNull(facultyAfterDelete.getId());
        assertNull(facultyAfterDelete.getColor());
        assertNull(facultyAfterDelete.getName());
    }
}
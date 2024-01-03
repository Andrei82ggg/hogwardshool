package ru.hogwarts.shoollhog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.shoollhog.model.Faculty;
import ru.hogwarts.shoollhog.repository.FacultyRepository;
import ru.hogwarts.shoollhog.service.FacultyService;
import ru.hogwarts.shoollhog.service.StudentService;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)
class FacultyControllerWebMvcTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @MockBean
    private StudentService studentService;
    @SpyBean
    private FacultyService facultyService;
    @InjectMocks
    private FacultyController facultyController;

    @Test
    void create() throws Exception {
        String name = "Gryffindor";
        String color = "red";
        Faculty facultyForCreate = new Faculty(name, color);
        String request = objectMapper.writeValueAsString(facultyForCreate);
        long id = 1L;
        Faculty facultyAfterCreate = new Faculty(name,color);
        facultyAfterCreate.setId(id);
        when(facultyService.add(name,color)).thenReturn(facultyAfterCreate);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(facultyForCreate.getName()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(facultyForCreate.getColor()))
                .andReturn();
    }

   // @Test
    void get() throws Exception {
        String name = "Gryffindor";
        String color = "red";
        long id = 1L;
        Faculty facultyForCreate = new Faculty(name, color);
        facultyForCreate.setId(id);
        when(facultyService.get(id)).thenReturn(facultyForCreate);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .param("id", String.valueOf(id)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(facultyForCreate.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(facultyForCreate.getColor()))
                .andReturn();
    }//

    //@Test
    void update() throws Exception {
        String name = "Gryffindor";
        String color = "red";
        long id = 1L;
        Faculty facultyForUpdate = new Faculty(name, color);

        String request = objectMapper.writeValueAsString(facultyForUpdate);
        facultyForUpdate.setId(id);
        when(facultyService.update(id, name, color));
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(facultyForUpdate.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value(facultyForUpdate.getColor()))
                .andReturn();
    }//


    @Test
    void delete() {
    }
}
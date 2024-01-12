package ru.hogwarts.shoollhog.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.shoollhog.model.Faculty;
import ru.hogwarts.shoollhog.service.FacultyService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty create(@RequestBody Faculty faculty) {
        return facultyService.add(faculty.getName(), faculty.getColor());
    }

    @GetMapping
    public Faculty get(@RequestParam long id) {
        return facultyService.get(id);
    }

    @PutMapping
    public Faculty update(@RequestBody Faculty faculty) {
        return facultyService.update(faculty.getId(), faculty.getName(), faculty.getColor());
    }

    @DeleteMapping
    public Faculty delete(@RequestParam long id) {
        return facultyService.delete(id);
    }

    @GetMapping("/longest-name")
    public String getLongestName(){
        return facultyService.getLongestName();
    }
}
package ru.hogwarts.shoollhog.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.shoollhog.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyServiceImpl implements FacultyService {
    private Map<Long, Faculty> facultyMap = new HashMap<>();
    private long counter = 0;

    @Override
    public Faculty addFaculty(Faculty faculty) {
        long id = counter++;
        Faculty newFaculty = new Faculty(id, faculty.getName(), faculty.getColor());
        facultyMap.put(id, newFaculty);
        return newFaculty;
    }

    @Override
    public Faculty getFaculty(Long id) {
        return facultyMap.get(id);
    }

    @Override
    public Faculty updateFaculty(Long id) {
        return null;
    }

    @Override
    public Faculty updateFaculty(Long id, Faculty faculty) {
        Faculty existingFaculty = facultyMap.get(id);
        existingFaculty.setName(faculty.getName());
        existingFaculty.setColor(faculty.getColor());
        return existingFaculty;
    }

    @Override
    public void removeFaculty(Long id) {
        facultyMap.remove(id);
    }

    @Override
    public Faculty add(Faculty faculty) {
        return null;
    }

    @Override
    public Faculty get(long id) {
        return null;
    }

    @Override
    public Faculty update(Faculty faculty) {
        return null;
    }

    @Override
    public boolean remove(long id) {
        return false;
    }

    @Override
    public Collection<Faculty> filterByColor(String color) {
        return null;
    }
}

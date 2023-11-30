package ru.hogwarts.shoollhog.service;

import ru.hogwarts.shoollhog.model.Faculty;

import java.util.Collection;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);
    Faculty getFaculty(Long id);
    Faculty updateFaculty(Long id);

    Faculty updateFaculty(Long id, Faculty faculty);

    void removeFaculty(Long id);

    Faculty add(Faculty faculty);

    Faculty get(long id);

    Faculty update(Faculty faculty);

    boolean remove(long id);

    Collection<Faculty> filterByColor(String color);
}

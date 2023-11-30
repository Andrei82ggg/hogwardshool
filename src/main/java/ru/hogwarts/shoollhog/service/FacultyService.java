package ru.hogwarts.shoollhog.service;

import ru.hogwarts.shoollhog.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class FacultyService {
    private final Map<Long, Faculty> storage = new HashMap<>();
    private long counter = 0;
    public Faculty add(Faculty faculty){
        faculty.setId(counter);
        storage.put(counter, faculty);
        counter++;
        return faculty;
    }
    public Faculty get(long id){
        return storage.get(id);
    }
    public boolean remove(long id){
        return storage.remove(id) != null;
    }
    public Faculty update(Faculty faculty){
        if (storage.containsKey(faculty.getId())){
            storage.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Collection<Faculty> filterByColor(String color) {
        return storage.values()
                .stream()
                .filter(faculty -> faculty.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }

    public abstract Faculty addFaculty(Faculty faculty);

    public abstract Faculty getFaculty(Long id);

    public abstract Faculty updateFaculty(Long id, Faculty faculty);

    public abstract void removeFaculty(Long id);
}

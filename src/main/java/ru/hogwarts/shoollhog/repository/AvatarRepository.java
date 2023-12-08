package ru.hogwarts.shoollhog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.shoollhog.model.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}

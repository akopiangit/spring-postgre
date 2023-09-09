package ru.akopian.springpostgre.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import ru.akopian.springpostgre.model.entity.Profile;

import java.util.Optional;


@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Profile> findFirstById(Long id);
}

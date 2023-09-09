package ru.akopian.springpostgre.service.test_locks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akopian.springpostgre.model.entity.Profile;
import ru.akopian.springpostgre.repository.ProfileRepository;


@Service
@RequiredArgsConstructor
@Slf4j
public class PersistenceService {
    private final ProfileRepository profileRepository;

    @Transactional
    public Long create() {
        Profile profile = new Profile();
        profile.setName("test");
        profile.setUsername("test");
        return profileRepository.save(profile).getId();
    }

    @Transactional
    public void startLock(Long profileId) {
        Profile profile = profileRepository.findFirstById(profileId).orElse(null);
        log.info("locked");
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        profile.setName("another name");
        profileRepository.save(profile);
        log.info("lock released");
    }

    @Transactional
    public void startUpdateWithoutLock(Long profileId) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("attempt to update");
        Profile profile = profileRepository.findById(profileId).orElse(null);
        profile.setName("aloha!");
        profileRepository.save(profile);
        log.info("update end");
    }
}

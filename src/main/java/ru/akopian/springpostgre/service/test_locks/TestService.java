package ru.akopian.springpostgre.service.test_locks;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Priority;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestService {

    private final PersistenceService persistenceService;
    private volatile Long profileId;

    private void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    @PostConstruct
    @Priority(0)
    public void createAndSetId() {
        setProfileId(persistenceService.create());
        log.info("profile created");
        new Thread(() -> persistenceService.startLock(profileId)).start();
        new Thread(() -> persistenceService.startUpdateWithoutLock(profileId)).start();
    }
}

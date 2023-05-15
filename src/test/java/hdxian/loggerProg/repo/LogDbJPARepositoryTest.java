package hdxian.loggerProg.repo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LogDbJPARepositoryTest {

    private final LogDbJPARepository repository;

    @Autowired
    public LogDbJPARepositoryTest(LogDbJPARepository repository) {
        this.repository = repository;
    }

    @Test
    void findById() {

        // when
        
        // then

        // given

    }

    @Test
    void findByPriorityAsc() {
    }

    @Test
    void findByPriorityDesc() {
    }

    @Test
    void findByHostAsc() {
    }

    @Test
    void findByHostDesc() {
    }

    @Test
    void findByMsgAsc() {
    }

    @Test
    void findByMsgDesc() {
    }

    @Test
    void findByDateAsc() {
    }

    @Test
    void findByDateDesc() {
    }

    @Test
    void getAllLogs() {
    }
}
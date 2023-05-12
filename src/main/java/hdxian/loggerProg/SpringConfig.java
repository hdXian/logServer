package hdxian.loggerProg;

import hdxian.loggerProg.repo.LogDbJPARepository;
import hdxian.loggerProg.repo.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;

public class SpringConfig {

    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public LogRepository logRepository() {
        return new LogDbJPARepository(em);
    }


}

package hdxian.loggerProg;

import hdxian.loggerProg.Service.LogAdminService;
import hdxian.loggerProg.repo.LogAdminRepository;
import hdxian.loggerProg.repo.LogAdminJPARepository;
import hdxian.loggerProg.repo.LogJPARepository;
import hdxian.loggerProg.repo.LogRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public LogRepository logRepository() {
        return new LogJPARepository(em);
    }

    @Bean
    public LogAdminRepository adminRepository() {
        return new LogAdminJPARepository(em);
    }

    @Bean
    public LogAdminService adminService() {
        return new LogAdminService(adminRepository());
    }


}

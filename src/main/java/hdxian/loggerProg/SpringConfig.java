package hdxian.loggerProg;

import com.fasterxml.jackson.databind.ObjectMapper;
import hdxian.loggerProg.Service.LogAdminService;
import hdxian.loggerProg.Service.LogService;
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

    // 엑셀 파일 다운로드 기능 추가
    private final ObjectMapper objectMapper;

    public SpringConfig(EntityManager em, ObjectMapper objectMapper) {
        this.em = em;
        this.objectMapper = objectMapper;
    }

    @Bean
    public LogAdminRepository adminRepository() {
        return new LogAdminJPARepository(em);
    }

    @Bean
    public LogAdminService adminService() {
        return new LogAdminService(adminRepository());
    }

    @Bean
    public LogRepository logRepository() {
        return new LogJPARepository(em);
    }

    @Bean
    public LogService logService() {
        return new LogService(logRepository(), objectMapper);
    }


}

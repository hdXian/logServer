package hdxian.loggerProg.repo;

import hdxian.loggerProg.domain.log_admin;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.Optional;

public class LogAdminJPARepository implements LogAdminRepository {

    private final EntityManager em;

    @Autowired
    public LogAdminJPARepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<log_admin> findById(String id) {
        log_admin res = em.find(log_admin.class, id);
        return Optional.ofNullable(res);
    }

}

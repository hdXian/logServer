package hdxian.loggerProg.repo;

import hdxian.loggerProg.domain.LogAdmin;
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
    public Optional<LogAdmin> findById(String id) {
        LogAdmin res = em.find(LogAdmin.class, id);
        return Optional.ofNullable(res);
    }

}

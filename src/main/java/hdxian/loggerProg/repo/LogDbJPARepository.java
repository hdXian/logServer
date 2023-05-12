package hdxian.loggerProg.repo;

import hdxian.loggerProg.domain.Log;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class LogDbJPARepository implements LogRepository {

    private final EntityManager em;

    @Autowired
    public LogDbJPARepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Log> findById(Long id) {
        Log log = em.find(Log.class, id);
        return Optional.ofNullable(log);
    }

    @Override
    public List<Log> findByPriorityAsc(String priority) {
        return em.createQuery("select se from SystemEvents se order by Priority", Log.class)
                .getResultList();
    }

    @Override
    public List<Log> findByPriorityDesc(String priority) {
        return em.createQuery("select se from SystemEvents se order by Priority desc", Log.class)
                .getResultList();
    }

    @Override
    public List<Log> findByHostAsc(String host) {
        return null;
    }

    @Override
    public List<Log> findByHostDesc(String host) {
        return null;
    }

    @Override
    public List<Log> findByMsgAsc(String msg) {
        return null;
    }

    @Override
    public List<Log> findByMsgDesc(String msg) {
        return null;
    }

    @Override
    public List<Log> findByDateAsc(String date) {
        return null;
    }

    @Override
    public List<Log> findByDateDesc(String date) {
        return null;
    }

    @Override
    public List<Log> getAllLogs() {
        return null;
    }
}

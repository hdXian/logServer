package hdxian.loggerProg.repo;

import hdxian.loggerProg.domain.Log;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class LogJPARepository implements LogRepository {

    private final EntityManager em;

    @Autowired
    public LogJPARepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Log> findById(Long id) {
        Log res = em.find(Log.class, id);
        return Optional.ofNullable(res);
    }

    @Override
    public List<Log> findByPriority(int order) {

        if(order == 1) { // 오름차순
            return em.createQuery("select se from SystemEvents se order by Priority asc", Log.class)
                    .getResultList();
        }
        else { // 내림차순
            return em.createQuery("select se from SystemEvents se order by Priority desc", Log.class)
                    .getResultList();
        }

    }

    @Override
    public List<Log> findByHost(int order) {

        if(order == 1) { // 오름차순
            return em.createQuery("select se from SystemEvents se order by FromHost asc", Log.class)
                    .getResultList();
        }
        else { // 내림차순
            return em.createQuery("select se from SystemEvents se order by FromHost desc", Log.class)
                    .getResultList();
        }

    }

    @Override
    public List<Log> findByMsg(String msg) {
        return em.createQuery("select se from SystemEvents se where Message like %:msg%", Log.class)
                .setParameter("msg", msg)
                .getResultList();
    }


    @Override
    public List<Log> findByDate(int order) {
        if(order == 1) { // 오름차순
            return em.createQuery("select se from SystemEvents se order by FromHost asc", Log.class)
                    .getResultList();
        }
        else { // 내림차순
            return em.createQuery("select se from SystemEvents se order by FromHost desc", Log.class)
                    .getResultList();
        }
    }

    @Override
    public List<Log> getAllLogs() {
        return em.createQuery("select se from SystemEvents se", Log.class)
                .getResultList();
    }

}

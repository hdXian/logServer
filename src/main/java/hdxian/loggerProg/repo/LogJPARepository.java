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
    public List<Log> findByPriority(String msg, int order) {

        if(order == 1) { // 오름차순
            return em.createQuery("select l from Log l where l.Message like concat('%', :msg ,'%') order by Priority asc", Log.class)
                    .setParameter("msg", msg)
                    .getResultList();
        }
        else { // 내림차순
            return em.createQuery("select l from Log l where l.Message like concat('%', :msg ,'%') order by Priority desc", Log.class)
                    .setParameter("msg", msg)
                    .getResultList();
        }

    }

    @Override
    public List<Log> findByHost(String msg, int order) {

        if(order == 1) { // 오름차순
            return em.createQuery("select l from Log l where l.Message like concat('%', :msg ,'%') order by FromHost asc", Log.class)
                    .setParameter("msg", msg)
                    .getResultList();
        }
        else { // 내림차순
            return em.createQuery("select l from Log l where l.Message like concat('%', :msg ,'%') order by FromHost desc", Log.class)
                    .setParameter("msg", msg)
                    .getResultList();
        }

    }

    @Override
    public List<Log> findByMsg(String msg) {
        return em.createQuery("select l from Log l where l.Message like concat('%', :msg ,'%')", Log.class)
                .setParameter("msg", msg)
                .getResultList();
    }


    @Override
    public List<Log> findByDate(String msg, int order) {
        if(order == 1) { // 오름차순
            return em.createQuery("select l from Log l where l.Message like concat('%', :msg ,'%') order by DeviceReportedTime asc", Log.class)
                    .setParameter("msg", msg)
                    .getResultList();
        }
        else { // 내림차순
            return em.createQuery("select l from Log l where l.Message like concat('%', :msg ,'%') order by DeviceReportedTime desc", Log.class)
                    .setParameter("msg", msg)
                    .getResultList();
        }
    }

    @Override
    public List<Log> getAll() {
        return em.createQuery("select l from Log l", Log.class)
                .getResultList();
    }

}

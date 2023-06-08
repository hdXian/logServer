package hdxian.loggerProg.repo;

import hdxian.loggerProg.custom.DateLogStat;
import hdxian.loggerProg.custom.DayHostLogStat;
import hdxian.loggerProg.custom.DayPriorityLogStat;
import hdxian.loggerProg.domain.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional
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
        return em.createQuery("select l from Log l order by DeviceReportedTime desc", Log.class)
                .getResultList();
    }

    @Override
    public List<Log> getSome(int num) {
        return em.createQuery("select l from Log l order by DeviceReportedTime desc", Log.class)
                .setMaxResults(num)
                .getResultList();
    }

    @Override
    public List<DateLogStat> getStatByDate() {
        return em.createQuery("select new hdxian.loggerProg.custom.DateLogStat(" +
                        "date(l.DeviceReportedTime), " +
                        "count(*)) " +
                        "from Log l " +
                        "group by date(l.DeviceReportedTime) " +
                        "order by date(l.DeviceReportedTime) desc ", DateLogStat.class)
                .setMaxResults(5)
                .getResultList();
    }

    @Override
    public List<DayHostLogStat> getStatByDayHost() {
        return em.createQuery("select new hdxian.loggerProg.custom.DayHostLogStat(" +
                "l.FromHost, " +
                "curdate(), " +
                "count(*)) " +
                "from Log l " +
                "where date(l.DeviceReportedTime) = curdate() " +
                "group by l.FromHost", DayHostLogStat.class).getResultList();
    }

    @Override
    public List<DayPriorityLogStat> getStatByDayPriority() {
        return em.createQuery("select new hdxian.loggerProg.custom.DayPriorityLogStat(" +
                "l.Priority, " +
                "curdate(), " +
                "count(*)) " +
                "from Log l " +
                "where date(DeviceReportedTime) = curdate() " +
                "group by l.Priority", DayPriorityLogStat.class).getResultList();
    }

}

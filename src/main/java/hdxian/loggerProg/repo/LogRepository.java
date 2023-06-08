package hdxian.loggerProg.repo;

import hdxian.loggerProg.custom.DateLogStat;
import hdxian.loggerProg.custom.DayHostLogStat;
import hdxian.loggerProg.custom.DayPriorityLogStat;
import hdxian.loggerProg.domain.Log;

import java.util.List;
import java.util.Optional;

public interface LogRepository {

    Optional<Log> findById(Long id);
    List<Log> findByPriority(String msg, int limit, int order);
    List<Log> findByHost(String msg, int limit, int order);
    List<Log> findByDate(String msg, int limit, int order);
    List<Log> findByMsg(String msg);
    List<Log> getAll();
    List<Log> getSome(int num);
    List<DateLogStat> getStatByDate();
    List<DayHostLogStat> getStatByDayHost();
    List<DayPriorityLogStat> getStatByDayPriority();

}

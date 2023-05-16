package hdxian.loggerProg.repo;

import hdxian.loggerProg.domain.Log;

import java.util.List;
import java.util.Optional;

public interface LogRepository {

    Optional<Log> findById(Long id);
    List<Log> findByPriority(int order);
    List<Log> findByHost(int order);
    List<Log> findByMsg(String msg);
    List<Log> findByDate(int order);
    List<Log> getAllLogs();
}

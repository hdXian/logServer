package hdxian.loggerProg.repo;

import hdxian.loggerProg.domain.Log;

import java.util.List;
import java.util.Optional;

public interface LogRepository {

    Optional<Log> findById(Long id);
    List<Log> findByPriorityAsc(String priority);
    List<Log> findByPriorityDesc(String priority);
    List<Log> findByHostAsc(String host);
    List<Log> findByHostDesc(String host);
    List<Log> findByMsgAsc(String msg);
    List<Log> findByMsgDesc(String msg);
    List<Log> findByDateAsc(String date);
    List<Log> findByDateDesc(String date);
    List<Log> getAllLogs();
}

package hdxian.loggerProg.Service;

import hdxian.loggerProg.domain.Log;
import hdxian.loggerProg.repo.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LogService {

    private final LogRepository repository;

    @Autowired
    public LogService(LogRepository repository) {
        this.repository = repository;
    }

    public List<Log> getAllLogs() {
        return repository.getAll();
    }

    public List<Log> getSortedLogs(String search, String sort) throws NullPointerException {
        if(search == null || sort == null) {
            throw new NullPointerException("search or sort is null");
        }
        switch (sort) {
            case "ta":
                return repository.findByDate(search, 1);
            case "td":
                return repository.findByDate(search, 0);
            case "pa":
                return repository.findByPriority(search, 1);
            case "pd":
                return repository.findByPriority(search, 0);
            default:
                return repository.getAll();
        }
    }


}

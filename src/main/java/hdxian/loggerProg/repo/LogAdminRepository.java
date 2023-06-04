package hdxian.loggerProg.repo;

import hdxian.loggerProg.domain.LogAdmin;

import java.util.Optional;

public interface LogAdminRepository {
    Optional<LogAdmin> findById(String id);
}

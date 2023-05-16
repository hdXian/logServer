package hdxian.loggerProg.repo;

import hdxian.loggerProg.domain.log_admin;

import java.util.Optional;

public interface LogAdminRepository {
    Optional<log_admin> findById(String id);
}

package hdxian.loggerProg.Service;

import hdxian.loggerProg.domain.log_admin;
import hdxian.loggerProg.repo.LogAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class LogAdminService {

    private final LogAdminRepository repository;

    @Autowired
    public LogAdminService(LogAdminRepository adminDataJPARepository) {
        this.repository = adminDataJPARepository;
    }

    public Optional<log_admin> findAdmin(String id) {
        return repository.findById(id);
    }


}

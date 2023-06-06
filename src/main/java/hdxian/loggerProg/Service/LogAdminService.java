package hdxian.loggerProg.Service;

import hdxian.loggerProg.domain.LogAdmin;
import hdxian.loggerProg.repo.LogAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class LogAdminService {

    private final LogAdminRepository repository;

    @Autowired
    public LogAdminService(LogAdminRepository adminDataJPARepository) {
        this.repository = adminDataJPARepository;
    }

    public Optional<LogAdmin> findAdmin(String id) {
        return repository.findById(id);
    }

    public Optional<LogAdmin> changePw(LogAdmin admin) {
        return repository.updatePw(admin);
    }


}

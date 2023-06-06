package hdxian.loggerProg.repo;

import hdxian.loggerProg.domain.LogAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

@Transactional
public class LogAdminJPARepository implements LogAdminRepository {

    private final EntityManager em;

    @Autowired
    public LogAdminJPARepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<LogAdmin> findById(String id) {
        LogAdmin res = em.find(LogAdmin.class, id);
        return Optional.ofNullable(res);
    }

    @Override
    public Optional<LogAdmin> updatePw(LogAdmin admin) {
        em.createQuery("update from LogAdmin la set la.password = :password where la.id= :id ")
                .setParameter("password", admin.getPassword())
                .setParameter("id", admin.getId());

        return Optional.of(admin);
    }
}

package hdxian.loggerProg.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class log_admin {

    @Id
    private String id;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

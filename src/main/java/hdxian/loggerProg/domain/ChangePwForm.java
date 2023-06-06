package hdxian.loggerProg.domain;

public class ChangePwForm {

    private String id;
    private String oldPw;
    private String newPw;
    private String checkPw;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOldPw() {
        return oldPw;
    }

    public void setOldPw(String oldPw) {
        this.oldPw = oldPw;
    }

    public String getNewPw() {
        return newPw;
    }

    public void setNewPw(String newPw) {
        this.newPw = newPw;
    }

    public String getCheckPw() {
        return checkPw;
    }

    public void setCheckPw(String checkPw) {
        this.checkPw = checkPw;
    }

}

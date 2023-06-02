package hdxian.loggerProg.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SystemEvents")
public class Log {

    @Id
    private int id;
    private String ReceivedAt;
    private String DeviceReportedTime;
    private int Facility;
    private int Priority;
    private String FromHost;
    private String Message;
    private String SysLogTag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceivedAt() {
        return ReceivedAt;
    }

    public void setReceivedAt(String receivedAt) {
        ReceivedAt = receivedAt;
    }

    public String getDeviceReportedTime() {
        return DeviceReportedTime;
    }

    public void setDeviceReportedTime(String deviceReportedTime) {
        DeviceReportedTime = deviceReportedTime;
    }

    public int getFacility() {
        return Facility;
    }

    public void setFacility(int facility) {
        Facility = facility;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }

    public String getFromHost() {
        return FromHost;
    }

    public void setFromHost(String fromHost) {
        FromHost = fromHost;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getSysLogTag() {
        return SysLogTag;
    }

    public void setSysLogTag(String sysLogTag) {
        SysLogTag = sysLogTag;
    }

    @Override
    public String toString() {
        return "id:" + id + " Priority:" + Priority + " FromHost:" + FromHost + " Message:" + Message + " Time:" + DeviceReportedTime;
    }
}

package hdxian.loggerProg.custom;

import java.util.Date;

// 일간 호스트별 통계
public class DayHostLogStat {

    private String FromHost;
    private Date today;
    private long count;

    public DayHostLogStat(String fromHost, Date today, long count) {
        FromHost = fromHost;
        this.today = today;
        this.count = count;
    }

    public String getFromHost() {
        return FromHost;
    }

    public void setFromHost(String fromHost) {
        FromHost = fromHost;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

}

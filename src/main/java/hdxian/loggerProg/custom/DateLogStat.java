package hdxian.loggerProg.custom;

import java.util.Date;

// 날짜별 통계
public class DateLogStat {
    private Date date;
    private long count;

    public DateLogStat(Date date, long count) {
        this.date = date;
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

}

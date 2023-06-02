package hdxian.loggerProg.custom;

import java.util.Date;

// 일간 호스트별 통계
public class DayPriorityLogStat {

    private int priority;
    private Date today;
    private long count;

    public DayPriorityLogStat(int priority, Date today, long count) {
        this.priority = priority;
        this.today = today;
        this.count = count;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

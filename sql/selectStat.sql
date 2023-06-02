select Priority, count(*)
from SystemEvents
group by Priority;

select FromHost, (curdate()-1), count(*)
from SystemEvents
where date(DeviceReportedTime) = (curdate()-1)
group by FromHost;

select date(DeviceReportedTime), count(*)
from SystemEvents
group by date(DeviceReportedTime);

select date(DeviceReportedTime), FromHost, count(*)
from SystemEvents
group by date(DeviceReportedTime), FromHost;

-- 최근 5일간 통계
select date(DeviceReportedTime), count(*)
from SystemEvents
group by date(DeviceReportedTime)
order by date(DeviceReportedTime) desc
limit 5;

-- 일간 호스트별 통계
select FromHost, curdate(), count(*)
from SystemEvents
where date(DeviceReportedTime) = curdate()
group by FromHost;

-- 일간 priority별 통계
select Priority, to_char(curdate(), 'YYYY-MM-DD'), count(*)
from SystemEvents
where date(DeviceReportedTime) = curdate()
group by Priority;

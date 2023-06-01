package hdxian.loggerProg.Controller;

import hdxian.loggerProg.Service.LogService;
import hdxian.loggerProg.custom.DateLogStat;
import hdxian.loggerProg.custom.DayHostLogStat;
import hdxian.loggerProg.custom.DayPriorityLogStat;
import hdxian.loggerProg.domain.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class logController {

    private final LogService logService;

    @Autowired
    public logController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("log/logList") // 로그 검색
    public String logList(@RequestParam(value = "search") String search, @RequestParam(value = "sort") String sort, Model model) {
        List<Log> res = logService.getSortedLogs(search, sort);
        model.addAttribute("logs", res);
;
        return "log/logMonitor";
    }

//    @GetMapping("log/logMonitor") // 로그인 후 최초 접속
//    public String logMonitor(Model model) {
//        List<Log> res = logService.getAllLogs();
//        model.addAttribute("logs", res);
//        return "log/logMonitor";
//    }

    @GetMapping("/home") // 로그인 후 최초 접속
    public String logDashBoard(Model model) {

        List<DateLogStat> dateStat = logService.getDateLogStat();
        List<DayHostLogStat> dayHostStat = logService.getDayHostLogStat();
        List<DayPriorityLogStat> dayPriorityStat = logService.getDayPriorityLogStat();

        model.addAttribute("dateStat", dateStat);
        model.addAttribute("dayHostStat", dayHostStat);
        model.addAttribute("dayPriorityStat", dayPriorityStat);

        System.out.println("dateStat");
        for(DateLogStat stat: dateStat) {
            System.out.println(stat.getCount());
            System.out.println(stat.getDate());
        }

        System.out.println("dayHostStat");
        for(DayHostLogStat stat: dayHostStat) {
            System.out.println(stat.getFromHost());
            System.out.println(stat.getToday());
            System.out.println(stat.getCount());
        }

        System.out.println("dayPriorityStat");
        for(DayPriorityLogStat stat: dayPriorityStat) {
            System.out.println(stat.getPriority());
            System.out.println(stat.getCount());
            System.out.println(stat.getToday());
        }

//        // 최근 5일간의 날짜와 로그 통계
//        Date[] dateStatDays = dateStat.stream().map(DateLogStat::getDate).toArray(Date[]::new);
//        long[] dateStatCounts = dateStat.stream().mapToLong(DateLogStat::getCount).toArray();
//
//        // 일간 호스트별 통계
//        String[] dayHostFromHosts = dayHostStat.stream().map(DayHostLogStat::getFromHost).toArray(String[]::new);
//        Date[] dayHostToday = dayHostStat.stream().map(DayHostLogStat::getToday).toArray(Date[]::new);
//        long[] dayHostCounts = dayHostStat.stream().mapToLong(DayHostLogStat::getCount).toArray();
//
//        // 일간 우선순위별 통계
//        int[] dayPriorities = dayPriorityStat.stream().mapToInt(DayPriorityLogStat::getPriority).toArray();
//        Date[] dayPriorityToday = dayPriorityStat.stream().map(DayPriorityLogStat::getToday).toArray(Date[]::new);
//        long[] dayPriorityCounts = dayPriorityStat.stream().mapToLong(DayPriorityLogStat::getCount).toArray();

//        model.addAttribute("dateStatDays", dateStatDays);
//        model.addAttribute("dateStatCounts", dateStatCounts);
//
//        model.addAttribute("dayHostFromHosts", dayHostFromHosts);
//        model.addAttribute("dayHostToday", dayHostToday);
//        model.addAttribute("dayHostCounts", dayHostCounts);
//
//        model.addAttribute("dayPriorities", dayPriorities);
//        model.addAttribute("dayPriorityToday", dayPriorityToday);
//        model.addAttribute("dayPriorityCounts", dayPriorityCounts);

        return "log/dashBoard";
    }


    @GetMapping("log/getXlsx") // 엑셀 파일로 다운로드
    public void getXlsx(@RequestParam(value = "search") String search, @RequestParam(value = "sort") String sort, HttpServletResponse response) {
        logService.getLogExcel(response, search, sort);
    }

    @GetMapping("log/showInGraph")
    public String showInGraph(@RequestParam(value = "search") String search, @RequestParam(value = "sort") String sort, Model model) {
        List<Log> res = logService.getSortedLogs(search, sort);
        model.addAttribute("logList", res);
        return "log/logGraph";
    }





}

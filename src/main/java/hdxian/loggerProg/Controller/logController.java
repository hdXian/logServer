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

    @GetMapping("/home") // 로그인 후 최초 접속
    public String logDashBoard(Model model) {

        List<DateLogStat> dateStat = logService.getDateLogStat();
        List<DayHostLogStat> dayHostStat = logService.getDayHostLogStat();
        List<DayPriorityLogStat> dayPriorityStat = logService.getDayPriorityLogStat();
        List<Log> logList = logService.getSomeLogs(10);

        model.addAttribute("dateStat", dateStat);
        model.addAttribute("dayHostStat", dayHostStat);
        model.addAttribute("dayPriorityStat", dayPriorityStat);
        model.addAttribute("logList", logList);

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

        System.out.println("logList");
        for(Log log: logList) {
            System.out.println(log);
        }

        return "log/dashBoard";
    }

    @GetMapping("home/logDetails") // 상세보기 클릭 시
    public String logMonitor(Model model) {
        List<Log> res = logService.getAllLogs();
        model.addAttribute("logs", res);
        return "log/logDetails";
    }

    @GetMapping("details/searchLog") // 로그 검색
    public String logList(@RequestParam(value = "search") String search, @RequestParam(value = "sort") String sort, Model model) {
        List<Log> res = logService.getSortedLogs(search, sort);
        model.addAttribute("logs", res);
;
        return "log/logDetails";
    }

    @GetMapping("details/getXlsx") // 엑셀 파일로 다운로드
    public void getXlsx(@RequestParam(value = "search") String search, @RequestParam(value = "sort") String sort, HttpServletResponse response) {
        logService.getLogExcel(response, search, sort);
    }



}

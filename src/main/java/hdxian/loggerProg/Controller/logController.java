package hdxian.loggerProg.Controller;

import hdxian.loggerProg.Service.LogService;
import hdxian.loggerProg.domain.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        return "log/logMonitor";
    }

    @GetMapping("log/logMonitor") // 로그인 후 최초 접속
    public String logMonitor(Model model) {
        List<Log> res = logService.getAllLogs();
        model.addAttribute("logs", res);
        return "log/logMonitor";
    }


    @GetMapping("log/getXlsx") // 엑셀 파일로 다운로드
    public ResponseEntity getXlsx(@RequestParam(value = "search") String search, @RequestParam(value = "sort") String sort, HttpServletResponse response) {
        return ResponseEntity.ok(logService.getLogExcel(response, sort, search));
    }


}

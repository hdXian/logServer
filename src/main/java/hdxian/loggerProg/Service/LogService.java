package hdxian.loggerProg.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hdxian.loggerProg.domain.Log;
import hdxian.loggerProg.repo.LogRepository;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogService {

    private final LogRepository repository;

    private final ObjectMapper objectMapper;

    @Autowired
    public LogService(LogRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }


    public List<Log> getAllLogs() {
        return repository.getAll();
    }

    public List<Log> getSortedLogs(String search, String sort) throws NullPointerException {
        return getLogs(search, sort);
    }

    public Object getLogExcel(HttpServletResponse response, String search, String sort)  {
        List<Log> logList = getLogs(search, sort);

        createExcelDownloadResponse(response, logList);

        List<Map> logMap = logList.stream()
                .map(log -> objectMapper.convertValue(log, Map.class))
                .collect(Collectors.toList());

        return logMap;
    }


    private List<Log> getLogs(String search, String sort) throws NullPointerException {
        if(search == null || sort == null) {
            throw new NullPointerException("search or sort is null");
        }
        switch (sort) {
            case "ta":
                // System.out.println("called ta");
                return repository.findByDate(search, 1);
            case "td":
                // System.out.println("called td");
                return repository.findByDate(search, 0);
            case "pa":
                // System.out.println("called pa");
                return repository.findByPriority(search, 1);
            case "pd":
                // System.out.println("called pd");
                return repository.findByPriority(search, 0);
            default:
                // System.out.println("called all");
                return repository.getAll();
        }
    }


    private void createExcelDownloadResponse(HttpServletResponse response, List<Log> logList) {

        try {

            Workbook workbook = new SXSSFWorkbook();
            Sheet sheet = workbook.createSheet("집계 로그 리스트");

            // 숫자 포맷 적용
            CellStyle numberCellStyle = workbook.createCellStyle();
            numberCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));

            // 파일명
            final String fileName = "집계 로그 리스트";

            //헤더
            final String[] header = {"시간", "레벨", "메시지", "호스트"};
            Row row = sheet.createRow(0);
            for (int i = 0; i < header.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(header[i]);
            }

            //바디
            for (int i = 0; i < logList.size(); i++) {
                row = sheet.createRow(i + 1);  //헤더 이후로 데이터가 출력되어야하니 +1

                Log log = logList.get(i);

                Cell cell = null;
                cell = row.createCell(0);
                cell.setCellValue(log.getDeviceReportedTime());

                cell = row.createCell(1);
                cell.setCellStyle(numberCellStyle); // 숫자 포맷
                cell.setCellValue(log.getPriority());

                cell = row.createCell(2);
                cell.setCellValue(log.getMessage());

                cell = row.createCell(3);
                cell.setCellValue(log.getFromHost());

            }

            response.setContentType("ms-vnd/excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
            //파일명은 URLEncoder로 감싸주는게 좋다!

            workbook.write(response.getOutputStream());
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

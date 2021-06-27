package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.CustomReportedAnswerDTO;
import lk.edu.esoft.alsskillminercloud.dto.ReportedAnswerDTO;
import lk.edu.esoft.alsskillminercloud.service.ReportedAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/reportedanswers")
public class ReportedAnswerController {

    @Autowired
    private ReportedAnswerService reportedAnswerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveReportedAnswer(@RequestBody ReportedAnswerDTO reportedAnswerDTO) {
        try {
            return reportedAnswerService.saveReportedAnswer(reportedAnswerDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReportedAnswerDTO getReportedAnswer(@PathVariable("id") long id) {
        try {
            return reportedAnswerService.getReportedAnswer(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteReportedAnswer(@PathVariable("id") long id) {
        try {
            return reportedAnswerService.deleteReportedAnswer(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<ReportedAnswerDTO> getAllReportedAnswers() {
        try {
            return reportedAnswerService.getAllReportedAnswers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/customReportedAnswers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CustomReportedAnswerDTO> getReportedAnswers() {
        try {
            return reportedAnswerService.getReportedAnswers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

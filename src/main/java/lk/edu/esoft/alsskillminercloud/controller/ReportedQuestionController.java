package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.CustomReportedQuestionDTO;
import lk.edu.esoft.alsskillminercloud.dto.ReportedQuestionDTO;
import lk.edu.esoft.alsskillminercloud.service.ReportedQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/reportedquestions")
public class ReportedQuestionController {

    @Autowired
    private ReportedQuestionService reportedQuestionService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveReportedQuestion(@RequestBody ReportedQuestionDTO reportedQuestionDTO) {
        try {
            return reportedQuestionService.saveReportedQuestion(reportedQuestionDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReportedQuestionDTO getReportedQuestion(@PathVariable("id") long id) {
        try {
            return reportedQuestionService.getReportedQuestion(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteReportedQuestion(@PathVariable("id") long id) {
        try {
            return reportedQuestionService.deleteReportedQuestion(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<ReportedQuestionDTO> getAllReportedQuestions() {
        try {
            return reportedQuestionService.getAllReportedQuestions();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/customReportedQuestions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CustomReportedQuestionDTO> getReportedQuestions() {
        try {
            return reportedQuestionService.getReportedQuestions();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}

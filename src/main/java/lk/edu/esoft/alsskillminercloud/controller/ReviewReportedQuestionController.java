package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.QuestionDTO;
import lk.edu.esoft.alsskillminercloud.dto.ReviewReportedQuestionDTO;
import lk.edu.esoft.alsskillminercloud.service.ReviewReportedQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/reviewreportedquestions")
public class ReviewReportedQuestionController {

    @Autowired
    private ReviewReportedQuestionService reviewReportedQuestionService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveReviewQuestion(@RequestBody ReviewReportedQuestionDTO reviewReportedQuestionDTO) {
        try {
            return reviewReportedQuestionService.saveReviewQuestion(reviewReportedQuestionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewReportedQuestionDTO getReviewQuestion(@PathVariable("id") long id) {
        try {
            return reviewReportedQuestionService.getReviewQuestion(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteReviewQuestion(@PathVariable("id") long id) {
        try {
            return reviewReportedQuestionService.deleteReviewQuestion(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<ReviewReportedQuestionDTO> getAllReviewQuestions() {
        try {
            return reviewReportedQuestionService.getAllReviewQuestions();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(value = "/ignoreQuestion", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean ignoreReportedQuestion(@RequestParam("status") int status, @RequestParam("id") long id,
                                          @RequestParam("questionID") long questionID,
                                          @RequestParam("adminName") String adminName,
                                          @RequestParam("date") String date) {

        ReviewReportedQuestionDTO reviewReportedQuestionDTO = new ReviewReportedQuestionDTO(0L, id, adminName, date,
                "Ignored");

        try {
            return reviewReportedQuestionService.ignoreReportedQuestion(status, questionID, reviewReportedQuestionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping(value = "/editQuestion", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean editReportedQuestion(@RequestParam("status") int status, @RequestParam("id") long id,
                                        @RequestParam("questionID") long questionID,
                                        @RequestParam("questionTitle") String questionTitle,
                                        @RequestParam("questionBody") String questionBody,
                                        @RequestParam("adminName") String adminName,
                                        @RequestParam("date") String date) {

        ReviewReportedQuestionDTO reviewReportedQuestionDTO = new ReviewReportedQuestionDTO(0L, id, adminName, date,
                "Edited");

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(questionID);
        questionDTO.setTitle(questionTitle);
        questionDTO.setBody(questionBody);

        try {
            return reviewReportedQuestionService.editReportedQuestion(status, questionDTO, reviewReportedQuestionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping(value = "/removeQuestion", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean removeReportedQuestion(@RequestParam("status") int status, @RequestParam("id") long id,
                                          @RequestParam("questionID") long questionID,
                                          @RequestParam("adminName") String adminName,
                                          @RequestParam("date") String date) {

        ReviewReportedQuestionDTO reviewReportedQuestionDTO = new ReviewReportedQuestionDTO(0L, id, adminName, date,
                "Removed");

        try {
            return reviewReportedQuestionService.removeReportedQuestion(status, questionID, reviewReportedQuestionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

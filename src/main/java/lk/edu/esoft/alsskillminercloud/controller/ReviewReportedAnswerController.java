package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.AnswerDTO;
import lk.edu.esoft.alsskillminercloud.dto.ReviewReportedAnswerDTO;
import lk.edu.esoft.alsskillminercloud.service.ReviewReportedAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/reviewreportedanswers")
public class ReviewReportedAnswerController {

    @Autowired
    private ReviewReportedAnswerService reviewReportedAnswerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveReviewAnswer(@RequestBody ReviewReportedAnswerDTO ReviewReportedAnswerDTO) {
        try {
            return reviewReportedAnswerService.saveReviewAnswer(ReviewReportedAnswerDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewReportedAnswerDTO getReviewAnswer(@PathVariable("id") long id) {

        try {
            return reviewReportedAnswerService.getReviewAnswer(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteReviewAnswer(@PathVariable("id") long id) {
        try {
            return reviewReportedAnswerService.deleteReviewAnswer(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<ReviewReportedAnswerDTO> getAllReviewAnswers() {
        try {
            return reviewReportedAnswerService.getAllReviewAnswers();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(value = "/ignoreAnswer", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean ignoreReportedQuestion(@RequestParam("status") int status, @RequestParam("id") long id,
                                          @RequestParam("answerID") long answerID,
                                          @RequestParam("adminName") String adminName,
                                          @RequestParam("date") String date) {

        ReviewReportedAnswerDTO reviewReportedAnswerDTO = new ReviewReportedAnswerDTO(0, id, adminName, date,
                "Ignored");

        try {
            return reviewReportedAnswerService.ignoreReportedAnswer(status, answerID, reviewReportedAnswerDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping(value = "/editAnswer", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean editReportedQuestion(@RequestParam("status") int status, @RequestParam("id") long id,
                                        @RequestParam("answerID") long answerID,
                                        @RequestParam("answer") String answer,
                                        @RequestParam("adminName") String adminName,
                                        @RequestParam("date") String date) {

        ReviewReportedAnswerDTO reviewReportedAnswerDTO = new ReviewReportedAnswerDTO(0, id, adminName, date,
                "Edited");

        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(answerID);
        answerDTO.setAnswer(answer);

        try {
            return reviewReportedAnswerService.editReportedAnswer(status, answerDTO, reviewReportedAnswerDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping(value = "/removeAnswer", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean removeReportedQuestion(@RequestParam("status") int status, @RequestParam("id") long id,
                                          @RequestParam("answerID") long answerID,
                                          @RequestParam("adminName") String adminName,
                                          @RequestParam("date") String date) {

        ReviewReportedAnswerDTO reviewReportedAnswerDTO = new ReviewReportedAnswerDTO(0, id, adminName, date,
                "Removed");

        try {
            return reviewReportedAnswerService.removeReportedAnswer(status, answerID, reviewReportedAnswerDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

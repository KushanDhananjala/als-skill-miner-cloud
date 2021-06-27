package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.AnswerDTO;
import lk.edu.esoft.alsskillminercloud.dto.CustomAnswerDTO;
import lk.edu.esoft.alsskillminercloud.dto.PostAnswerDTO;
import lk.edu.esoft.alsskillminercloud.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveAnswer(@RequestBody PostAnswerDTO postAnswerDTO) {
        try {
            return answerService.saveAnswer(postAnswerDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AnswerDTO getAnswer(@PathVariable("id") long id) {
        try {
            return answerService.getAnswer(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteAnswer(@PathVariable("id") long id) {
        try {
            return answerService.deleteAnswer(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(value = "/selectedQuestionID/{questionID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CustomAnswerDTO> getQuestionAnswers(@PathVariable("questionID") long questionID) {
        try {
            return answerService.getQuestionAnswers(questionID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/getLastID", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getLastID() {
        try {
            return Long.parseLong(answerService.getLastID());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getTotalAnswers() {
        try {
            return answerService.getTotalAnswers();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @GetMapping(value = "/userAnswerCount/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getUserQuestionCount(@PathVariable("name") String name) {
        try {
            return answerService.getUserAnswerCount(name);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @PostMapping(value = "/increseScore", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean increseScore(@RequestBody AnswerDTO answerDTO) {
        try {
            return answerService.increseScore(answerDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

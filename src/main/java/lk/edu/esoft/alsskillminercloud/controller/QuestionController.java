package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.CustomQuestionDTO;
import lk.edu.esoft.alsskillminercloud.dto.PostQuestionDTO;
import lk.edu.esoft.alsskillminercloud.dto.QuestionDTO;
import lk.edu.esoft.alsskillminercloud.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveQuestion(@RequestBody PostQuestionDTO postQuestionDTO) {
        try {
            return questionService.saveQuestion(postQuestionDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public QuestionDTO getQuestion(@PathVariable("id") long id) {
        try {
            return questionService.getQuestion(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<QuestionDTO> getAllQuestions() {
        try {
            return questionService.getAllQuestions();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/landingPageQuestions/{streamId}/{subjectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CustomQuestionDTO> getLandingPageQuestions(@PathVariable("streamId") Long streamId,
                                                                @PathVariable(value = "subjectId", required = false) Long subjectId) {
        try {
            return questionService.getLandingPageQuestions(streamId, subjectId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/recentQuestions/{streamId}/{subjectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CustomQuestionDTO> getRecentQuestions(@PathVariable("streamId") Long streamId,
                                                           @PathVariable(value = "subjectId", required = false) Long subjectId) {
        try {
            return questionService.getRecentQuestions(streamId, subjectId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/mostlyViewedQuestions/{streamId}/{subjectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CustomQuestionDTO> getMostlyViewedQuestions(@PathVariable("streamId") Long streamId,
                                                                 @PathVariable(value = "subjectId", required = false) Long subjectId) {
        try {
            return questionService.getMostlyViewedQuestions(streamId, subjectId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/mostlyVotedQuestions/{streamId}/{subjectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CustomQuestionDTO> getMostlyVotedQuestions(@PathVariable("streamId") Long streamId,
                                                                @PathVariable(value = "subjectId", required = false) Long subjectId) {
        try {
            return questionService.getMostlyVotedQuestions(streamId, subjectId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/recentFiveQuestions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CustomQuestionDTO> getRecentFiveQuestions() {
        try {
            return questionService.getRecentFiveQuestions();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/getLastID", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getLastID() {
        try {
            return Long.parseLong(questionService.getLastID());
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    @GetMapping(value = "/userQuestionCount/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getUserQuestionCount(@PathVariable("name") String name) {
        try {
            return questionService.getUserQuestionCount(name);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    @PostMapping(value = "/increseCounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean increseCounts(@RequestBody QuestionDTO questionDTO) {
        try {
            return questionService.increseCounts(questionDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping(value = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getTotalQuestions() {
        try {
            return questionService.getTotalQuestions();
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

    @PostMapping(value = "/updateTitleAndBody", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateQuestionTitleAndBody(@RequestParam("title") String title, @RequestParam("body") String body, @RequestParam("id") long id) {
        try {
            return questionService.updateQuestionTitleAndBody(title, body, id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @PostMapping(value = "/updateActiveStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean updateQuestionActiveStatus(@RequestParam("active") int active, @RequestParam("id") long id) {
        try {
            return questionService.updateQuestionActiveStatus(active, id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

}

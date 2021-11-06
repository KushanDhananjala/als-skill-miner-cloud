package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.AnswerVoteDTO;
import lk.edu.esoft.alsskillminercloud.service.AnswerVoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/answervotes")
public class AnswerVoteController {

    @Autowired
    private AnswerVoteService answerVoteService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean saveAnswerVote(@RequestBody AnswerVoteDTO answerVoteDTO) {
        try {
            return answerVoteService.saveAnswerVote(answerVoteDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AnswerVoteDTO getAnswerVote(@PathVariable("id") int id) {
        try {
            return answerVoteService.getAnswerVote(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteAnswerVote(@PathVariable("id") int id) {
        try {
            return answerVoteService.deleteAnswerVote(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<AnswerVoteDTO> getAllAnswerVotes() {
        try {
            return answerVoteService.getAllAnswerVotes();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/userVotesCount/{userName},{answerID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public long getUserAnswerVotesCount(@PathVariable("userName") String userName, @PathVariable("answerID") long answerID) {
        try {
            return answerVoteService.getUserAnswerVotesCount(userName, answerID);
        } catch (Exception e) {
            log.error(e.getMessage());
            return 0;
        }
    }

}

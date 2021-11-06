package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.QuestionAttachmentDTO;
import lk.edu.esoft.alsskillminercloud.service.QuestionAttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/questionattachments")
public class QuestionAttchmentController {

    @Autowired
    private QuestionAttachmentService questionAttachmentService;

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean dleteQuestionAttchment(@PathVariable("id") long id) {
        try {
            return questionAttachmentService.deleteQuestionAttchment(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<QuestionAttachmentDTO> getAllQuestionAttchments() {
        try {
            return questionAttachmentService.getAllQuestionAttchments();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/selectedQuestionAttachments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<QuestionAttachmentDTO> getSelectedQuestionAttachments(@PathVariable("id") long id) {
        try {
            return questionAttachmentService.getSelectedQuestionAttachments(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}

package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.AnswerAttachmentDTO;
import lk.edu.esoft.alsskillminercloud.service.AnswerAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/answerattachments")
public class AnswerAttachmentController {

    @Autowired
    private AnswerAttachmentService answerAttachmentService;

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteAnswerAttchment(@PathVariable("id") long id) {
        try {
            return answerAttachmentService.deleteAnswerAttchment(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<AnswerAttachmentDTO> getAllAnswerAttchments() {
        try {
            return answerAttachmentService.getAllAnswerAttchments();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/selectedQuestionAttachments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<AnswerAttachmentDTO> getSelectedAnswerAttachments(@PathVariable("id") long id) {
        try {
            return answerAttachmentService.getSelectedAnswerAttachments(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

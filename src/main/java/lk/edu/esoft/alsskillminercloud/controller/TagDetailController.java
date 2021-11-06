package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.CustomTagDetailDTO;
import lk.edu.esoft.alsskillminercloud.service.TagDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/tagdetails")
public class TagDetailController {

    @Autowired
    private TagDetailService tagDetailService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CustomTagDetailDTO> getAllQuestionsTags() {
        try {
            return tagDetailService.getAllQuestionsTags();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}

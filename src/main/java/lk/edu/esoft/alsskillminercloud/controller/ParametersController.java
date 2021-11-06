package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.StreamDTO;
import lk.edu.esoft.alsskillminercloud.dto.SubjectDTO;
import lk.edu.esoft.alsskillminercloud.service.StreamService;
import lk.edu.esoft.alsskillminercloud.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/parameters")
@RequiredArgsConstructor
public class ParametersController {

    private final StreamService streamService;
    private final SubjectService subjectService;

    @GetMapping(value = "/streams", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StreamDTO>> getStreams() {
        try {
            return ResponseEntity.ok(streamService.getStreams());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/subjects/{streamId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubjectDTO>> getSubjectsByStream(@PathVariable("streamId") Long streamId) {
        try {
            return ResponseEntity.ok(subjectService.getSubjectsByStream(streamId));
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}

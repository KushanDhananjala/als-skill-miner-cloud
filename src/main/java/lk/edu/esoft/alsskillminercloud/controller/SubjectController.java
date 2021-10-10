package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.SaveSubjectDTO;
import lk.edu.esoft.alsskillminercloud.dto.SubjectDTO;
import lk.edu.esoft.alsskillminercloud.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping(value = "api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public boolean save(@RequestBody SaveSubjectDTO saveSubjectDTO) {
        try {
            return subjectService.save(saveSubjectDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getSubjects() {
        try {
            return ResponseEntity.ok(subjectService.getSubjects());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(subjectService.getSubjectById(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(subjectService.deleteById(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.ok(false);
        }
    }

}

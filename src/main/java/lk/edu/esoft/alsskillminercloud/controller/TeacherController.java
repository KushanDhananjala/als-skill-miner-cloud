package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.TeacherDTO;
import lk.edu.esoft.alsskillminercloud.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping(value = "api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public boolean save(@RequestBody TeacherDTO teacherDTO) {
        try {
            return teacherService.save(teacherDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getSubjects() {
        try {
            return ResponseEntity.ok(teacherService.getTeachers());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(teacherService.getTeacherById(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/user-name/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDTO> getTeacherByUserName(@PathVariable("userName") String userName) {
        try {
            return ResponseEntity.ok(teacherService.getTeacherByUserName(userName));
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(teacherService.deleteById(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.ok(false);
        }
    }

    @PostMapping(value = "/{login}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean canAuthenticate(@RequestBody TeacherDTO teacherDTO) {
        try {
            return teacherService.canAuthenticate(teacherDTO.getUserName(), teacherDTO.getPassword());
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

}

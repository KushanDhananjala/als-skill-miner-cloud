package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.ResourceDTO;
import lk.edu.esoft.alsskillminercloud.service.ResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping(value = "/save-file")
    public ResponseEntity<String> saveResourceFile(@RequestParam("file") MultipartFile file, @RequestParam("teacher") String teacher) {
        try {
            return ResponseEntity.ok(resourceService.saveResourceFile(file, teacher));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(file.getOriginalFilename() + "upload failed");
        }
    }

    @PostMapping
    public boolean save(@RequestBody ResourceDTO resourceDTO) {
        try {
            return resourceService.saveResource(resourceDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping
    public ResponseEntity<List<ResourceDTO>> getStreams() {
        try {
            return ResponseEntity.ok(resourceService.getAllResources());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResourceDTO> getResourcesById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(resourceService.getResource(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/by-teacher/{teacherId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ResourceDTO>> getResourcesByTeacher(@PathVariable("teacherId") Long teacherId) {
        try {
            return ResponseEntity.ok(resourceService.getResourcesByTeacher(teacherId));
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @PutMapping
    public ResponseEntity<Boolean> updateResource(@RequestBody ResourceDTO resourceDTO) {
        try {
            return ResponseEntity.ok(resourceService.updateResource(resourceDTO));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping(value = "/download/{id}")
    public ResponseEntity<InputStreamResource> downloadResource(@PathVariable("id") Long id) {
        try {
            return resourceService.downloadResource(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

}

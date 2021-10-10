package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.dto.StreamDTO;
import lk.edu.esoft.alsskillminercloud.service.StreamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping(value = "api/v1/streams")
@RequiredArgsConstructor
public class StreamController {

    private final StreamService streamService;

    @PostMapping
    public boolean save(@RequestBody StreamDTO streamDTO) {
        try {
            return streamService.save(streamDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @GetMapping
    public ResponseEntity<List<StreamDTO>> getStreams() {
        try {
            return ResponseEntity.ok(streamService.getStreams());
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StreamDTO> getStreamById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(streamService.getStreamById(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(streamService.deleteStream(id));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.ok(false);
        }
    }

}

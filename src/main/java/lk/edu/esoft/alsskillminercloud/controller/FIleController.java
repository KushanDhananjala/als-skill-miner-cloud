package lk.edu.esoft.alsskillminercloud.controller;

import lk.edu.esoft.alsskillminercloud.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class FIleController {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "api/v1/uploadUserImages")
    public ResponseEntity<String> storeUserImage(@RequestParam("file") MultipartFile file, @RequestParam("userName") String userName) {
        String status = null;
        try {
            fileService.storeUserImage(file, userName);
            status = file.getOriginalFilename() + "upload complete";
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } catch (Exception e) {
            status = file.getOriginalFilename() + "upload failed";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(status);
        }
    }

    @PostMapping(value = "api/v1/uploadAdminImages")
    public ResponseEntity<String> storeAdminImage(@RequestParam("file") MultipartFile file, @RequestParam("userName") String userName) {
        String status = null;
        try {
            fileService.storeAdminImage(file, userName);
            status = file.getOriginalFilename() + "upload complete";
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } catch (Exception e) {
            status = file.getOriginalFilename() + "upload faild";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(status);
        }
    }

    @PostMapping(value = "api/v1/uploadQuestionImages")
    public ResponseEntity<String> storeQuestionImages(@RequestParam("file") MultipartFile file, @RequestParam("id") long id) {
        String status = null;
        try {
            fileService.storeQuestionImages(file, id);
            status = "Files upload complete";
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } catch (Exception e) {
            status = "Files upload faild";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(status);
        }
    }

    @PostMapping(value = "api/v1/uploadAnswerImages")
    public ResponseEntity<String> storeAnswerImages(@RequestParam("file") MultipartFile file, @RequestParam("id") long id) {
        String status = null;
        try {
            fileService.storeAnswerImages(file, id);
            status = "Files upload complete";
            return ResponseEntity.status(HttpStatus.OK).body(status);
        } catch (Exception e) {
            status = "Files upload faild";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(status);
        }
    }

    @GetMapping(value = "api/v1/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("path") String path) {
        try {
            return fileService.download(path.replace("/", "\\"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }

}

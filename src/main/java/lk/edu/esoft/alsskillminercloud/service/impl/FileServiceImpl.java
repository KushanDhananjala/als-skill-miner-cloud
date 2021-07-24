package lk.edu.esoft.alsskillminercloud.service.impl;

import lk.edu.esoft.alsskillminercloud.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FileServiceImpl implements FileService {

    private final String userImagesLocation = "user-images/";
    private final String adminImagesLocation = "admin-images/";
    private final String questionImagesLocation = "question-images/";
    private final String answerImagesLocation = "answer-images/";
    @Value("${app.location.uploaded-images}")
    private String LOCATION_UPLOADED_IMAGES;

    @Override
    public void storeUserImage(MultipartFile file, String userName) {
        try {
            Path path = Paths.get(LOCATION_UPLOADED_IMAGES + userImagesLocation + userName + "/");
            Files.createDirectories(path);
            File f = new File(LOCATION_UPLOADED_IMAGES + userImagesLocation + userName + "/" + file.getOriginalFilename());
            if (!f.exists()) {
                Files.copy(file.getInputStream(), path.resolve(Objects.requireNonNull(file.getOriginalFilename())));
            }
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    @Override
    public void storeAdminImage(MultipartFile file, String userName) {
        try {
            Path path = Paths.get(LOCATION_UPLOADED_IMAGES + adminImagesLocation + userName + "/");
            Files.createDirectories(path);
            File f = new File(LOCATION_UPLOADED_IMAGES + adminImagesLocation + userName + "/" + file.getOriginalFilename());
            if (!f.exists()) {
                Files.copy(file.getInputStream(), path.resolve(Objects.requireNonNull(file.getOriginalFilename())));
            }
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    @Override
    public void storeQuestionImages(MultipartFile file, long id) {
        try {
            Path path = Paths.get(LOCATION_UPLOADED_IMAGES + questionImagesLocation + id + "/");
            Files.createDirectories(path);
            File f = new File(LOCATION_UPLOADED_IMAGES + questionImagesLocation + id + "/" + file.getOriginalFilename());
            if (!f.exists()) {
                Files.copy(file.getInputStream(), path.resolve(Objects.requireNonNull(file.getOriginalFilename())));
            }
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    @Override
    public void storeAnswerImages(MultipartFile file, long id) {
        try {
            Path path = Paths.get(LOCATION_UPLOADED_IMAGES + answerImagesLocation + id + "/");
            Files.createDirectories(path);
            File f = new File(LOCATION_UPLOADED_IMAGES + answerImagesLocation + id + "/" + file.getOriginalFilename());
            if (!f.exists()) {
                Files.copy(file.getInputStream(), path.resolve(Objects.requireNonNull(file.getOriginalFilename())));
            }
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }

    @Override
    public ResponseEntity<InputStreamResource> download(String path) {
        try {
            File file = new File(path);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment;filename=" + file.getName())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length())
                    .body(resource);
        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
        }
    }
}
